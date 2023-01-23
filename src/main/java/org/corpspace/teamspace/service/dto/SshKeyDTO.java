/*
 * Copyright (C) 2023 The Corpspace technologies.
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this file,
 * You can obtain one at https://mozilla.org/MPL/2.0/.
 */

package org.corpspace.teamspace.service.dto;

import java.io.Serializable;
import java.util.UUID;
import lombok.Getter;
import lombok.Setter;

/**
 * A DTO for the {@link org.corpspace.teamspace.domain.SshKey} entity.
 */
public class SshKeyDTO implements Serializable {

    @Getter
    @Setter
    private UUID id;

    @Getter
    @Setter
    private String content;

    @Getter
    @Setter
    private String owner;
}
