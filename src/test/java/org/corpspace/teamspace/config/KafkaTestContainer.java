/*
 * Copyright (C) 2023 The Corpspace technologies.
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this file,
 * You can obtain one at https://mozilla.org/MPL/2.0/.
 */

package org.corpspace.teamspace.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.testcontainers.containers.KafkaContainer;
import org.testcontainers.containers.output.Slf4jLogConsumer;
import org.testcontainers.utility.DockerImageName;

public class KafkaTestContainer implements InitializingBean, DisposableBean {

    private KafkaContainer kafkaContainer;
    private static final Logger log = LoggerFactory.getLogger(KafkaTestContainer.class);

    @Override
    public void destroy() {
        if (null != kafkaContainer && kafkaContainer.isRunning()) {
            kafkaContainer.close();
        }
    }

    @Override
    public void afterPropertiesSet() {
        if (null == kafkaContainer) {
            kafkaContainer =
                new KafkaContainer(DockerImageName.parse("confluentinc/cp-kafka:7.2.1"))
                    .withLogConsumer(new Slf4jLogConsumer(log))
                    .withReuse(true);
        }
        if (!kafkaContainer.isRunning()) {
            kafkaContainer.start();
        }
    }

    public KafkaContainer getKafkaContainer() {
        return kafkaContainer;
    }
}
