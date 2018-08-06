package com.taylor.common;

import com.taylor.common.exception.ServiceException;

import java.util.List;

public interface CrudService<Entity,Query> {
	
	  Entity save(Entity entity) throws ServiceException;

	  Entity update(Entity entity) throws ServiceException;

	  Entity updateByPrimaryKeySelective(Entity entity) throws ServiceException;

	  void del(Entity entity) throws ServiceException;

	  void delByPrimaryKey(Object id) throws ServiceException;
	  
	  Entity get(Entity entity) throws ServiceException;

	  Entity getByPrimaryKey(Object id);

	  List<Entity> find(Query query) throws ServiceException;

	  int findTotalCount(Query query) throws ServiceException;
	  
	  boolean exist(Entity entity) throws ServiceException;


}
