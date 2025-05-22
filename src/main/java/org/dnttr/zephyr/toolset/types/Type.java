package org.dnttr.zephyr.toolset.types;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * @author dnttr
 */

@Getter
@AllArgsConstructor
@RequiredArgsConstructor
public enum Type {

    /*
     * 8 bit
     */

    BYTE(0x1, 1),
    BOOLEAN(0x2, 1),

    /*
     * 16 bit
     */

    SHORT(0x3, 2),
    CHAR(0x4, 2),

    /*
     * 32 bit
     */

    INT(0x5, 4),
    FLOAT(0x6, 4),

    /*
     * 64 bit
     */

    LONG(0x7, 8),
    DOUBLE(0x8, 8),

    /*
     * Non-primitive types (therefore not fixed size)
     */

    STRING(0x9);

    private final int modifiers;
    private int bytes;
}
