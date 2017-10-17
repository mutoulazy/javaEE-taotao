package com.taotao.rest.service;

import com.taotao.pojo.TbContent;

import java.util.List;

/**
 * 接口服务器获取内容列表
 * Created by mutoulazy on 2017/10/13.
 */
public interface ContentService {
    List<TbContent> getContentList(long contentCid);
}
