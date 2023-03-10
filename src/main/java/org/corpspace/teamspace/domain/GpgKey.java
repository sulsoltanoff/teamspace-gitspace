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
import java.util.Date;
import java.util.UUID;
import javax.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.corpspace.teamspace.domain.core.BaseGpgKey;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

@Entity
@Table(indexes = { @Index(columnList = "id") }, schema = "public")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class GpgKey extends BaseGpgKey {

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @Column(unique = true, nullable = false)
    @Setter
    @Getter
    private UUID id;

    @JsonIgnore
    @Column(nullable = false)
    @Setter
    @Getter
    private Date creationDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(nullable = false)
    @Setter
    @Getter
    private User owner;
}
