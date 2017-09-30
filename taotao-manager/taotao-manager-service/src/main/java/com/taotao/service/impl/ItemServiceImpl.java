package com.taotao.service.impl;

import java.util.Date;
import java.util.List;

import com.taotao.mapper.TbItemDescMapper;
import com.taotao.mapper.TbItemParamItemMapper;
import com.taotao.pojo.TbItemDesc;
import com.taotao.pojo.TbItemParamItem;
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

	@Autowired
	private TbItemDescMapper tbItemDescMapper;

	@Autowired
	private TbItemParamItemMapper tbItemParamItemMapper;
	
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
	public TaotaoResult createItem(TbItem item , String itemDesc, String itemParam) throws Exception{
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
			throw new Exception("插入商品信息异常");
		}
		//添加商品描述
		TaotaoResult insertDescResult =  insertItemDesc(itemId,itemDesc);
		if (insertDescResult.getStatus() != 200){
			throw new Exception("插入商品描述异常");
		}
		//添加商品规格参数
		TaotaoResult insertParamResult = insertItemParamItem(itemId, itemParam);
		if (insertParamResult.getStatus() != 200){
			throw new Exception();
		}
		//返回返回值
		return TaotaoResult.ok();
	}

	/**
	 * 添加商品描述信息
	 * @param itemId
	 * @param itemDesc
	 * @return
	 */
	private TaotaoResult insertItemDesc(long itemId, String itemDesc){
		TbItemDesc tbItemDesc = new TbItemDesc();
		tbItemDesc.setItemId(itemId);
		tbItemDesc.setItemDesc(itemDesc);
		tbItemDesc.setCreated(new Date());
		tbItemDesc.setUpdated(new Date());

		tbItemDescMapper.insert(tbItemDesc);
		return TaotaoResult.ok();
	}

	/**
	 * 添加商品规格信息
	 * @param itemId
	 * @param itemParam
	 * @return
	 */
	public TaotaoResult insertItemParamItem(long itemId, String itemParam) {
		//构建TbItemParmItem类型
		TbItemParamItem tbItemParamItem = new TbItemParamItem();
		tbItemParamItem.setItemId(itemId);
		tbItemParamItem.setParamData(itemParam);
		tbItemParamItem.setCreated(new Date());
		tbItemParamItem.setUpdated(new Date());
		//插入数据
		tbItemParamItemMapper.insert(tbItemParamItem);

		return TaotaoResult.ok();
	}

	/**
	 * 根据id获取商品信息，并且返回到商品修改页面
	 * @param itemId
	 * @return
	 */
	@Override
	public List<TbItem> toItemEditPage(long itemId) {
		//添加查询条件
		TbItemExample example = new TbItemExample();
		Criteria criteria = example.createCriteria();
		criteria.andIdEqualTo(itemId);
		List<TbItem> list = itemMapper.selectByExample(example);
		if (list != null && list.size() > 0) {
			return list;
		}
		return null;
	}

	/**
	 * 修改商品信息
	 * @param item
	 * @return
	 */
	@Override
	public TaotaoResult updateItem(TbItem item) {
		int result = itemMapper.updateByPrimaryKey(item);
		if (result<1){
			System.out.println("update 执行失败");
			return null;
		}
		return TaotaoResult.ok();
	}
}

