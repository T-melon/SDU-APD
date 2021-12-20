package com.wzt.tapm.service;

import com.auth0.jwt.JWT;
import com.wzt.tapm.entity.DemandBean;
import com.wzt.tapm.entity.LogBean;
import com.wzt.tapm.mapper.DemandMapper;
import com.wzt.tapm.util.Result;
import com.wzt.tapm.util.ResultCodeEnum;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;

import static com.wzt.tapm.interceptor.AuthenticationInterceptor.token;

/**
 * Basic demand methods(createDemand, getDoingDemand, getDoneDemand, getDemandNum, setAddress, setDocu, getDocu)
 */
@Service
public class DemandService {

    @Resource
    private final DemandMapper demandMapper;

    public DemandService(DemandMapper demandMapper) {
        this.demandMapper = demandMapper;
    }

    public Result createDemand(DemandBean demandBean){

        Result result;

        String title = demandBean.getTitle();
        String project = demandBean.getProject();
        String ddl = demandBean.getDdl();
        String doer = demandBean.getDoer();

        if(title == null||project == null||ddl == null||doer == null){
            result = Result.getResult(ResultCodeEnum.DEMAND_LACK);
        }else{

            demandBean.setCtime(new Date().toString());
            demandBean.setStatus(1);

            if(token != null){
                demandBean.setCer(JWT.decode(token).getAudience().get(0));

                int num = demandMapper.insertDemand(demandBean);

                if(num>0){
                    result = Result.getResult(ResultCodeEnum.SUCCESS);
                }else{
                    result = Result.getResult(ResultCodeEnum.UNKNOWN_REASON);
                }

            }else{
                result = Result.getResult(ResultCodeEnum.FETCH_TOKEN_FAILED);
            }

        }
        return result;

    }

    public Result getDoingDemand(){

        Result result;
        int identity = demandMapper.selectIdentity(JWT.decode(token).getAudience().get(0));
        List<DemandBean> list;
        if(identity == 1){
            list = demandMapper.select1DoingDemand(JWT.decode(token).getAudience().get(0));
        }else{
            list = demandMapper.select2DoingDemand(JWT.decode(token).getAudience().get(0));
        }
        result = Result.getResult(ResultCodeEnum.SUCCESS,list);

        return result;

    }

    public Result getDoneDemand(){

        Result result;

        int identity = demandMapper.selectIdentity(JWT.decode(token).getAudience().get(0));
        List<DemandBean> list;
        if(identity == 1){
            list = demandMapper.select1DoneDemand(JWT.decode(token).getAudience().get(0));
        }else{
            list = demandMapper.select2DoneDemand(JWT.decode(token).getAudience().get(0));
        }
        result = Result.getResult(ResultCodeEnum.SUCCESS,list);

        return result;

    }

