package com.reece.myrpc.pojo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author reece
 * @date 2021-09-06 14:18
 * @description:
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Blog implements Serializable {


    private static final long serialVersionUID = 1L;


    private Integer id;

    private String title;

    private String content;

    private User user;
}
