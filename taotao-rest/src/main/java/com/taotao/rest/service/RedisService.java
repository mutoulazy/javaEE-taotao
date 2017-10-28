package com.taotao.rest.service;

import com.taotao.common.pojo.TaotaoResult;

/**
 * Created by mutoulazy on 2017/10/28.
 */
public interface RedisService {
    TaotaoResult syncContent(long contentCid);
}
