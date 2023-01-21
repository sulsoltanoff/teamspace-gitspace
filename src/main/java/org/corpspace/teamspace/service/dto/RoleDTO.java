/*
 * Copyright (C) 2023 The Corpspace technologies.
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this file,
 * You can obtain one at https://mozilla.org/MPL/2.0/.
 */

package org.corpspace.teamspace.service.dto;

import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;
import lombok.Getter;
import lombok.Setter;

/**
 * A DTO for the {@link org.corpspace.teamspace.domain.Role} entity.
 */
@SuppressWarnings("common-java:DuplicatedBlocks")
public class RoleDTO implements Serializable {

    @Getter
    @Setter
    private UUID id;

    @Getter
    @Setter
    private String name;

    @Getter
    @Setter
    private Boolean managePullRequests;

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof RoleDTO roleDTO)) {
            return false;
        }

        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, roleDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "RoleDTO{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            ", managePullRequests='" + getManagePullRequests() + "'" +
            "}";
    }
}
