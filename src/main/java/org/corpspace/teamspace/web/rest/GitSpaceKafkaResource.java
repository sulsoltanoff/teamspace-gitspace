/*
 * Copyright (C) 2023 The Corpspace technologies.
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this file,
 * You can obtain one at https://mozilla.org/MPL/2.0/.
 */

package org.corpspace.teamspace.web.rest;

import static org.springframework.web.servlet.mvc.method.annotation.SseEmitter.event;

import java.io.IOException;
import java.security.Principal;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import org.corpspace.teamspace.config.KafkaSseConsumer;
import org.corpspace.teamspace.config.KafkaSseProducer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.http.MediaType;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.support.GenericMessage;
import org.springframework.util.MimeTypeUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyEmitter;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

@RestController
@RequestMapping("/api/git-space-kafka")
public class GitSpaceKafkaResource {

    private final Logger log = LoggerFactory.getLogger(GitSpaceKafkaResource.class);
    private final MessageChannel output;

    // TODO implement state of the art emitter repository to become 12 factor
    private Map<String, SseEmitter> emitters = new HashMap<>();

    public GitSpaceKafkaResource(@Qualifier(KafkaSseProducer.CHANNELNAME) MessageChannel output) {
        this.output = output;
    }

    @PostMapping("/publish")
    public void publish(@RequestParam String message) {
        log.debug("REST request the message : {} to send to Kafka topic ", message);
        Map<String, Object> map = new HashMap<>();
        map.put(MessageHeaders.CONTENT_TYPE, MimeTypeUtils.TEXT_PLAIN_VALUE);
        MessageHeaders headers = new MessageHeaders(map);
        output.send(new GenericMessage<>(message, headers));
    }

    @GetMapping("/register")
    public ResponseBodyEmitter register(Principal principal) {
        log.debug("Registering sse client for {}", principal.getName());
        SseEmitter emitter = new SseEmitter();
        emitter.onCompletion(() -> emitters.remove(emitter));
        emitters.put(principal.getName(), emitter);
        return emitter;
    }

    @GetMapping("/unregister")
    public void unregister(Principal principal) {
        String user = principal.getName();
        log.debug("Unregistering sse emitter for user: {}", user);
        Optional.ofNullable(emitters.get(user)).ifPresent(ResponseBodyEmitter::complete);
    }

    @StreamListener(value = KafkaSseConsumer.CHANNELNAME, copyHeaders = "false")
    public void consume(Message<String> message) {
        log.debug("Got message from kafka stream: {}", message.getPayload());
        emitters
            .entrySet()
            .stream()
            .map(Map.Entry::getValue)
            .forEach((SseEmitter emitter) -> {
                try {
                    emitter.send(event().data(message.getPayload(), MediaType.TEXT_PLAIN));
                } catch (IOException e) {
                    log.debug("error sending sse message, {}", message.getPayload());
                }
            });
    }
}
