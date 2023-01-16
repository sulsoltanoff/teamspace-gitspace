/*
 * Copyright (C) 2023 The Corpspace technologies.
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this file,
 * You can obtain one at https://mozilla.org/MPL/2.0/.
 */

package org.corpspace.teamspace.service.mapper;

import static org.assertj.core.api.Assertions.assertThat;

import org.corpspace.teamspace.service.impl.RoleMapperImpl;
import org.junit.jupiter.api.BeforeEach;

class RoleMapperTest {

    private RoleMapperImpl roleMapper;

    @BeforeEach
    public void setUp() {
        roleMapper = new RoleMapperImpl();
    }
}
