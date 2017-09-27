package com.taotao.service;

import java.util.List;

import com.taotao.common.pojo.TreeNode;

public interface ItemCatService {
	//获取商品分类信息
	List<TreeNode> getItemCatList(long parentId);
}
