package com.jianhaoweb.config;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public enum LevelEnum {

    CORE_DATA(1,"核心数据"),
    IMPORTANT_DATA(2,"重要数据"),
    GENERAL_DATA_4(3,"一般数据4级"),
    GENERAL_DATA_3(4,"一般数据3级"),
    GENERAL_DATA_2(5,"一般数据2级"),
    GENERAL_DATA_1(6,"一般数据1级");
    private Integer value;
    private String name;

    private static List<String> names = new ArrayList<>();

    static{
        for (LevelEnum value : LevelEnum.values()) {
            names.add(value.getName());
        }
    }

    public static List<String> getNames(){
        return names;
    }


}
