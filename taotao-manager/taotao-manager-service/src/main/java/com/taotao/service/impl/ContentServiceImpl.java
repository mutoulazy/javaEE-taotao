package com.taotao.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.taotao.common.pojo.EUDataGridResult;
import com.taotao.common.pojo.TaotaoResult;
import com.taotao.mapper.TbContentMapper;
import com.taotao.pojo.TbContent;
import com.taotao.pojo.TbContentExample;
import com.taotao.service.ContentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * Created by mutoulazy on 2017/10/13.
 */
@Service
public class ContentServiceImpl implements ContentService{
    @Autowired
    private TbContentMapper contentMapper;

    @Override
    public EUDataGridResult getContentList(int page, int rows, long categoryId) {
        //根据分类Id查询内容列表
        TbContentExample example = new TbContentExample();
        TbContentExample.Criteria criteria = example.createCriteria();
        criteria.andCategoryIdEqualTo(categoryId);
        PageHelper.startPage(page, rows);// 添加分页条件
        List<TbContent> list = contentMapper.selectByExample(example);
        PageInfo<TbContent> pageInfo = new PageInfo<TbContent>(list);
        //构造返回的数据类型
        EUDataGridResult euDataGridResult = new EUDataGridResult();
        euDataGridResult.setRows(list);
        euDataGridResult.setTotal(pageInfo.getTotal());
        return euDataGridResult;
    }

    @Override
    public TaotaoResult insertContent(TbContent content) {
        //补全实体类对象的属性
        content.setUpdated(new Date());
        content.setCreated(new Date());

        //插入数据
        contentMapper.insert(content);

        return TaotaoResult.ok();
    }
}