    public Result getDemandNum(){
        Result result;

        int identity = demandMapper.selectIdentity(JWT.decode(token).getAudience().get(0));
        if (identity == 1) {

            int i1 = demandMapper.select1Num1(JWT.decode(token).getAudience().get(0));
            int i2 = demandMapper.select2Num1(JWT.decode(token).getAudience().get(0));
            int i3 = demandMapper.select3Num1(JWT.decode(token).getAudience().get(0));
            int i4 = demandMapper.select4Num1(JWT.decode(token).getAudience().get(0));
            int i5 = demandMapper.select5Num1(JWT.decode(token).getAudience().get(0));
            int i6 = demandMapper.select6Num1(JWT.decode(token).getAudience().get(0));
//        List<Integer> list = new List<>() {
//            @Override
//            public int size() {
//                return 0;
//            }
//
//            @Override
//            public boolean isEmpty() {
//                return false;
//            }
//
//            @Override
//            public boolean contains(Object o) {
//                return false;
//            }
//
//            @Override
//            public Iterator<Integer> iterator() {
//                return null;
//            }
//
//            @Override
//            public Object[] toArray() {
//                return new Object[0];
//            }
//
//            @Override
//            public <T> T[] toArray(T[] a) {
//                return null;
//            }
//
//            @Override
//            public boolean add(Integer integer) {
//                return false;
//            }
//
//            @Override
//            public boolean remove(Object o) {
//                return false;
//            }
//
//            @Override
//            public boolean containsAll(Collection<?> c) {
//                return false;
//            }
//
//            @Override
//            public boolean addAll(Collection<? extends Integer> c) {
//                return false;
//            }
//
//            @Override
//            public boolean addAll(int index, Collection<? extends Integer> c) {
//                return false;
//            }
//
//            @Override
//            public boolean removeAll(Collection<?> c) {
//                return false;
//            }
//
//            @Override
//            public boolean retainAll(Collection<?> c) {
//                return false;
//            }
//
//            @Override
//            public void clear() {
//
//            }
//
//            @Override
//            public Integer get(int index) {
//                return null;
//            }
//
//            @Override
//            public Integer set(int index, Integer element) {
//                return null;
//            }
//
//            @Override
//            public void add(int index, Integer element) {
//
//            }
//
//            @Override
//            public Integer remove(int index) {
//                return null;
//            }
//
//            @Override
//            public int indexOf(Object o) {
//                return 0;
//            }
//
//            @Override
//            public int lastIndexOf(Object o) {
//                return 0;
//            }
//
//            @Override
//            public ListIterator<Integer> listIterator() {
//                return null;
//            }
//
//            @Override
//            public ListIterator<Integer> listIterator(int index) {
//                return null;
//            }
//
//            @Override
//            public List<Integer> subList(int fromIndex, int toIndex) {
//                return null;
//            }
//        };
//        list.add(0,i1);
//        list.add(1,i2);
//        list.add(2,i3);
//        list.add(3,i4);
//        list.add(4,i5);
//        list.add(5,i6);

//        采用List报错org.springframework.http.converter.HttpMessageNotWritableException:
//        Could not write JSON: (was java.lang.NullPointerException); nested exception is com.fasterxml.jackson.databind.
//        data返回暂时改成String
            String str = "i1=" + i1 + ", i2=" + i2 + ", i3=" + i3 + ", i4=" + i4 + ", i5=" + i5 + ", i6=" + i6;

            result = Result.getResult(ResultCodeEnum.SUCCESS, str);

        }else{

            int i1 = demandMapper.select1Num2(JWT.decode(token).getAudience().get(0));
            int i2 = demandMapper.select2Num2(JWT.decode(token).getAudience().get(0));
            int i3 = demandMapper.select3Num2(JWT.decode(token).getAudience().get(0));
            int i4 = demandMapper.select4Num2(JWT.decode(token).getAudience().get(0));
            int i5 = demandMapper.select5Num2(JWT.decode(token).getAudience().get(0));
            int i6 = demandMapper.select6Num2(JWT.decode(token).getAudience().get(0));
//        List<Integer> list = new List<>() {
//            @Override
//            public int size() {
//                return 0;
//            }
//
//            @Override
//            public boolean isEmpty() {
//                return false;
//            }
//
//            @Override
//            public boolean contains(Object o) {
//                return false;
//            }
//
//            @Override
//            public Iterator<Integer> iterator() {
//                return null;
//            }
//
//            @Override
//            public Object[] toArray() {
//                return new Object[0];
//            }
//
//            @Override
//            public <T> T[] toArray(T[] a) {
//                return null;
//            }
//
//            @Override
//            public boolean add(Integer integer) {
//                return false;
//            }
//
//            @Override
//            public boolean remove(Object o) {
//                return false;
//            }
//
//            @Override
//            public boolean containsAll(Collection<?> c) {
//                return false;
//            }
//
//            @Override
//            public boolean addAll(Collection<? extends Integer> c) {
//                return false;
//            }
//
//            @Override
//            public boolean addAll(int index, Collection<? extends Integer> c) {
//                return false;
//            }
//
//            @Override
//            public boolean removeAll(Collection<?> c) {
//                return false;
//            }
//
//            @Override
//            public boolean retainAll(Collection<?> c) {
//                return false;
//            }
//
//            @Override
//            public void clear() {
//
//            }
//
//            @Override
//            public Integer get(int index) {
//                return null;
//            }
//
//            @Override
//            public Integer set(int index, Integer element) {
//                return null;
//            }
//
//            @Override
//            public void add(int index, Integer element) {
//
//            }
//
//            @Override
//            public Integer remove(int index) {
//                return null;
//            }
//
//            @Override
//            public int indexOf(Object o) {
//                return 0;
//            }
//
//            @Override
//            public int lastIndexOf(Object o) {
//                return 0;
//            }
//
//            @Override
//            public ListIterator<Integer> listIterator() {
//                return null;
//            }
//
//            @Override
//            public ListIterator<Integer> listIterator(int index) {
//                return null;
//            }
//
//            @Override
//            public List<Integer> subList(int fromIndex, int toIndex) {
//                return null;
//            }
//        };
//        list.add(0,i1);
//        list.add(1,i2);
//        list.add(2,i3);
//        list.add(3,i4);
//        list.add(4,i5);
//        list.add(5,i6);

//        采用List报错org.springframework.http.converter.HttpMessageNotWritableException:
//        Could not write JSON: (was java.lang.NullPointerException); nested exception is com.fasterxml.jackson.databind.
//        data返回暂时改成String
            String str = "i1=" + i1 + ", i2=" + i2 + ", i3=" + i3 + ", i4=" + i4 + ", i5=" + i5 + ", i6=" + i6;

            result = Result.getResult(ResultCodeEnum.SUCCESS, str);

        }

        return result;
    }

