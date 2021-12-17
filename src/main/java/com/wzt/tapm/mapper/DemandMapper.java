package com.wzt.tapm.mapper;

import com.wzt.tapm.entity.DemandBean;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DemandMapper {
    int insertDemand(DemandBean demandBean);
    List<DemandBean> selectDoingDemand(String username);
    List<DemandBean> selectDoneDemand(String username);

    int select1Num(String username);
    int select2Num(String username);
    int select3Num(String username);
    int select4Num(String username);
    int select5Num(String username);
    int select6Num(String username);

    int updateAddress(String address, String demand_id);

    int updateDocu(String docu, String demand_id);

    String selectDocu(String demand_id);
}

