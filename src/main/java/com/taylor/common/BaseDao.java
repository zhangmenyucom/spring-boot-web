package com.taylor.common;
import java.util.List;
import java.util.Map;

/**
 * 基础Dao(还需在XML文件里，有对应的SQL语句)
 *
 * @author taylor
 * @email 516195940@qq.com
 * @date 2016年9月18日 上午9:31:36
 */
public interface BaseDao<T> {

    int save(T t);

    void save(Map<String, Object> map);

    void saveBatch(List<T> list);

    int update(T t);

    int update(Map<String, Object> map);

    int delete(Long id);

    int delete(Map<String, Object> map);

    int deleteBatch(Long[] id);

    T queryObject(Long id);

    List<T> queryList(Map<String, Object> map);

    int queryTotal(Map<String, Object> map);
}
