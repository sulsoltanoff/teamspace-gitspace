/*
 * Copyright (C) 2023 The Corpspace technologies.
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this file,
 * You can obtain one at https://mozilla.org/MPL/2.0/.
 */

package org.corpspace.teamspace.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.RandomStringUtils;
import org.corpspace.teamspace.config.Constants;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

@Entity
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@Table(name = "user", indexes = { @Index(columnList = "username") })
public class User extends AbstractAuditingEntity {

    private static final long serialVersionUID = 1L;

    @Column(unique = true, nullable = false)
    @NotNull
    @Pattern(regexp = Constants.USERNAME_REGEX)
    @Getter
    @Setter
    private String username;

    @JsonIgnore
    @Column(length = 1024, nullable = false, name = "password_hash")
    @Getter
    @Setter
    private String password;

    @Getter
    @Setter
    private String fullName;

    @JsonIgnore
    @Lob
    @Column(length = 65535)
    @Getter
    @Setter
    private String accessToken = RandomStringUtils.randomAlphanumeric(40);

    @OneToMany(mappedBy = "owner", cascade = CascadeType.REMOVE)
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @Getter
    @Setter
    private List<SshKey> sshKeys = new ArrayList<>();

    @OneToMany(mappedBy = "owner", cascade = CascadeType.REMOVE)
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @Getter
    @Setter
    @Email
    private Collection<EmailAddress> emails = new ArrayList<>();

    @OneToMany(mappedBy = "owner", cascade = CascadeType.REMOVE)
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @Getter
    @Setter
    private List<GpgKey> gpgKeys = new ArrayList<>();

    public String getDisplayUsername() {
        if (getFullName() != null) return getFullName();
        return getUsername();
    }

    @Override
    public String toString() {
        return "User{" + "username='" + username + '\'' + ", id='" + this.getId() + '\'' + '}';
    }

    @Override
    public int compareTo(AbstractAuditingEntity entity) {
        User user = (User) entity;
        return getDisplayUsername().compareTo(user.getDisplayUsername());
    }
}
