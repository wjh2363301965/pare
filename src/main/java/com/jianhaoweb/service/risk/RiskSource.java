package com.jianhaoweb.service.risk;

import lombok.Data;

import java.io.Serializable;
import java.util.UUID;

@Data
public class RiskSource implements Serializable {

    private String service = UUID.randomUUID().toString();

    private String dbType;
    private String dbIp;
    private Integer dbPort;
    private String userName;
    private String password;

}