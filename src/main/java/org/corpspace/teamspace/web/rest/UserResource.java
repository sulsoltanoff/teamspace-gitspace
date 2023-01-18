/*
 * Copyright (C) 2023 The Corpspace technologies.
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this file,
 * You can obtain one at https://mozilla.org/MPL/2.0/.
 */

package org.corpspace.teamspace.web.rest;

import java.net.URI;
import java.net.URISyntaxException;
import javax.validation.Valid;
import org.corpspace.teamspace.repository.UserRepository;
import org.corpspace.teamspace.service.dto.RoleDTO;
import org.corpspace.teamspace.service.dto.UserDTO;
import org.corpspace.teamspace.service.impl.UserServiceImpl;
import org.corpspace.teamspace.web.rest.errors.BadRequestAlertException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tech.jhipster.web.util.HeaderUtil;

@RestController
@RequestMapping("/api/v1")
public class UserResource {

    private final Logger log = LoggerFactory.getLogger(UserResource.class);

    private static final String ENTITY_NAME = "gitSpaceUser";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final UserServiceImpl userService;

    private final UserRepository userRepository;

    public UserResource(UserServiceImpl userService, UserRepository userRepository) {
        this.userService = userService;
        this.userRepository = userRepository;
    }

    /**
     * POST  /users : Create a new user.
     * @param userDTO the userDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new userDTO, or with status 400 (Bad Request) if the user has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/users")
    public ResponseEntity<UserDTO> createUser(@Valid @RequestBody UserDTO userDTO) throws URISyntaxException {
        log.debug("REST request to save User : {}", userDTO);
        if (userDTO.getId() != null) {
            throw new BadRequestAlertException("A new role cannot already have an ID", ENTITY_NAME, "id exists");
        }
        UserDTO result = userService.save(userDTO);
        return ResponseEntity
            .created(new URI("/api/v1/users/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }
}
