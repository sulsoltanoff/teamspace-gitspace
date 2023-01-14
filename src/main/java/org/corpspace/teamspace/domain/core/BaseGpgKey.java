/*
 * Copyright (C) 2023 The Corpspace technologies.
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this file,
 * You can obtain one at https://mozilla.org/MPL/2.0/.
 */

package org.corpspace.teamspace.domain.core;

import java.nio.charset.StandardCharsets;
import javax.persistence.Column;
import javax.persistence.Lob;
import javax.persistence.MappedSuperclass;
import javax.validation.constraints.NotEmpty;
import org.bouncycastle.openpgp.PGPPublicKey;
import org.corpspace.teamspace.domain.AbstractAuditingEntity;

@MappedSuperclass
public class BaseGpgKey extends AbstractAuditingEntity {

    private static final long serialVersionUID = 1L;

    /**
     * Gpg keys content can be long.
     */
    @Lob
    @Column(length = 65540, nullable = false)
    private byte[] content;

    private transient List<PGPPublicKey> pgpPublicKeyList;

    @Override
    public Object getId() {
        return null;
    }

    @NotEmpty
    public String getContent() {
        if (content != null) return new String(content, StandardCharsets.UTF_8);
        return null;
    }

    public void setContent(String content) {
        if (content != null) this.content = content.getBytes(StandardCharsets.UTF_8);
        this.content = null;
    }
}
