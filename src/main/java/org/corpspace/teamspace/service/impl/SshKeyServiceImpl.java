/*
 * Copyright (C) 2023 The Corpspace technologies.
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this file,
 * You can obtain one at https://mozilla.org/MPL/2.0/.
 */

package org.corpspace.teamspace.service.impl;

import java.util.UUID;
import org.corpspace.teamspace.domain.SshKey;
import org.corpspace.teamspace.repository.SshKeyRepository;
import org.corpspace.teamspace.repository.UserRepository;
import org.corpspace.teamspace.service.SshKeyService;
import org.corpspace.teamspace.service.dto.SshKeyDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class SshKeyServiceImpl implements SshKeyService {

    private final Logger log = LoggerFactory.getLogger(UserServiceImpl.class);

    private final SshKeyRepository sshKeyRepository;

    private final UserRepository userRepository;

    public SshKeyServiceImpl(SshKeyRepository sshKeyRepository, UserRepository userRepository) {
        this.sshKeyRepository = sshKeyRepository;
        this.userRepository = userRepository;
    }

    @Override
    public SshKey createKey(SshKeyDTO sshKeyDTO) {
        log.debug("Request to save SshKey : {}", sshKeyDTO);

        SshKey sshKey = new SshKey();
        sshKey.setId(UUID.randomUUID());
        sshKey.setContent(sshKeyDTO.getContent());
        sshKey.setOwner(userRepository.findById(sshKeyDTO.getOwnerId()).get());
        sshKeyRepository.save(sshKey);

        return sshKey;
    }

    @Override
    public SshKey getKey(SshKeyDTO sshKeyDTO) {
        return null;
    }

    @Override
    public void deleteKey(SshKeyDTO sshKeyDTO) {}
}
