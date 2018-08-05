package com.ldy.shch91.mapper.employees;

import com.ldy.shch91.daoentity.Salary;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface SalaryMapper {

    public  int  add(Salary salary);

    public Salary getByEmpNo(String empNo);

    public List<String> allEmpNo();
}
