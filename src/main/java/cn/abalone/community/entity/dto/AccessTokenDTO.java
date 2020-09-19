package cn.abalone.community.entity.dto;

import lombok.Data;

/**
 * Create by Abalone
 * CreateTime: 2020/9/20 0:01
 */

@Data
public class AccessTokenDTO {
    private String client_id;
    private String client_secret;
    private String code;
}