    public Result setAddress(String commit, String address, String demand_id){

        Result result;

        LogBean logBean = new LogBean();
        logBean.setCer(JWT.decode(token).getAudience().get(0));
        logBean.setCtime(new Date().toString());
        logBean.setCommit(commit);
        logBean.setDemand_id(Integer.parseInt(demand_id));
        logBean.setProject(demandMapper.selectProject(demand_id));

        int num1 = demandMapper.updateAddress(address, demand_id);
        int num2 = demandMapper.insertLog(logBean);

        if(num1>0 && num2>0){
            result = Result.getResult(ResultCodeEnum.SUCCESS);
        }else{
            result = Result.getResult(ResultCodeEnum.UNKNOWN_REASON);
        }

        return result;

    }

    public Result setDocu(String docu, String demand_id){

        Result result;

        int num = demandMapper.updateDocu(docu, demand_id);

        if(num>0){
            result = Result.getResult(ResultCodeEnum.SUCCESS,docu);
        }else{
            result = Result.getResult(ResultCodeEnum.UNKNOWN_REASON);
        }

        return result;

    }

    public Result getDocu(String demand_id){

        Result result;
        String docu = demandMapper.selectDocu(demand_id);
        result = Result.getResult(ResultCodeEnum.SUCCESS,docu);
        return result;
    }

    public Result from1to2(String commit,String demand_id){

        Result result;
        LogBean logBean = new LogBean();
        logBean.setCer(JWT.decode(token).getAudience().get(0));
        logBean.setCtime(new Date().toString());
        logBean.setCommit(commit);
        logBean.setDemand_id(Integer.parseInt(demand_id));
        logBean.setProject(demandMapper.selectProject(demand_id));

        int num1 = demandMapper.updateStatus(2, Integer.parseInt(demand_id));
        int num2 = demandMapper.insertLog(logBean);

        if(num1>0 && num2>0){
            result = Result.getResult(ResultCodeEnum.SUCCESS);
        }else{
            result = Result.getResult(ResultCodeEnum.UNKNOWN_REASON);
        }
        return result;
    }

    public Result from1to3(String commit,String demand_id){

        Result result;
        LogBean logBean = new LogBean();
        logBean.setCer(JWT.decode(token).getAudience().get(0));
        logBean.setCtime(new Date().toString());
        logBean.setCommit(commit);
        logBean.setDemand_id(Integer.parseInt(demand_id));
        logBean.setProject(demandMapper.selectProject(demand_id));

        int num1 = demandMapper.updateStatus(3, Integer.parseInt(demand_id));
        int num2 = demandMapper.insertLog(logBean);

        if(num1>0 && num2>0){
            result = Result.getResult(ResultCodeEnum.SUCCESS);
        }else{
            result = Result.getResult(ResultCodeEnum.UNKNOWN_REASON);
        }
        return result;
    }

