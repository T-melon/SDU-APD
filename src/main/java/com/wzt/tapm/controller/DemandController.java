package com.wzt.tapm.controller;

import com.wzt.tapm.entity.DemandBean;
import com.wzt.tapm.service.DemandService;
import com.wzt.tapm.util.Result;
import com.wzt.tapm.util.annotation.UserLoginToken;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.apache.http.entity.ContentType;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

/**
 * /demand/create, /demand/doing, /demand/done, /demand/num, /demand/github, /demand/upload, /demand/download...
 */
@RestController
public class DemandController {

    @Resource
    private final DemandService demandService;

    public DemandController(DemandService demandService) {
        this.demandService = demandService;
    }

    @UserLoginToken
    @PostMapping("/demand/create")
    public Result createDemand(DemandBean demandBean) {

        return demandService.createDemand(demandBean);
    }

//    public static String token;
//
//    @UserLoginToken
//    @GetMapping("/getToken")
//    public void fetchToken(@RequestHeader("token") String Token) {
//        token = Token;
//    }

    @UserLoginToken
    @PostMapping("/demand/doing")
    public Result getDoingDemand() {
        return demandService.getDoingDemand();
    }

    @UserLoginToken
    @PostMapping("/demand/done")
    public Result getDoneDemand() {
        return demandService.getDoneDemand();
    }

    @UserLoginToken
    @PostMapping("/demand/num")
    public Result getDemandNum() {
        return demandService.getDemandNum();
    }

    @UserLoginToken
    @PostMapping("/demand/github")
    public Result setAddress(String commit, String address, String demand_id) {
        return demandService.setAddress(commit, address, demand_id);
    }

    @UserLoginToken
    @PostMapping("/demand/upload")
    public Result setDocu(@RequestParam("uploadFile") File uploadFile, @RequestParam("demand_id") String demand_id, HttpServletRequest request) throws IOException {

        File file = new File(String.valueOf(uploadFile));
        FileInputStream fileInputStream = new FileInputStream(file);
        MultipartFile multipartFile = new MockMultipartFile("copy"+file.getName(),file.getName(),ContentType.APPLICATION_OCTET_STREAM.toString(),fileInputStream);


        //定义上传文件存放的路径
        String path = request.getSession().getServletContext().getRealPath("/uploadFile/");//此处为tomcat下的路径,服务重启路径会变化
        //定义文件在上传路径中的文件夹名称
        File folder = new File(path + demand_id);
        //检测folder是否是文件夹,不是就创建
        if (!folder.isDirectory()) {
            folder.mkdirs();
//            不知道应该怎么去掉这个警告
            try {
                boolean folderCreated = folder.mkdirs();
                // if dir wasn't created this time, and doesn't exist
                if (!folderCreated && !folder.exists()) {
                    throw new IOException("Unable to create path");
                }
            } catch (SecurityException e) {
                throw new IOException("Unable to create path", e);
            }
        }
        //获取文件的原始名称
        String oldName = multipartFile.getOriginalFilename();
        //oldName.substring(oldName.lastIndexOf("."))获取文件的后缀名
        //生成新的文件名(下面根据自己需要决定是否使用)
        //String newName ="定义新名字" + oldName.substring(oldName.lastIndexOf("."));
        //文件保存操作
        if (oldName != null)
            multipartFile.transferTo(new File(folder, oldName));
        //返回保存的url,根据url可以进行文件查看或者下载
        String filePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + "/uploadFile/" + demand_id + "/" + oldName;

        return demandService.setDocu(filePath,demand_id);
    }

    @UserLoginToken
    @PostMapping("/demand/download")
    public Result getDocu(String demand_id) {
        return demandService.getDocu(demand_id);
    }

    @UserLoginToken
    @PostMapping("/demand/from1to2")
    public Result from1to2(String commit,String demand_id) {
        return demandService.from1to2(commit,demand_id);
    }

    @UserLoginToken
    @PostMapping("/demand/from1to3")
    public Result from1to3(String commit,String demand_id) {
        return demandService.from1to3(commit,demand_id);
    }

    @UserLoginToken
    @PostMapping("/demand/from2to4")
    public Result from2to4(String commit,String demand_id) {
        return demandService.from2to4(commit,demand_id);
    }

    @UserLoginToken
    @PostMapping("/demand/from2to5")
    public Result from2to5(String commit,String demand_id) {
        return demandService.from2to5(commit,demand_id);
    }

    @UserLoginToken
    @PostMapping("/demand/from2to6")
    public Result from2to6(String commit,String demand_id) {
        return demandService.from2to6(commit,demand_id);
    }

    @UserLoginToken
    @PostMapping("/demand/from4to5")
    public Result from4to5(String commit,String demand_id) {
        return demandService.from4to5(commit,demand_id);
    }

    @UserLoginToken
    @PostMapping("/demand/from4to6")
    public Result from4to6(String commit,String demand_id) {
        return demandService.from4to6(commit,demand_id);
    }
}
