package com.taotao.search.service;

import com.taotao.search.pojo.SearchResult;

/**
 * Created by mutoulazy on 2017/11/1.
 */
public interface SearchService {
    SearchResult search(String queryString, int page, int rows) throws Exception;
}
