/*
 * Copyright (C) 2023 The Corpspace technologies.
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this file,
 * You can obtain one at https://mozilla.org/MPL/2.0/.
 */

package org.corpspace.teamspace.service.impl;

import java.util.*;
import org.corpspace.teamspace.domain.User;
import org.corpspace.teamspace.repository.UserRepository;
import org.corpspace.teamspace.service.UserService;
import org.corpspace.teamspace.service.dto.UserDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    private final Logger log = LoggerFactory.getLogger(UserServiceImpl.class);

    private final UserRepository userRepository;

    private final UserMapperImpl userMapper;

    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository, UserMapperImpl userMapper, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public User createUser(UserDTO userDto) {
        log.debug("Request to save User : {}", userDto);

        User user = new User();
        user.setId(UUID.randomUUID());
        user.setUsername(userDto.getUsername().toLowerCase());
        user.setFullName(userDto.getFullName());
        String passwordEncrypted = passwordEncoder.encode(userDto.getPassword());
        user.setPassword(passwordEncrypted);
        user.setEmails(convertToEntity(userDto.getEmails()));
        user.setAccessToken(userDto.getAccessToken());
        user.setSshKeys(convertToEntity(userDto.getSshKey()));
        user.setGpgKeys(convertToEntity(userDto.getGpgKey()));
        userRepository.save(user);

        return user;
    }

    @Override
    public User update(UserDTO user) {
        log.debug("Request to update User : {}", user);

        Optional<User> userEntity = userRepository.findById(user.getId());
        if (userEntity.isEmpty()) {
            return null;
        }
        User userEntityToUpdate = userEntity.get();
        userEntityToUpdate.setFullName(user.getFullName());
        userEntityToUpdate.setEmails(convertToEntity(user.getEmails()));
        userEntityToUpdate.setSshKeys(convertToEntity(user.getSshKey()));
        userEntityToUpdate.setGpgKeys(convertToEntity(user.getGpgKey()));
        userRepository.save(userEntityToUpdate);
        return userEntityToUpdate;
    }

    @Override
    public Optional<User> partialUpdate(UserDTO user) {
        log.debug("Request to update User : {}", user);

        return userRepository
            .findById(user.getId())
            .map(existingUser -> {
                userMapper.partialUpdate(existingUser, user);
                return existingUser;
            })
            .map(userRepository::save);
    }

    @Override
    public void delete(UUID id) {
        log.debug("Request to delete User : {}", id);

        userRepository.deleteById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<User> findOne(UUID id) {
        log.debug("Request to get User : {}", id);

        return userRepository.findById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<User> findAll() {
        log.debug("Request to get all Users");

        return new LinkedList<>(userRepository.findAll());
    }

    private <T, K> List<K> convertToEntity(List<T> dtoCollection) {
        List<K> entityList = new ArrayList<>();
        if (dtoCollection != null) {
            for (T dto : dtoCollection) {
                entityList.add((K) dto);
            }
        }
        return entityList;
    }
}
