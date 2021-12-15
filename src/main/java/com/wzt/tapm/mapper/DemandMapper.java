package com.wzt.tapm.mapper;

import com.wzt.tapm.entity.DemandBean;
import org.springframework.stereotype.Repository;

@Repository
public interface DemandMapper {
    int insertDemand(DemandBean demandBean);
}
