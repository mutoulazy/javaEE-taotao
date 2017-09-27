package com.taotao.service;

import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

/**
 * 图片服务类
 * Created by mutoulazy on 2017/9/26.
 */
public interface PictureService {
    Map uploadPicture(MultipartFile uploadFile);
}
