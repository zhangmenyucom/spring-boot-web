package com.taylor.common;

import com.taylor.common.exception.ServiceException;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;

/**
 * @author Taylor
 */
public  class AbstractCrudService<Entity, Query, Dao extends BaseDao<Entity, Query>> extends BaseService<Entity, Query, Dao> implements CrudService<Entity, Query> {


    @Override
    @Transactional
    public Entity save(Entity entity) throws ServiceException {
        if (entity == null) {
            throw new ServiceException(RETURN_CODE.ARGS_EMPTY.getCode(),RETURN_CODE.ARGS_EMPTY.getMsg());
        }
        this.getDao().save(entity);
        return entity;
    }
    
    @Override
    @Transactional
    public Entity update(Entity entity) throws ServiceException {
        if (entity == null) {
            throw new ServiceException(RETURN_CODE.ARGS_EMPTY.getCode(), RETURN_CODE.ARGS_EMPTY.getMsg());
        }
        this.getDao().update(entity);
        return entity;
    }

    @Override
    public Entity updateByPrimaryKeySelective(Entity entity) throws ServiceException {
        if (entity == null) {
            throw new ServiceException(RETURN_CODE.ARGS_EMPTY.getCode(), RETURN_CODE.ARGS_EMPTY.getMsg());
        }
        this.getDao().updateByPrimaryKeySelective(entity);
        return entity;
    }

    @Override
    @Transactional
    public void del(Entity entity) throws ServiceException {
        if (entity == null) {
            throw new ServiceException(RETURN_CODE.ARGS_EMPTY.getCode(), RETURN_CODE.ARGS_EMPTY.getMsg());
        }
        this.getDao().del(entity);
    }

    @Override
    @Transactional
    public void delByPrimaryKey(Object id) throws ServiceException {
        if (id == null) {
            throw new ServiceException(RETURN_CODE.ARGS_EMPTY.getCode(), RETURN_CODE.ARGS_EMPTY.getMsg());
        }
        this.getDao().delByPrimaryKey(id);
    }

    @Override
    public boolean exist(Entity entity) throws ServiceException {
        return this.get(entity) != null;
    }

    @Override
    public Entity get(Entity entity) throws ServiceException {
        if (entity == null) {
            throw new ServiceException(RETURN_CODE.ARGS_EMPTY.getCode(), RETURN_CODE.ARGS_EMPTY.getMsg());
        }
        return this.getDao().get(entity);
    }
    @Override
    public Entity getByPrimaryKey(Object id) {
        return this.getDao().getByPrimaryKey(id);
    }

    @Override
    public List<Entity> find(Query query) throws ServiceException {
        if (query == null) {
            throw new ServiceException(RETURN_CODE.ARGS_EMPTY.getCode(), RETURN_CODE.ARGS_EMPTY.getMsg());
        }
        List<Entity> list = this.getDao().findByCondition(query);
        if (list == null) {
            list = Collections.emptyList();
        }
        return list;
    }

    @Override
    public int findTotalCount(Query query) throws ServiceException {
        if (query == null) {
            throw new ServiceException(RETURN_CODE.ARGS_EMPTY.getCode(), "参数为空");
        }
        Integer total = this.getDao().findTotalCount(query);
        return total == null ? 0 : total;
    }
}
