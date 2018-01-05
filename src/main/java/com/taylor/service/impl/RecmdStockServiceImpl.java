package com.taylor.service.impl;

import com.taylor.common.AbstractCrudService;
import com.taylor.dao.RecmdStockDao;
import com.taylor.entity.RecmdStock;
import com.taylor.service.RecmdStockService;
import org.springframework.stereotype.Service;

/**
 * @author Administrator
 */
@Service
public class RecmdStockServiceImpl extends  AbstractCrudService<RecmdStock, RecmdStock,RecmdStockDao> implements RecmdStockService {

}
