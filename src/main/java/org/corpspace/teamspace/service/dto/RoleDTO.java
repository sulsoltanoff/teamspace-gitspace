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
import javax.validation.constraints.*;
import org.corpspace.teamspace.domain.Role;

/**
 * A DTO for the {@link org.corpspace.teamspace.domain.Role} entity.
 */
@SuppressWarnings("common-java:DuplicatedBlocks")
public class RoleDTO implements Serializable {

    private Long id;

    private String name;

    private Boolean managePullRequests;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean getManagePullRequests() {
        return managePullRequests;
    }

    public void setManagePullRequests(Boolean managePullRequests) {
        this.managePullRequests = managePullRequests;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof RoleDTO)) {
            return false;
        }

        RoleDTO roleDTO = (RoleDTO) o;
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
