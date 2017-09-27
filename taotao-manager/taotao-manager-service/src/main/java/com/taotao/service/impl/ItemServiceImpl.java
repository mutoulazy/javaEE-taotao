package com.taotao.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.taotao.common.pojo.EUDataGridResult;
import com.taotao.common.pojo.TaotaoResult;
import com.taotao.common.utils.IDUtils;
import com.taotao.mapper.TbItemMapper;
import com.taotao.pojo.TbItem;
import com.taotao.pojo.TbItemExample;
import com.taotao.pojo.TbItemExample.Criteria;
import com.taotao.service.ItemService;

/**
 * 商品管理Service
 * @author mutoulazy
 *
 */
@Service
public class ItemServiceImpl implements ItemService {

	@Autowired
	private TbItemMapper itemMapper;
	
	@Override
	public TbItem getItemById(long itemId) {
		
		//TbItem item = itemMapper.selectByPrimaryKey(itemId);
		
		//添加查询条件
		TbItemExample example = new TbItemExample();
		Criteria criteria = example.createCriteria();
		criteria.andIdEqualTo(itemId);
		List<TbItem> list = itemMapper.selectByExample(example);
		if (list != null && list.size() > 0) {
			TbItem item = list.get(0);
			return item;
		}
		return null;
	}

	/**
	 * 商品列表查询
	 */
	@Override
	public EUDataGridResult getItemList(int page, int rows) {
		//查询商品列表
		TbItemExample example = new TbItemExample();
		PageHelper.startPage(page, rows);// 添加分页条件
		List<TbItem> list = itemMapper.selectByExample(example);
		PageInfo<TbItem> pageInfo = new PageInfo<TbItem>(list);
		//构造返回的数据类型
		EUDataGridResult euDataGridResult = new EUDataGridResult();
		euDataGridResult.setRows(list);
		euDataGridResult.setTotal(pageInfo.getTotal());
		return euDataGridResult;
	}

	/**
	 * 添加商品信息
	 */
	@Override
	public TaotaoResult createItem(TbItem item) {
		// 补全Item中的信息
		//生成商品Id
		long itemId = IDUtils.genItemId();
		item.setId(itemId);
		//商品状态正常
		item.setStatus((byte)1);
		//补全商品创建更新时间
		item.setCreated(new Date());
		item.setUpdated(new Date());
		
		//插入数据库
		int result = itemMapper.insert(item);
		System.out.println("insert result " + result);
		if (result <= 0){
			return TaotaoResult.build(400, "insert error");
		}
		//返回返回值
		return TaotaoResult.ok();
	}

}

