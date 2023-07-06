package com.jianhaoweb.config;

/**
 * @Auther:剑豪
 * @Date:2023/5/26
 * @VERSON:1.8
 */
public interface IRiskService {

    Boolean startRiskService(RiskSource riskSource) throws Exception;

    Boolean stopRiskService(RiskSource riskSource) throws Exception;


    Object restartLogstashForNewDatabase(RiskSource riskSource);
}
