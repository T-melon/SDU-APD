package com.wzt.tapm.mapper;

import com.wzt.tapm.entity.LogBean;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LogMapper {
    List<LogBean> selectAllLog();
    List<LogBean> selectSelfLog(String username);
    List<LogBean> selectDemandLog(String demand_id);
}
