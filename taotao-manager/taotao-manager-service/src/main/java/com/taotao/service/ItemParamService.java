package com.taotao.service;

import com.taotao.common.pojo.EUDataGridResult;
import com.taotao.common.pojo.TaotaoResult;
import com.taotao.pojo.TbItemParam;

/**
 * 商品规格管理类
 * Created by mutoulazy on 2017/9/30.
 */

public interface ItemParamService {

    // 获取商品规格的分页信息
    EUDataGridResult getItemParamList(int page, int rows);

    //根据id获取商品规格信息
    TaotaoResult getItemParamByCId(long itemCatId);

    //添加商品规格信息
    TaotaoResult insertItamParam(TbItemParam tbItemParam);
}
