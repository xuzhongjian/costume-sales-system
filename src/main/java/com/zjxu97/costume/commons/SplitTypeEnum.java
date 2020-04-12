package com.zjxu97.costume.commons;

/**
 * @author zjxu97
 * @date 2020-04-12 20:42
 */
public enum SplitTypeEnum {

    TYPE("Type"),
    ITEM("Item"),
    SIZE("Size"),
    SEX("Sex");

    private String value;

    SplitTypeEnum(String value) {
        this.value = value;
    }
}
// ItemType ItemDetail Size Sex