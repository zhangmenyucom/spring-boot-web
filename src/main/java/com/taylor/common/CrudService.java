package com.taylor.common;

import java.util.List;

/**
 * @author taylor
 */
public interface CrudService<Entity,Query> {
	
	  Entity save(Entity entity);

	  Entity saveSelective(Entity entity);

	  int update(Entity entity);

	  Entity updateByPrimaryKeySelective(Entity entity);

	  int del(Entity entity);

	  void delByPrimaryKey(Object id);
	  
	  Entity get(Entity entity);

	  Entity getByPrimaryKey(Object id);

	  List<Entity> find(Query query);

	  int findTotalCount(Query query);
	  
	  boolean exist(Entity entity);


}
