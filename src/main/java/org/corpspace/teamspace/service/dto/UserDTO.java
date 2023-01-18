/*
 * Copyright (C) 2023 The Corpspace technologies.
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this file,
 * You can obtain one at https://mozilla.org/MPL/2.0/.
 */

package org.corpspace.teamspace.service.dto;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.Objects;
import javax.validation.constraints.Email;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.corpspace.teamspace.config.Constants;

public class UserDTO implements Serializable {

    @Getter
    @Setter
    private Long id;

    @Pattern(regexp = Constants.USERNAME_REGEX)
    @Getter
    @Setter
    private String username;

    @Email
    @Size(min = 5, max = 255)
    @Getter
    @Setter
    private Collection<String> emails;

    @Getter
    @Setter
    private String fullName;

    @Getter
    @Setter
    private String accessToken;

    @Getter
    @Setter
    private List<String> sshKey;

    @Getter
    @Setter
    private List<String> gpgKey;

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof UserDTO userDTO)) {
            return false;
        }

        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, userDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "UserDTO{" +
            "id=" + getId() +
            ", name='" + getFullName() + "'" +
            ", email ='" + getEmails() + "'" +
            "}";
    }
}
