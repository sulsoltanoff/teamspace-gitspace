/*
 * Copyright (C) 2023 The Corpspace technologies.
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this file,
 * You can obtain one at https://mozilla.org/MPL/2.0/.
 */

package org.corpspace.teamspace.service.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import org.corpspace.teamspace.domain.GpgKey;
import org.corpspace.teamspace.domain.SshKey;
import org.corpspace.teamspace.domain.User;
import org.corpspace.teamspace.service.dto.UserDTO;
import org.corpspace.teamspace.service.mapper.UserMapper;
import org.springframework.stereotype.Service;

@Service
public class UserMapperImpl implements UserMapper {

    @Override
    public User toEntity(UserDTO dto) {
        User user = new User();
        user.setId(dto.getId());
        user.setUsername(dto.getUsername());
        user.setEmails(convertToEntity(dto.getEmails()));
        user.setFullName(dto.getFullName());
        user.setGpgKeys(convertToEntity(dto.getGpgKey()));
        user.setSshKeys(convertToEntity(dto.getSshKey()));
        user.setAccessToken(dto.getAccessToken());
        return user;
    }

    @Override
    public UserDTO toDto(User entity) {
        UserDTO dto = new UserDTO();
        dto.setId(entity.getId());
        dto.setUsername(entity.getUsername());
        //        dto.setEmails(toEmailAddressesDTO(entity.getEmails()));
        dto.setFullName(entity.getFullName());
        //        dto.setGpgKey(toGpgKeysDTO(entity.getGpgKeys()));
        //        dto.setSshKey(toSshKeysDTO(entity.getSshKeys()));
        dto.setAccessToken(entity.getAccessToken());
        return dto;
    }

    @Override
    public List<User> toEntity(List<UserDTO> dtoList) {
        return null;
    }

    @Override
    public List<UserDTO> toDto(List<User> entityList) {
        return null;
    }

    @Override
    public void partialUpdate(User entity, UserDTO dto) {}

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
