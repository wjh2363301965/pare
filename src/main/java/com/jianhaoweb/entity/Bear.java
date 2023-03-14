package com.jianhaoweb.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * @Auther:剑豪
 * @Date:2023/3/13
 * @VERSON:1.8
 */
@Data
@NoArgsConstructor
@ToString
public class Bear {

    private String name;
    private String type;
    private String weight;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public Bear(String name, String type, String weight) {
        this.name = name;
        this.type = type;
        this.weight = weight;
    }
}
