/*
 * Copyright (C) 2023 The Corpspace technologies.
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this file,
 * You can obtain one at https://mozilla.org/MPL/2.0/.
 */

package org.corpspace.teamspace.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
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
    public UserDTO update(UserDTO user) {
        return null;
    }

    @Override
    public Optional<UserDTO> partialUpdate(UserDTO user) {
        return Optional.empty();
    }

    @Override
    public void delete(UUID id) {}

    @Override
    public UserDTO findOne(UUID id) {
        return null;
    }

    @Override
    public List<UserDTO> findAll() {
        return null;
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
