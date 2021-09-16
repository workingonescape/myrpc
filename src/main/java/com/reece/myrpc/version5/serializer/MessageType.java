package com.reece.myrpc.version5.serializer;

import lombok.Getter;

/**
 * @author reece
 * @date 2021-09-06 18:19
 * @description:
 */
@Getter
public enum MessageType {


    REQUEST(1),RESPONSE(2);


    private Integer type;


    MessageType(Integer type) {
        this.type = type;
    }
}
