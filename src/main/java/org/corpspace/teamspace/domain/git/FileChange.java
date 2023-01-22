/*
 * Copyright (C) 2023 The Corpspace technologies.
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this file,
 * You can obtain one at https://mozilla.org/MPL/2.0/.
 */

package org.corpspace.teamspace.domain.git;

import java.io.Serializable;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import lombok.Getter;

@Getter
public class FileChange implements Serializable {

    private final String newPath;
    private final String oldPath;
    private final int addition;
    private final int removal;

    public FileChange(String newPath, String oldPath, int addition, int removal) {
        this.newPath = newPath;
        this.oldPath = oldPath;
        this.addition = addition;
        this.removal = removal;
    }

    /**
     * @return the set of changed files
     */
    public Collection<String> getPaths() {
        Set<String> paths = new HashSet<>();
        if (getOldPath() != null) paths.add(getOldPath());
        if (getNewPath() != null) paths.add(getNewPath());
        return paths;
    }

    public boolean matches(String path) {
        return path.equals(getOldPath()) || path.equals(getNewPath());
    }
}
