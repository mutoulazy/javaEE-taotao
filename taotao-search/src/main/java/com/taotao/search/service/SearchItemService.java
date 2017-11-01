package com.taotao.search.service;

import com.taotao.common.pojo.TaotaoResult;
import com.taotao.search.pojo.SearchResult;

/**
 * Created by mutoulazy on 2017/10/31.
 */
public interface SearchItemService {
    //由数据库向solr服务器传输数据
    TaotaoResult importAllItems();
}