    public Result from2to4(String commit,String demand_id){

        Result result;
        LogBean logBean = new LogBean();
        logBean.setCer(JWT.decode(token).getAudience().get(0));
        logBean.setCtime(new Date().toString());
        logBean.setCommit(commit);
        logBean.setDemand_id(Integer.parseInt(demand_id));
        logBean.setProject(demandMapper.selectProject(demand_id));

        int num1 = demandMapper.updateStatus(4, Integer.parseInt(demand_id));
        int num2 = demandMapper.insertLog(logBean);

        if(num1>0 && num2>0){
            result = Result.getResult(ResultCodeEnum.SUCCESS);
        }else{
            result = Result.getResult(ResultCodeEnum.UNKNOWN_REASON);
        }
        return result;
    }

    public Result from2to5(String commit,String demand_id){

        Result result;
        LogBean logBean = new LogBean();
        logBean.setCer(JWT.decode(token).getAudience().get(0));
        logBean.setCtime(new Date().toString());
        logBean.setCommit(commit);
        logBean.setDemand_id(Integer.parseInt(demand_id));
        logBean.setProject(demandMapper.selectProject(demand_id));

        int num1 = demandMapper.updateStatus(5, Integer.parseInt(demand_id));
        int num2 = demandMapper.insertLog(logBean);

        if(num1>0 && num2>0){
            result = Result.getResult(ResultCodeEnum.SUCCESS);
        }else{
            result = Result.getResult(ResultCodeEnum.UNKNOWN_REASON);
        }
        return result;
    }

    public Result from2to6(String commit,String demand_id){

        Result result;
        LogBean logBean = new LogBean();
        logBean.setCer(JWT.decode(token).getAudience().get(0));
        logBean.setCtime(new Date().toString());
        logBean.setCommit(commit);
        logBean.setDemand_id(Integer.parseInt(demand_id));
        logBean.setProject(demandMapper.selectProject(demand_id));

        int num1 = demandMapper.updateStatus(6, Integer.parseInt(demand_id));
        int num2 = demandMapper.insertLog(logBean);

        if(num1>0 && num2>0){
            result = Result.getResult(ResultCodeEnum.SUCCESS);
        }else{
            result = Result.getResult(ResultCodeEnum.UNKNOWN_REASON);
        }
        return result;
    }

    public Result from4to5(String commit,String demand_id){

        Result result;
        LogBean logBean = new LogBean();
        logBean.setCer(JWT.decode(token).getAudience().get(0));
        logBean.setCtime(new Date().toString());
        logBean.setCommit(commit);
        logBean.setDemand_id(Integer.parseInt(demand_id));
        logBean.setProject(demandMapper.selectProject(demand_id));

        int num1 = demandMapper.updateStatus(5, Integer.parseInt(demand_id));
        int num2 = demandMapper.insertLog(logBean);

        if(num1>0 && num2>0){
            result = Result.getResult(ResultCodeEnum.SUCCESS);
        }else{
            result = Result.getResult(ResultCodeEnum.UNKNOWN_REASON);
        }
        return result;
    }

    public Result from4to6(String commit,String demand_id){

        Result result;
        LogBean logBean = new LogBean();
        logBean.setCer(JWT.decode(token).getAudience().get(0));
        logBean.setCtime(new Date().toString());
        logBean.setCommit(commit);
        logBean.setDemand_id(Integer.parseInt(demand_id));
        logBean.setProject(demandMapper.selectProject(demand_id));

        int num1 = demandMapper.updateStatus(6, Integer.parseInt(demand_id));
        int num2 = demandMapper.insertLog(logBean);

        if(num1>0 && num2>0){
            result = Result.getResult(ResultCodeEnum.SUCCESS);
        }else{
            result = Result.getResult(ResultCodeEnum.UNKNOWN_REASON);
        }
        return result;
    }

}
