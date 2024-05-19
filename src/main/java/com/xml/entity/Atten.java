package com.xml.entity;

import lombok.Data;

/**
 * (Atten)实体类
 *
 * @author makejava
 * @since 2024-05-19 10:20:41
 */
@Data
public class Atten {

    private Integer id;
    
    private Integer userId;
    
    private String userName;
    private String course;

    private String status;
    
    private String time;

}

