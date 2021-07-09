package com.zlw.pojo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class person {
    private int id;
    private String name;
    private int age;
    private String password;
    private String power;
}
