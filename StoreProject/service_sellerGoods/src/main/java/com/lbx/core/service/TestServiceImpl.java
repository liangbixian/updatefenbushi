package com.lbx.core.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.dubbo.config.annotation.Service;
import com.fmjava.core.dao.good.BrandDao;
import com.fmjava.core.pojo.good.Brand;

@Service
public class TestServiceImpl implements TestService{
	
	@Autowired
	private BrandDao brandao;
	@Override
	public List<Brand> getName() {
		List<Brand> brands = brandao.selectByExample(null);
		// TODO Auto-generated method stub
		return brands;
	}

}
