package com.jianhaoweb.controller;

import com.jianhaoweb.service.wjh.Test1Service;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
