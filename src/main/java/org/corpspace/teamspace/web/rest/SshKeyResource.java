/*
 * Copyright (C) 2023 The Corpspace technologies.
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this file,
 * You can obtain one at https://mozilla.org/MPL/2.0/.
 */

package org.corpspace.teamspace.web.rest;

import java.util.UUID;
import javax.validation.Valid;
import org.corpspace.teamspace.domain.SshKey;
import org.corpspace.teamspace.repository.SshKeyRepository;
import org.corpspace.teamspace.service.dto.SshKeyDTO;
import org.corpspace.teamspace.service.impl.SshKeyServiceImpl;
import org.corpspace.teamspace.web.rest.errors.BadRequestAlertException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/ssh-keys")
public class SshKeyResource {

    private static final String ENTITY_NAME = "gitSpaceSshKey";
    private final Logger log = LoggerFactory.getLogger(SshKeyResource.class);
    private final SshKeyServiceImpl sshKeyService;
    private final SshKeyRepository sshKeyRepository;

    public SshKeyResource(SshKeyServiceImpl sshKeyService, SshKeyRepository sshKeyRepository) {
        this.sshKeyService = sshKeyService;
        this.sshKeyRepository = sshKeyRepository;
    }

    /**
     * @param id the id of the ssh key to retrieve
     * @return the ResponseEntity with status {@code 200 (OK)} and with body the ssh key, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/{id}")
    public ResponseEntity<SshKey> getSshKey(@PathVariable UUID id) {
        log.debug("REST request to get SshKey : {}", id);

        if (sshKeyRepository.findById(id).isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(sshKeyRepository.findById(id).get());
    }

    @PostMapping("/")
    public ResponseEntity<SshKey> createSshKey(@Valid @RequestBody SshKeyDTO sshKeyDTO) {
        log.debug("REST request to save SshKey : {}", sshKeyDTO);

        if (sshKeyDTO.getKeyId() != null) {
            throw new BadRequestAlertException("A new role cannot already have an ID", ENTITY_NAME, "idexists");
        }

        SshKey sshKey = sshKeyService.createKey(sshKeyDTO);
        return ResponseEntity.ok(sshKey);
    }
}
