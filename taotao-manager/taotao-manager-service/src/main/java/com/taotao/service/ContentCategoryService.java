package com.taotao.service;

import com.taotao.common.pojo.TaotaoResult;
import com.taotao.common.pojo.TreeNode;

import java.util.List;

/**
 * CMS内容分类管理
 * Created by mutoulazy on 2017/10/9.
 */
public interface ContentCategoryService {
    /**
     * 根据parentId获取分类列表
     * @param parentId
     * @return
     */
    List<TreeNode> getCategoryList(long parentId);

    /**
     * 内容分类添加
     * @param parentId
     * @param name
     * @return
     */
    TaotaoResult insertContentCategory(long parentId, String name);

    /**
     * 内容分类名称修改
     * @param id
     * @param name
     * @return
     */
    TaotaoResult updateContentCategory(long id, String name);

    /**
     * 内容分类删除
     * @param id
     * @return
     */
    TaotaoResult deleteContentCategory(long id);
}
