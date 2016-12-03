
package com.jju.service;

import java.io.Serializable;

import com.jju.domain.Goods;
import com.jju.domain.PageModel;
import com.jju.exception.ServiceException;
import com.jju.formbean.GoodsBean;


public interface IGoodsService {
	void getList(PageModel<GoodsBean> page, String filterType, String searchkey) throws ServiceException;

	boolean add(Goods goods) throws ServiceException;

	boolean update(Goods goods) throws ServiceException;

	boolean delete(Serializable goods) throws ServiceException;

	Goods get(Serializable pk) throws ServiceException;
}
