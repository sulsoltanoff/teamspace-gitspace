/*
 * Copyright (C) 2023 The Corpspace technologies.
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this file,
 * You can obtain one at https://mozilla.org/MPL/2.0/.
 */

package org.corpspace.teamspace.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;
import java.time.Instant;
import javax.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

/**
 * Base abstract class for entities which will hold definitions for created, last modified, created by,
 * last modified by attributes.
 */
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
@JsonIgnoreProperties(value = { "createdBy", "createdDate", "lastModifiedBy", "lastModifiedDate" }, allowGetters = true)
public abstract class AbstractAuditingEntity<T> implements Serializable, Comparable<AbstractAuditingEntity> {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(generator = "entity_id")
    @GenericGenerator(name = "entity_id", strategy = "org.corpspace.teamspace.domain.core.EntityIdGenerator")
    @Getter
    @Setter
    private Long id;

    @CreatedBy
    @Column(name = "created_by", nullable = false, length = 50, updatable = false)
    @Getter
    @Setter
    private String createdBy;

    @CreatedDate
    @Column(name = "created_date", updatable = false)
    @Getter
    @Setter
    private Instant createdDate = Instant.now();

    @LastModifiedBy
    @Column(name = "last_modified_by", length = 50)
    @Getter
    @Setter
    private String lastModifiedBy;

    @LastModifiedDate
    @Column(name = "last_modified_date")
    @Getter
    @Setter
    private Instant lastModifiedDate = Instant.now();

    public int compareTo(AbstractAuditingEntity entity) {
        if (this.getId() != null) {
            if (entity.getId() != null) return getId().compareTo(entity.getId()); else return -1;
        } else if (entity.getId() != null) {
            return 1;
        } else {
            return 0;
        }
    }
}
