/*
 * Copyright (C) 2023 The Corpspace technologies.
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this file,
 * You can obtain one at https://mozilla.org/MPL/2.0/.
 */

package org.corpspace.teamspace.service.impl;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.corpspace.teamspace.domain.Role;
import org.corpspace.teamspace.repository.RoleRepository;
import org.corpspace.teamspace.service.RoleService;
import org.corpspace.teamspace.service.dto.RoleDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link Role}.
 */
@Service
@Transactional
public class RoleServiceImpl implements RoleService {

    private final Logger log = LoggerFactory.getLogger(RoleServiceImpl.class);

    private final RoleRepository roleRepository;

    private final RoleMapperImpl roleMapper;

    public RoleServiceImpl(RoleRepository roleRepository, RoleMapperImpl roleMapper) {
        this.roleRepository = roleRepository;
        this.roleMapper = roleMapper;
    }

    @Override
    public Role createRole(RoleDTO roleDTO) {
        log.debug("Request to save Role : {}", roleDTO);

        Role role = new Role();
        role.setId(UUID.randomUUID());
        role.setName(roleDTO.getName());
        role.setManagePullRequests(roleDTO.getManagePullRequests());
        roleRepository.save(role);

        return role;
    }

    @Override
    public Role update(RoleDTO roleDTO) {
        log.debug("Request to update Role : {}", roleDTO);

        Optional<Role> role = roleRepository.findById(roleDTO.getId());
        if (role.isEmpty()) {
            return null;
        }
        Role roleToUpdate = role.get();
        roleToUpdate.setName(roleDTO.getName());
        roleToUpdate.setManagePullRequests(roleDTO.getManagePullRequests());
        roleRepository.save(roleToUpdate);

        return roleToUpdate;
    }

    @Override
    public Optional<Role> partialUpdate(RoleDTO roleDTO) {
        log.debug("Request to partially update Role : {}", roleDTO);

        return roleRepository
            .findById(roleDTO.getId())
            .map(existingRole -> {
                roleMapper.partialUpdate(existingRole, roleDTO);

                return existingRole;
            })
            .map(roleRepository::save);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Role> findAll() {
        log.debug("Request to get all Roles");

        return new LinkedList<>(roleRepository.findAll());
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Role> findOne(UUID id) {
        log.debug("Request to get Role : {}", id);

        return roleRepository.findById(id);
    }

    @Override
    public void delete(UUID id) {
        log.debug("Request to delete Role : {}", id);

        roleRepository.deleteById(id);
    }
}
