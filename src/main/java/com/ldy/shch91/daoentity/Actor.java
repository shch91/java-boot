package com.ldy.shch91.daoentity;


import lombok.Data;

import java.util.Date;

@Data
public class Actor {

    private  int  actorId;

    private String firstName;

    private  String lastName;

    private Date lastUpdate;

}
