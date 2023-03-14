package com.jianhaoweb.controller;

import com.jianhaoweb.entity.Bear;
import com.jianhaoweb.entity.Test1;
import com.jianhaoweb.mapper.Test1Mapper;
import com.jianhaoweb.service.Test1Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author wangjianhao
 * @since 2023-03-14
 */
@RestController
@RequestMapping("/jianhaoweb/test1")
public class Test1Controller {

    @Autowired
    Test1Mapper test1Mapper;

    @RequestMapping("/test2")
    public Object test() {
        List<Test1> tt = test1Mapper.getjson();
        System.out.println(tt);
        return tt;
    }

}
