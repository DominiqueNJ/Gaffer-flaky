/*
 * Copyright 2016-2020 Crown Copyright
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package uk.gov.gchq.gaffer.serialisation;

import uk.gov.gchq.gaffer.exception.SerialisationException;

import java.nio.charset.StandardCharsets;

/**
 * @deprecated this is not very efficient and should only be used for compatibility
 * reasons. For new properties use {@link uk.gov.gchq.gaffer.serialisation.implementation.raw.RawFloatSerialiser}
 * instead.
 */
@Deprecated
public class FloatSerialiser implements ToBytesSerialiser<Float> {
    private static final long serialVersionUID = -4732565151514793209L;

    @Override
    public boolean canHandle(final Class clazz) {
        return Float.class.equals(clazz);
    }

    @Override
    public byte[] serialise(final Float value) throws SerialisationException {
        return value.toString().getBytes(StandardCharsets.ISO_8859_1);
    }

    @Override
    public Float deserialise(final byte[] bytes) throws SerialisationException {
        try {
            return Float.parseFloat(new String(bytes, StandardCharsets.ISO_8859_1));
        } catch (final NumberFormatException e) {
            throw new SerialisationException(e.getMessage(), e);
        }
    }

    @Override
    public Float deserialiseEmpty() {
        return null;
    }

    @Override
    public boolean preservesObjectOrdering() {
        return true;
    }

    @Override
    public boolean isConsistent() {
        return true;
    }

    @Override
    public boolean equals(final Object obj) {
        return this == obj || obj != null && this.getClass() == obj.getClass();
    }

    @Override
    public int hashCode() {
        return FloatSerialiser.class.getName().hashCode();
    }
}
