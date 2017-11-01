package com.taotao.search.dao.impl;

import com.taotao.search.dao.SearchDao;
import com.taotao.search.pojo.SearchItem;
import com.taotao.search.pojo.SearchResult;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by mutoulazy on 2017/11/1.
 */
@Repository
public class SearchDaoImpl implements SearchDao{
    @Autowired
    private SolrServer solrServer;

    @Override
    public SearchResult search(SolrQuery query) throws Exception{
        SearchResult searchResult = new SearchResult();
        QueryResponse queryResponse = solrServer.query(query);
        SolrDocumentList solrDocumentList = queryResponse.getResults();

        searchResult.setRecordCount(solrDocumentList.getNumFound());
        List<SearchItem> itemList = new ArrayList<>();

        //获取高亮显示
        Map<String, Map<String, List<String>>> highlighting = queryResponse.getHighlighting();
        for (SolrDocument solrDocument:solrDocumentList) {
            SearchItem item = new SearchItem();
            item.setId((String)solrDocument.get("id"));
            //取高亮显示的结果
            List<String> list = highlighting.get(solrDocument.get("id")).get("item_title");
            String title = "";
            if (list != null && list.size()>0) {
                title = list.get(0);
            } else {
                title = (String) solrDocument.get("item_title");
            }
            item.setTitle(title);
            item.setImage((String) solrDocument.get("item_image"));
            item.setPrice((long) solrDocument.get("item_price"));
            item.setSell_point((String) solrDocument.get("item_sell_point"));
            item.setCategory_name((String) solrDocument.get("item_category_name"));
            //添加的商品列表
            itemList.add(item);
        }
        searchResult.setItemList(itemList);
        return searchResult;
    }
}
