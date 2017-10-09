package com.taotao.service.impl;

import com.taotao.common.pojo.TaotaoResult;
import com.taotao.common.pojo.TreeNode;
import com.taotao.mapper.TbContentCategoryMapper;
import com.taotao.pojo.TbContentCategory;
import com.taotao.pojo.TbContentCategoryExample;
import com.taotao.service.ContentCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by mutoulazy on 2017/10/9.
 */
@Service
public class ContentCategoryServiceImpl implements ContentCategoryService {
    @Autowired
    private TbContentCategoryMapper tbContentCategoryMapper;

    @Override
    public List<TreeNode> getCategoryList(long parentId) {
        //根据parrntId查询节点列表
        TbContentCategoryExample example = new TbContentCategoryExample();
        TbContentCategoryExample.Criteria criteria = example.createCriteria();
        criteria.andParentIdEqualTo(parentId);
        //执行查询
        List<TbContentCategory> list = tbContentCategoryMapper.selectByExample(example);
        List<TreeNode> resultList = new ArrayList<>();
        for (TbContentCategory tbContentCategory:list
             ) {
            //创建一个节点
            TreeNode node = new TreeNode();
            node.setId(tbContentCategory.getId());
            node.setText(tbContentCategory.getName());
            node.setState(tbContentCategory.getIsParent()?"closed":"open");

            resultList.add(node);
        }
        return resultList;
    }

    @Override
    public TaotaoResult insertContentCategory(long parentId, String name) {
        //创建一个实体类
        TbContentCategory contentCategory = new TbContentCategory();
        contentCategory.setName(name);
        contentCategory.setIsParent(false);
        contentCategory.setStatus(1);//1正常 2删除
        contentCategory.setParentId(parentId);
        contentCategory.setSortOrder(1);
        contentCategory.setCreated(new Date());
        contentCategory.setUpdated(new Date());
        //添加记录
        tbContentCategoryMapper.insert(contentCategory);
        //判断添加节点的父节点IsParent是否为true,如果不是true修改为true。
        TbContentCategory parentCat = tbContentCategoryMapper.selectByPrimaryKey(parentId);
        if(!parentCat.getIsParent()){
            parentCat.setIsParent(true);
            //更新节点
            tbContentCategoryMapper.updateByPrimaryKey(parentCat);
        }
        return TaotaoResult.ok(contentCategory);
    }

    @Override
    public TaotaoResult updateContentCategory(long id, String name) {
        TbContentCategory contentCategory = tbContentCategoryMapper.selectByPrimaryKey(id);
        System.out.println("contentCategory" + contentCategory.getName());
        contentCategory.setName(name);
        //更新节点
        tbContentCategoryMapper.updateByPrimaryKey(contentCategory);
        return TaotaoResult.ok(contentCategory);
    }
}
