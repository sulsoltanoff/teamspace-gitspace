/*
 * Copyright (C) 2023 The Corpspace technologies.
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this file,
 * You can obtain one at https://mozilla.org/MPL/2.0/.
 */

package org.corpspace.teamspace.repository;

import java.util.List;
import org.corpspace.teamspace.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the User entity.
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    //    User findByUsername(String username);
    //
    //    User findByEmail(String email);
    //
    //    User findByUsernameOrEmail(String username, String email);
    //
    //    User findByFullName(String fullName);
    //
    //    User findByAccessToken(String accessToken);
}
