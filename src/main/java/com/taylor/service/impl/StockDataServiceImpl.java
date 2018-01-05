package com.taylor.service.impl;

import com.taylor.common.AbstractCrudService;
import com.taylor.dao.StockDataDao;
import com.taylor.entity.StockData;
import com.taylor.service.StockDataService;
import org.springframework.stereotype.Service;

/**
 * @author Administrator
 */
@Service
public class StockDataServiceImpl extends  AbstractCrudService<StockData, StockData,StockDataDao> implements StockDataService {

}
