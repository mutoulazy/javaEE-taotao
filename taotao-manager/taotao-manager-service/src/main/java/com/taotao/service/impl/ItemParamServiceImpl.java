package com.taotao.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.taotao.common.pojo.EUDataGridResult;
import com.taotao.common.pojo.TaotaoResult;
import com.taotao.mapper.TbItemParamMapper;
import com.taotao.pojo.TbItemParam;
import com.taotao.pojo.TbItemParamExample;
import com.taotao.service.ItemParamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * Created by mutoulazy on 2017/9/30.
 */
@Service
public class ItemParamServiceImpl implements ItemParamService{
    @Autowired
    private TbItemParamMapper tbItemParamMapper;

    /**
     * 规格列表查询
     * @param page
     * @param rows
     * @return
     */
    @Override
    public EUDataGridResult getItemParamList(int page, int rows) {
        //查询商品项目列表
        TbItemParamExample example = new TbItemParamExample();
        PageHelper.startPage(page, rows);// 添加分页条件
        List<TbItemParam> list = tbItemParamMapper.selectByExampleWithBLOBs(example);
        PageInfo<TbItemParam> pageInfo = new PageInfo<>(list);
        //构造返回的数据类型
        EUDataGridResult euDataGridResult = new EUDataGridResult();
        euDataGridResult.setRows(list);
        euDataGridResult.setTotal(pageInfo.getTotal());
        return euDataGridResult;
    }

    /**
     * 根据商品类别id获取商品规格类型是否存在
     * @param itemCatId
     * @return
     */
    @Override
    public TaotaoResult getItemParamByCId(long itemCatId) {
        TbItemParamExample example = new TbItemParamExample();
        TbItemParamExample.Criteria criteria = example.createCriteria();
        criteria.andItemCatIdEqualTo(itemCatId);
        List<TbItemParam> list = tbItemParamMapper.selectByExampleWithBLOBs(example);
        if (list.size() >0 || list != null){
            return TaotaoResult.ok(list.get(0));
        }
        return TaotaoResult.ok();
    }

    /**
     * 添加商品规格信息
     * @param tbItemParam
     * @return
     */
    @Override
    public TaotaoResult insertItamParam(TbItemParam tbItemParam) {
        //补全商品规格pojo中的参数
        tbItemParam.setCreated(new Date());
        tbItemParam.setUpdated(new Date());

        //插入参数
        //insert方法是使用所有的属性作为字段使用
        //insertSelective插入数据，使用不为null的属性作为字段使用
        tbItemParamMapper.insert(tbItemParam);
        return TaotaoResult.ok();
    }
}
