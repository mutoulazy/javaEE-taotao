package com.taotao.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.taotao.common.pojo.TreeNode;
import com.taotao.mapper.TbItemCatMapper;
import com.taotao.pojo.TbItemCat;
import com.taotao.pojo.TbItemCatExample;
import com.taotao.pojo.TbItemCatExample.Criteria;
import com.taotao.service.ItemCatService;

/**
 * 商品类别管理Service
 * @author mutoulazy
 *
 */
@Service
public class ItemCatServiceImpl implements ItemCatService{
	@Autowired
	private TbItemCatMapper itemCatMapper;

	//获取商品分类列表
	@Override
	public List<TreeNode> getItemCatList(long parentId) {
		//根据parentId查询分类列表
		TbItemCatExample catExample = new TbItemCatExample();
		//设置查询条件
		Criteria criteria = catExample.createCriteria();
		criteria.andParentIdEqualTo(parentId);
		//执行查询
		List<TbItemCat> itemCatList = itemCatMapper.selectByExample(catExample);
		List<TreeNode> resultList = new ArrayList<>();
		//分类列表转换成TreeNode的列表
		for (TbItemCat tbItemCat : itemCatList) {
			//创建一个TreeNode对象
			TreeNode node = new TreeNode(tbItemCat.getId(), tbItemCat.getName(), 
					tbItemCat.getIsParent()?"closed":"open");
			resultList.add(node);

		}
		return resultList;
	}

}
