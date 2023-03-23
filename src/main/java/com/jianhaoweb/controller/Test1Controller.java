package com.jianhaoweb.controller;

import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.jianhaoweb.entity.Bear;
import com.jianhaoweb.entity.Test1;
import com.jianhaoweb.mapper.Test1Mapper;
import com.jianhaoweb.service.Test1Service;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
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
@Api(tags = "本地mysql的controller" )
@RequestMapping("/mysql")
public class Test1Controller {

    @Autowired
    Test1Service test1Service;

/*    @GetMapping("/test")
    public Object test() {
        List<Test1> tt = test1Mapper.getjson();
        System.out.println(tt);
        test1Mapper.save(tt.get(0).getJson());

        return tt;
    }*/

    @GetMapping("/insert10000")
    public Object insert10000() {
        test1Service.insert10000();
        return null;
    }

}
