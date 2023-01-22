/*
 * Copyright (C) 2023 The Corpspace technologies.
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this file,
 * You can obtain one at https://mozilla.org/MPL/2.0/.
 */

package org.corpspace.teamspace.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.corpspace.teamspace.domain.User;
import org.corpspace.teamspace.service.dto.UserDTO;

/**
 * Service Interface for managing {@link org.corpspace.teamspace.domain.User}.
 */
public interface UserService {
    /**
     * Save a user.
     *
     * @param user the entity to save.
     * @return the persisted entity.
     */
    User createUser(UserDTO user);

    /**
     * Update the users.
     *
     * @param user the entity to update.
     * @return the persisted entity.
     */
    User update(UserDTO user);

    /**
     * Partial update the users.
     *
     * @param user the entity to update.
     * @return the persisted entity.
     */
    Optional<User> partialUpdate(UserDTO user);

    /**
     * Delete the user.
     *
     * @param id the id of the entity.
     */
    void delete(UUID id);

    /**
     * Find all users.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<User> findOne(UUID id);

    /**
     * Find all users.
     *
     * @return the find all entity.
     */
    List<User> findAll();
}
