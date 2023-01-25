/*
 * Copyright (C) 2023 The Corpspace technologies.
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this file,
 * You can obtain one at https://mozilla.org/MPL/2.0/.
 */

package org.corpspace.teamspace.service;

import org.corpspace.teamspace.domain.SshKey;
import org.corpspace.teamspace.service.dto.SshKeyDTO;

public interface SshKeyService {
    /**
     * Get the public key for the given user.
     *
     * @param sshKeyDTO the ssh key dto.
     * @return The public key entity.
     */
    SshKey createKey(SshKeyDTO sshKeyDTO);

    /**
     * Get the public key for the given user.
     *
     * @param sshKeyDTO the ssh key dto.
     * @return The public key entity.
     */
    SshKey getKey(SshKeyDTO sshKeyDTO);

    /**
     * Delete the key for the given user.
     *
     * @param sshKeyDTO The public key dto.
     */
    void deleteKey(SshKeyDTO sshKeyDTO);
}
