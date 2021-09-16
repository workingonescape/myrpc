package com.reece.myrpc.version5.serializer;

import lombok.Getter;

/**
 * @author reece
 * @date 2021-09-06 21:04
 * @description:
 */
@Getter
public enum SerializeTypeEnum {

    JAVA(0), JSON(1);


    private Integer type;

    SerializeTypeEnum(Integer type) {
        this.type = type;
    }


    public Integer getType() {
        return type;
    }
}

