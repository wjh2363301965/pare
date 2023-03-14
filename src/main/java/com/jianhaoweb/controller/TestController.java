package com.jianhaoweb.controller;

import com.jianhaoweb.entity.Bear;

import com.jianhaoweb.mapper.BearMapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Auther:剑豪
 * @Date:2023/3/13
 * @Description:controller
 * @VERSON:1.8
 */
@RestController
@Api(tags = "测试" )
public class TestController {

    @Autowired
    BearMapper bearMapper;

    @GetMapping("/test")
    @ApiOperation("test测试")
    public Object test() {
        List<Bear> tt = bearMapper.getAllBears();
        return tt;
    }



}
