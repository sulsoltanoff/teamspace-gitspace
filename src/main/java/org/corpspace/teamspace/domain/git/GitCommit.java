/*
 * Copyright (C) 2023 The Corpspace technologies.
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this file,
 * You can obtain one at https://mozilla.org/MPL/2.0/.
 */

package org.corpspace.teamspace.domain.git;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import org.eclipse.jgit.lib.PersonIdent;

/**
 * Git commit information.
 */
public class GitCommit implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    private final String hash;
    private final String message;
    private final PersonIdent author;
    private final Date date;
    private final String subject;
    private final String body;
    private final PersonIdent committer;
    private final List<String> parentHash;
    private final List<FileChange> fileChanges;

    public GitCommit(
        String hash,
        String message,
        PersonIdent author,
        Date date,
        String subject,
        String body,
        PersonIdent committer,
        List<String> parentHash,
        List<FileChange> fileChanges
    ) {
        this.hash = hash;
        this.message = message;
        this.author = author;
        this.date = date;
        this.subject = subject;
        this.body = body;
        this.committer = committer;
        this.parentHash = parentHash;
        this.fileChanges = fileChanges;
    }
}
