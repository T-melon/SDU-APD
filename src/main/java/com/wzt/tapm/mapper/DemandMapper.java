package com.wzt.tapm.mapper;

import com.wzt.tapm.entity.DemandBean;
import com.wzt.tapm.entity.LogBean;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DemandMapper {
    int insertDemand(DemandBean demandBean);
    List<DemandBean> select1DoingDemand(String username);
    List<DemandBean> select1DoneDemand(String username);
    List<DemandBean> select2DoingDemand(String username);
    List<DemandBean> select2DoneDemand(String username);

    int select1Num1(String username);
    int select2Num1(String username);
    int select3Num1(String username);
    int select4Num1(String username);
    int select5Num1(String username);
    int select6Num1(String username);

    int select1Num2(String username);
    int select2Num2(String username);
    int select3Num2(String username);
    int select4Num2(String username);
    int select5Num2(String username);
    int select6Num2(String username);

    int updateAddress(String address, String demand_id);

    int updateDocu(String docu, String demand_id);

    String selectDocu(String demand_id);

    int updateStatus(int status, int demand_id);
    int insertLog(LogBean logBean);

    String selectProject(String demand_id);

    int selectIdentity(String username);

}

