package com.taotao.service;

import java.util.List;

import com.taotao.common.pojo.EUDataGridResult;
import com.taotao.common.pojo.TaotaoResult;
import com.taotao.common.pojo.TreeNode;
import com.taotao.pojo.TbItem;

public interface ItemService {
	//根据itemId获取商品信息
	TbItem getItemById(long itemId);
	
	//获取商品表的分页信息
	EUDataGridResult getItemList(int page, int rows);
	
	//添加商品信息
	TaotaoResult createItem(TbItem item);
}
