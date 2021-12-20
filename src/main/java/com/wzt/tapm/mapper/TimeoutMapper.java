package com.wzt.tapm.mapper;

import com.wzt.tapm.entity.TimeOutBean;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface TimeoutMapper {

    List<TimeOutBean> selectDemandTimeAndStatus();

}
