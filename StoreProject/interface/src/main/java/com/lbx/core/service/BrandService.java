package com.lbx.core.service;

import java.util.List;
import java.util.Map;

import com.fmjava.core.pojo.entity.PageResult;
import com.fmjava.core.pojo.entity.Result;
import com.fmjava.core.pojo.good.Brand;

public interface BrandService {
	public List<Brand> findAllBrand();

	public PageResult findPage(Integer page, Integer pageSize, Brand brand);

	public Result add(Brand brand);

	public Brand findById(Long id);

	public Result update(Brand brand);

	public Result delete(Long[] idx);

	public List<Map> selectOptionList();
	
}
