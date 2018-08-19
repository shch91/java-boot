package com.ldy.shch91.mapper.employees;

import com.ldy.shch91.daoentity.Salary;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface TmpMapper {
        int  add(Salary salary);

        int  addBatch(List<Salary> salarys);

        Salary getByEmpNo(String empNo);

        List<String> allEmpNo();

        List<Salary> getAll();

}
