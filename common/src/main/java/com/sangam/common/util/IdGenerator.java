package com.sangam.common.util;

import java.util.UUID;

public class IdGenerator {

    public static Long   getID() {
        return UUID.randomUUID().getMostSignificantBits() & Long.MAX_VALUE;
    }
}
