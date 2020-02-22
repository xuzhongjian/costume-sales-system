package com.zjxu97.costume.commons;

/**
 * @author zjxu97
 * @date 2020/1/19 18:16
 */
public enum InOutEnum {
    /**
     * in - 1
     * out - 2
     */
    IN((byte) 1),
    OUT((byte) 2);

    private Byte value;

    InOutEnum(Byte value) {
        this.value = value;
    }

    public Byte getValue() {
        return value;
    }

    public void setValue(Byte value) {
        this.value = value;
    }
}
