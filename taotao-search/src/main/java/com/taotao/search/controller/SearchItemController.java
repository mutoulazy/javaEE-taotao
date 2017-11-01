package com.taotao.search.controller;

import com.taotao.common.pojo.TaotaoResult;
import com.taotao.search.service.SearchItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by mutoulazy on 2017/10/31.
 */
@Controller
@RequestMapping("/manager")
public class SearchItemController {
    @Autowired
    private SearchItemService searchItemService;

    @RequestMapping("/importall")
    @ResponseBody
    public TaotaoResult importAllItems() {
        TaotaoResult result = searchItemService.importAllItems();
        return result;
    }
}
