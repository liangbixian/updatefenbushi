package com.lbx.core.service;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.dubbo.config.annotation.Service;
import com.fmjava.core.dao.item.ItemCatDao;
import com.fmjava.core.pojo.entity.PageResult;
import com.fmjava.core.pojo.item.ItemCat;
import com.fmjava.core.pojo.item.ItemCatQuery;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;

@Service
public class ItemCatServiceImpl implements ItemCatService{
	@Autowired
    private ItemCatDao catDao;
    @Override
    public PageResult findByParentId(Integer page,Integer pageSize,Long parentId) {
    	PageHelper.startPage(page, pageSize);
        ItemCatQuery query = new ItemCatQuery();
        ItemCatQuery.Criteria criteria = query.createCriteria();
        criteria.andParentIdEqualTo(parentId);
        Page<ItemCat> itemCats = (Page<ItemCat>) catDao.selectByExample(query);
        return new PageResult(itemCats.getTotal(),itemCats.getResult());
    }
	@Override
	public List<ItemCat> findByParentIdeasy(Long parentId) {
		ItemCatQuery query = new ItemCatQuery();
        ItemCatQuery.Criteria criteria = query.createCriteria();
        criteria.andParentIdEqualTo(parentId);
        List<ItemCat> itemCats = catDao.selectByExample(query);
		return itemCats;
	}
	@Override
	public ItemCat findOneCategory(Long id) {
		
		return catDao.selectByPrimaryKey(id);
	}

}
