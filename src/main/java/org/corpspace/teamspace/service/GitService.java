/*
 * Copyright (C) 2023 The Corpspace technologies.
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this file,
 * You can obtain one at https://mozilla.org/MPL/2.0/.
 */

package org.corpspace.teamspace.service;

import org.eclipse.jgit.lib.ObjectId;
import org.eclipse.jgit.lib.PersonIdent;

public interface GitService {
    String getDefaultBranch(String project);
    void push(String targetProject, String sourceProject);
    void fetch(String sourceProject, String targetProject);
    ObjectId rebase(String project, ObjectId source, ObjectId target, PersonIdent committer);

    ObjectId commit(
        String project,
        String blobEdits,
        String refName,
        ObjectId expectedOldCommitId,
        ObjectId parentCommitId,
        PersonIdent authorAndCommitter,
        String commitMessage,
        boolean signRequired
    );
    void deleteTag(String project, String tagName);
    void deleteBranch(String project, String branchName);
}
