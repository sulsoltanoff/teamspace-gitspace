/*
 * Copyright (C) 2023 The Corpspace technologies.
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this file,
 * You can obtain one at https://mozilla.org/MPL/2.0/.
 */

package org.corpspace.teamspace.service;

import org.corpspace.teamspace.domain.User;

public interface SshKeyService {
    /**
     * Get the public key for the given user.
     *
     * @param name  The name of the key.
     * @param owner The user to get the key for.
     * @return The public key.
     */
    String createKey(String name, User owner);

    /**
     * Get the public key for the given user.
     *
     * @param name The name of the key.
     * @return The public key.
     */
    String getKey(String name);

    /**
     * Delete the key for the given user.
     *
     * @param name The user name.
     */
    void deleteKey(String name);
}
