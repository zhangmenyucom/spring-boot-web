package com.taylor.common; /**
 * ${author} on 2018/9/19.
 */

import java.util.List;
import java.util.Map;

/**
 * @author zhangxiaolu
 * @描述 通用服务接口
 * @since 2018/9/19 11:36
 */
public interface BaseService<T> {
    T queryObject(Long id);

    List<T> queryList(Map<String, Object> map);

    List<T> queryList(Map<String, Object> map,Long merchantId);

    int queryTotal(Map<String, Object> map);

    int queryTotal(Map<String, Object> map,Long merchantId);

    int save(T t);

    int update(T t);

    int delete(Long id);

    int deleteBatch(Long[] ids);
}
