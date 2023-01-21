/*
 * Copyright (C) 2023 The Corpspace technologies.
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this file,
 * You can obtain one at https://mozilla.org/MPL/2.0/.
 */

package org.corpspace.teamspace.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.io.Serial;
import java.util.UUID;
import javax.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

@Entity
@Table(name = "ssh_key", schema = "public")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class SshKey extends AbstractAuditingEntity<SshKey> {

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @Column(unique = true, nullable = false)
    @Setter
    @Getter
    private UUID id;

    @Column(nullable = false, length = 5000)
    @Getter
    @Setter
    private String content;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(nullable = false)
    private User owner;
}
