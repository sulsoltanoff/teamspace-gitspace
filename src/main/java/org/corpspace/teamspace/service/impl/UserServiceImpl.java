/*
 * Copyright (C) 2023 The Corpspace technologies.
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this file,
 * You can obtain one at https://mozilla.org/MPL/2.0/.
 */

package org.corpspace.teamspace.service.impl;

import java.util.List;
import java.util.Optional;
import org.corpspace.teamspace.domain.User;
import org.corpspace.teamspace.repository.UserRepository;
import org.corpspace.teamspace.service.UserService;
import org.corpspace.teamspace.service.dto.UserDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    private final Logger log = LoggerFactory.getLogger(UserServiceImpl.class);

    private final UserRepository userRepository;

    private final UserMapperImpl userMapper;

    public UserServiceImpl(UserRepository userRepository, UserMapperImpl userMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }

    @Override
    public UserDTO save(UserDTO userDto) {
        log.debug("Request to save User : {}", userDto);
        User user = userMapper.toEntity(userDto);
        user = userRepository.save(user);
        return userMapper.toDto(user);
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
    public void delete(Long id) {}

    @Override
    public UserDTO findOne(Long id) {
        return null;
    }

    @Override
    public List<UserDTO> findAll() {
        return null;
    }
}
