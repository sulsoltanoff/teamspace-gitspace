/*
 * Copyright (C) 2023 The Corpspace technologies.
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this file,
 * You can obtain one at https://mozilla.org/MPL/2.0/.
 */

package org.corpspace.teamspace.util;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.bouncycastle.openpgp.PGPException;
import org.bouncycastle.openpgp.PGPPublicKey;
import org.bouncycastle.openpgp.PGPPublicKeyRing;
import org.bouncycastle.openpgp.PGPUtil;
import org.bouncycastle.openpgp.jcajce.JcaPGPPublicKeyRingCollection;

public class GpgUtils {

    private GpgUtils() {
        throw new IllegalStateException("Utility class");
    }

    public static List<PGPPublicKey> parsePublicKey(String key) {
        try (InputStream in = PGPUtil.getDecoderStream(new ByteArrayInputStream(key.getBytes(StandardCharsets.UTF_8)))) {
            List<PGPPublicKey> keys = new ArrayList<>();
            JcaPGPPublicKeyRingCollection keyRings = new JcaPGPPublicKeyRingCollection(in);
            Iterator<PGPPublicKeyRing> keyRingIterator = keyRings.getKeyRings();

            while (keyRingIterator.hasNext()) {
                Iterator<PGPPublicKey> keyIterator = keyRingIterator.next().getPublicKeys();
                while (keyIterator.hasNext()) {
                    keys.add(keyIterator.next());
                }
            }
            if (keys.isEmpty()) throw new IllegalArgumentException("No public keys found");
            return keys;
        } catch (IOException | PGPException e) {
            // TODO: Define and throw a dedicated exception instead of using a generic one.
            throw new RuntimeException(e);
        }
    }
}
