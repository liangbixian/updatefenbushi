package com.lbx.core.service;


import java.util.List;

import com.fmjava.core.pojo.entity.PageResult;
import com.fmjava.core.pojo.item.ItemCat;

public interface ItemCatService {

	public PageResult findByParentId(Integer page, Integer pageSize, Long parentId);

	public List<ItemCat> findByParentIdeasy(Long parentId);

	public ItemCat findOneCategory(Long id);
	
}
