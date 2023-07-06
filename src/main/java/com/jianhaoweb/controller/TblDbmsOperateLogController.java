package com.jianhaoweb.controller;



import com.jianhaoweb.config.IRiskService;
import com.jianhaoweb.config.RiskSource;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author wangjh
 * @since 2023-05-25
 */
@Slf4j
@RestController
@RequestMapping(value = "/dataSecurityRisk")
@CrossOrigin
@Api(tags = "数据安全风险监测")
public class TblDbmsOperateLogController  {
    @Resource
    private IRiskService riskService;

    @ApiOperation(value = "重新启动新指定数据源logstash脚本")
    @RequestMapping(value = "/restartLogstashForNewDatabase", method = RequestMethod.POST)
    public void restartLogstashForNewDatabase(@RequestBody RiskSource riskSource) {


            Object a = riskService.restartLogstashForNewDatabase(riskSource);

    }

}
