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
import java.util.UUID;
import org.corpspace.teamspace.domain.Role;
import org.corpspace.teamspace.service.dto.RoleDTO;
import org.corpspace.teamspace.service.mapper.RoleMapper;
import org.springframework.stereotype.Service;

@Service
public class RoleMapperImpl implements RoleMapper {

    @Override
    public Role toEntity(RoleDTO dto) {
        Role role = new Role();
        role.setId(UUID.randomUUID());
        role.setName(dto.getName());
        role.setManagePullRequests(dto.getManagePullRequests());
        return role;
    }

    @Override
    public RoleDTO toDto(Role entity) {
        RoleDTO dto = new RoleDTO();
        dto.setId(entity.getId());
        dto.setName(entity.getName());
        dto.setManagePullRequests(entity.getManagePullRequests());
        return dto;
    }

    @Override
    public List<Role> toEntity(List<RoleDTO> dtoList) {
        List<Role> roleList = new ArrayList<>();
        for (RoleDTO roleDto : dtoList) {
            roleList = (List<Role>) toEntity(roleDto);
        }
        return roleList;
    }

    @Override
    public List<RoleDTO> toDto(List<Role> entityList) {
        List<RoleDTO> dtoList = new ArrayList<>();
        for (Role roleEntity : entityList) {
            dtoList = (List<RoleDTO>) toDto(roleEntity);
        }
        return dtoList;
    }

    @Override
    public void partialUpdate(Role entity, RoleDTO dto) {}
}
