package com.taotao.search.dao;

import com.taotao.search.pojo.SearchResult;
import org.apache.solr.client.solrj.SolrQuery;

/**
 * Created by mutoulazy on 2017/11/1.
 */
public interface SearchDao {
    SearchResult search(SolrQuery query) throws Exception;
}
