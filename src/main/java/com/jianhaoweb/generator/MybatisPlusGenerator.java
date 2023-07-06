package com.jianhaoweb.generator;

import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * 持久层代码生成器
 */
public class MybatisPlusGenerator {
    private static String url = "jdbc:mariadb://172.20.52.135:3066/dscg?useUnicode=true&characterEncoding=UTF-8";
    private static String username = "dscg";
    private static String password = "sailing2018!";
    //代码生成位置，每个人代码位置不一样需要修改
    private static String outputDir = "D:\\ideaproject\\generator";

    public static void main(String[] args) {
        FastAutoGenerator.create(url,username,password)
                // 全局配置
                .globalConfig((scanner, builder) -> builder.author(scanner.apply("请输入作者名称？")).fileOverride()
                        .outputDir(outputDir)
                        .enableSwagger()
                )
                // 包配置
                .packageConfig((scanner, builder) -> builder.parent("com.sailing"))
                // 策略配置
                .strategyConfig((scanner, builder) -> builder.addInclude(getTables(scanner.apply("请输入表名，多个英文逗号分隔？所有输入 all")))
                        .controllerBuilder().enableRestStyle().enableHyphenStyle()
                        .entityBuilder().enableTableFieldAnnotation().enableLombok().build()
                )
                .templateEngine(new FreemarkerTemplateEngine())
                .execute();
    }
    // 处理 all 情况
    protected static List<String> getTables(String tables) {
        return "all".equals(tables) ? Collections.emptyList() : Arrays.asList(tables.split(","));
    }

}
