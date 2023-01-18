/*
 * Copyright (C) 2023 The Corpspace technologies.
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this file,
 * You can obtain one at https://mozilla.org/MPL/2.0/.
 */

package org.corpspace.teamspace.service.impl;

import java.util.Collection;
import java.util.List;
import org.corpspace.teamspace.domain.EmailAddress;
import org.corpspace.teamspace.domain.GpgKey;
import org.corpspace.teamspace.domain.SshKey;
import org.corpspace.teamspace.domain.User;
import org.corpspace.teamspace.service.dto.UserDTO;
import org.corpspace.teamspace.service.mapper.UserMapper;

public class UserMapperImpl implements UserMapper {

    @Override
    public User toEntity(UserDTO dto) {
        User user = new User();
        user.setId(dto.getId());
        user.setUsername(dto.getUsername());
        user.setEmails(toEmailAddresses(dto.getEmails()));
        user.setFullName(dto.getFullName());
        user.setGpgKeys(toGpgKeys(dto.getGpgKey()));
        user.setSshKeys(toSshKeys(dto.getSshKey()));
        user.setAccessToken(dto.getAccessToken());
        return user;
    }

    @Override
    public UserDTO toDto(User entity) {
        return null;
    }

    @Override
    public List<User> toEntity(List<UserDTO> dtoList) {
        return null;
    }

    @Override
    public List<UserDTO> toDto(List<User> entityList) {
        return null;
    }

    @Override
    public void partialUpdate(User entity, UserDTO dto) {}

    private Collection<EmailAddress> toEmailAddresses(Collection<String> emails) {
        Collection<EmailAddress> emailAddresses = null;
        if (emails != null) {
            for (Object email : emails) {
                if (emailAddresses == null) {
                    emailAddresses = (Collection<EmailAddress>) email;
                } else {
                    emailAddresses.add((EmailAddress) email);
                }
            }
        }
        return emailAddresses;
    }

    private List<SshKey> toSshKeys(List<String> sshKeys) {
        return null;
    }

    private List<GpgKey> toGpgKeys(List<String> gpgKeys) {
        return null;
    }
}
