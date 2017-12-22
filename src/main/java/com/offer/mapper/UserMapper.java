package com.offer.mapper;

import com.offer.bean.User;

/**
 * @Author lizhen [v_zhennli@tencent.com]
 * @Date 2017/12/19 15:51
 */
public interface UserMapper {
    User findById(Integer userId);
}
