package com.taotao.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.taotao.common.pojo.EUDataGridResult;
import com.taotao.common.pojo.TaotaoResult;
import com.taotao.pojo.TbItem;
import com.taotao.service.ItemService;

import java.util.List;

/**
 * 商品管理Controller
 * @author mutoulazy
 *
 */

@Controller
public class ItemController {

	@Autowired
	private ItemService itemService;
	
	@RequestMapping("/item/{itemId}")
	@ResponseBody
	public TbItem getItemById(@PathVariable Long itemId) {
		TbItem tbItem = itemService.getItemById(itemId);
		return tbItem;
	}
	
	@RequestMapping("/item/list")
	@ResponseBody
	public EUDataGridResult getItemList(Integer page, Integer rows) {
		EUDataGridResult result = itemService.getItemList(page, rows);
		return result;
	}

	@RequestMapping(value="/item/save",method=RequestMethod.POST)
	@ResponseBody
	public TaotaoResult createItem(TbItem item, String desc, String itemParam) throws Exception{
		TaotaoResult result = itemService.createItem(item, desc, itemParam);
		return result;
	}

	@RequestMapping("rest/page/item-edit/{itemId}")
	@ResponseBody
	public List<TbItem>  toItemEditPage(@PathVariable Long itemId){
		return itemService.toItemEditPage(itemId);
	}

	@RequestMapping(value="/rest/item/update",method=RequestMethod.POST)
	@ResponseBody
	public TaotaoResult updateItem(TbItem item){
		TaotaoResult result = itemService.updateItem(item);
		return result;
	}
}

