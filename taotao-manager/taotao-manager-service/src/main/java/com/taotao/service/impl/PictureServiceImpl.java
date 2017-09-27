package com.taotao.service.impl;

import com.taotao.common.utils.FtpUtil;
import com.taotao.common.utils.IDUtils;
import com.taotao.service.PictureService;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.Map;

/**
 * 图片上传服务
 * Created by mutoulazy on 2017/9/26.
 */
@Service
public class PictureServiceImpl implements PictureService{
    @Value("${FTP_ADDRESS}")
    private String FTP_ADDRESS;
    @Value("${FTP_PORT}")
    private Integer FTP_PORT;
    @Value("${FTP_USERNAME}")
    private String FTP_USERNAME;
    @Value("${FTP_PASSWORD}")
    private String FTP_PASSWORD;
    @Value("${FTP_BASEPATH}")
    private String FTP_BASEPATH;
    @Value("${IMAGE_BASEURL}")
    private String IMAGE_BASEURL;


    @Override
    public Map uploadPicture(MultipartFile uploadFile){
        Map resuleMap = new HashMap();
        try {
            //生成一个新的文件名，获取原文件的扩展名
            String oldName = uploadFile.getOriginalFilename();
            String newName = IDUtils.genImageName();
            newName = newName + oldName.substring(oldName.lastIndexOf("."));
            //图片上传
            String imagePath = new DateTime().toString("/yyyy/MM/dd");
            boolean resule = FtpUtil.uploadFile(FTP_ADDRESS,FTP_PORT,FTP_USERNAME,FTP_PASSWORD,FTP_BASEPATH,imagePath,
                    newName,uploadFile.getInputStream());
            //返回结果URL
            if (!resule){
                resuleMap.put("error",1);
                resuleMap.put("message","文件上传失败");
                return resuleMap;
            }
            resuleMap.put("error",0);
            resuleMap.put("url",IMAGE_BASEURL + imagePath + "/" + newName);
            return resuleMap;
        }catch (Exception e){
            resuleMap.put("error",1);
            resuleMap.put("message","文件上传异常"+e.getStackTrace());
            e.printStackTrace();
            return resuleMap;
        }
    }
}
