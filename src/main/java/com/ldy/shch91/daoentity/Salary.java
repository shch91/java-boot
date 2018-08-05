package com.ldy.shch91.daoentity;

import lombok.Data;
import java.util.Date;

@Data
public class Salary {
    private  int empNo;
    private  int salary;
    private Date fromDate;
    private Date toDate;
}
