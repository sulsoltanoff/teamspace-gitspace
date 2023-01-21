/*
 * Copyright (C) 2023 The Corpspace technologies.
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this file,
 * You can obtain one at https://mozilla.org/MPL/2.0/.
 */

package org.corpspace.teamspace.web.rest.errors;

import java.net.URI;

public class UsernameAlreadyUsedException extends BadRequestAlertException {

    private static final long serialVersionUID = 1L;

    public UsernameAlreadyUsedException() {
        super(ErrorConstants.USERNAME_ALREADY_USED_TYPE, "Username already in use", "userManagement", "userexists");
    }
}
