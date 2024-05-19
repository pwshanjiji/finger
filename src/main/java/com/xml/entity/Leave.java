package com.xml.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * (Leave)实体类
 *
 * @author makejava
 * @since 2024-05-18 20:27:59
 */
@Data
public class Leave {

    private Integer id;
    
    private Integer userId;

    private String userName;
    
    private String startTime;
    
    private String endTime;
    
    private String launchTime;
    
    private String reason;
    
    private String status;

}

