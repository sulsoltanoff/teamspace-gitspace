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
import org.corpspace.teamspace.domain.User;
import org.corpspace.teamspace.service.dto.UserDTO;
import org.corpspace.teamspace.service.mapper.UserMapper;
import org.springframework.stereotype.Service;

@Service
public class UserMapperImpl implements UserMapper {

    @Override
    public User toEntity(UserDTO dto) {
        User user = new User();
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
        dto.setEmails(convertToDto(entity.getEmails()));
        dto.setFullName(entity.getFullName());
        dto.setGpgKey(convertToDto(entity.getGpgKeys()));
        dto.setSshKey(convertToDto(entity.getSshKeys()));
        dto.setAccessToken(entity.getAccessToken());
        return dto;
    }

    @Override
    public List<User> toEntity(List<UserDTO> dtoList) {
        List<User> user = new ArrayList<>();
        for (UserDTO dto : dtoList) {
            user = (List<User>) toEntity(dto);
        }
        return user;
    }

    @Override
    public List<UserDTO> toDto(List<User> entityList) {
        List<UserDTO> dtoList = new ArrayList<>();
        for (User userEntity : entityList) {
            dtoList = (List<UserDTO>) toDto(userEntity);
        }
        return dtoList;
    }

    @Override
    public void partialUpdate(User entity, UserDTO dto) {
        if (dto.getUsername() != null) {
            entity.setUsername(dto.getUsername());
        }
        if (dto.getEmails() != null) {
            entity.setEmails(convertToEntity(dto.getEmails()));
        }
        if (dto.getFullName() != null) {
            entity.setFullName(dto.getFullName());
        }
        if (dto.getGpgKey() != null) {
            entity.setGpgKeys(convertToEntity(dto.getGpgKey()));
        }
        if (dto.getSshKey() != null) {
            entity.setSshKeys(convertToEntity(dto.getSshKey()));
        }
        if (dto.getAccessToken() != null) {
            entity.setAccessToken(dto.getAccessToken());
        }
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

    private <T, K> List<K> convertToDto(List<T> entityCollection) {
        List<K> dtoList = new ArrayList<>();
        if (entityCollection != null) {
            for (T entity : entityCollection) {
                dtoList.add((K) entity);
            }
        }
        return dtoList;
    }
}
