package com.zjxu97.costume.common;

/**
 * @author zjxu97
 * @date 2020/1/19 18:16
 */

public enum SaleTypeEnum {
    SALE((byte) 1),
    RETURN((byte) 2);

    private Byte value;

    SaleTypeEnum(Byte value) {
        this.value = value;
    }

    public Byte getValue() {
        return value;
    }

    public void setValue(Byte value) {
        this.value = value;
    }
}
