/*
 * Copyright (C) 2023 The Corpspace technologies.
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this file,
 * You can obtain one at https://mozilla.org/MPL/2.0/.
 */

package org.corpspace.teamspace.domain;

import java.io.Serial;
import java.util.UUID;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A Role.
 */
@Entity
@Table(name = "role", schema = "public")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@SuppressWarnings("common-java:DuplicatedBlocks")
public class Role extends AbstractAuditingEntity<Role> {

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @Column(unique = true, nullable = false)
    @Getter
    @Setter
    private UUID id;

    @Column(name = "name", unique = true)
    @Getter
    @Setter
    private String name;

    @Column(name = "manage_pull_requests")
    @Getter
    @Setter
    private Boolean managePullRequests;

    public Role id(UUID id) {
        this.setId(id);
        return this;
    }

    public Role name(String name) {
        this.setName(name);
        return this;
    }

    public Role managePullRequests(Boolean managePullRequests) {
        this.setManagePullRequests(managePullRequests);
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Role)) {
            return false;
        }
        return id != null && id.equals(((Role) o).id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }

    @Override
    public String toString() {
        return "Role{" + "id=" + getId() + ", name='" + getName() + "'" + ", managePullRequests='" + getManagePullRequests() + "'" + "}";
    }
}
