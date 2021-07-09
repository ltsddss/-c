package com.zlw.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Relation {
    private int id;//关系id
    private String mid;//自己的username
    private String sid;//对方的username
    private String relation;//关系名称
}
