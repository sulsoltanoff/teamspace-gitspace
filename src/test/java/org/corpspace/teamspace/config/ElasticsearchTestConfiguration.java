/*
 * Copyright (C) 2023 The Corpspace technologies.
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this file,
 * You can obtain one at https://mozilla.org/MPL/2.0/.
 */

package org.corpspace.teamspace.config;

import javax.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;
import org.springframework.data.elasticsearch.core.RefreshPolicy;

@Configuration
public class ElasticsearchTestConfiguration {

    @Autowired
    ElasticsearchRestTemplate template;

    @PostConstruct
    public void configureTemplate() {
        this.template.setRefreshPolicy(RefreshPolicy.IMMEDIATE);
    }
}
