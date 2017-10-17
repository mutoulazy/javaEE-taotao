package com.taotao.service;

import com.taotao.common.pojo.EUDataGridResult;
import com.taotao.common.pojo.TaotaoResult;
import com.taotao.pojo.TbContent;

/**
 * 内容服务类
 * Created by mutoulazy on 2017/10/13.
 */
public interface ContentService {
    /**
     * 根据categoryId获取content列表
     * @param page
     * @param rows
     * @param categoryId
     * @return
     */
    EUDataGridResult getContentList(int page,int rows,long categoryId);

    /**
     * 插入Content数据
     * @param content
     * @return
     */
    TaotaoResult insertContent(TbContent content);
}
