package com.taotao.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.taotao.common.utils.JsonUtils;
import com.taotao.service.PictureService;

/**
 * 图片上传管理类
 * Created by mutoulazy on 2017/9/26.
 */
@Controller
public class PictureController {
    @Autowired
    private PictureService pictureService;

    @RequestMapping("/pic/upload")
    @ResponseBody
    public String upload(MultipartFile uploadFile) {
        Map result = pictureService.uploadPicture(uploadFile);
        //为了保持插件的兼容性，需要把Result转换层json格式的字符串。
        String jsonString = JsonUtils.objectToJson(result);
        return jsonString;
    }

}
