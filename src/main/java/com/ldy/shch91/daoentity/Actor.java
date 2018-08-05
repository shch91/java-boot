package com.ldy.shch91.daoentity;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

import java.util.Date;

@Data
public class Actor {

    private  int  actorId;

    private String firstName;

    private  String lastName;

    //@JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date lastUpdate;

}
