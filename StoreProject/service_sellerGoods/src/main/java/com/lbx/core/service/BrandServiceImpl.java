package com.lbx.core.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.dubbo.config.annotation.Service;
import com.fmjava.core.dao.good.BrandDao;
import com.fmjava.core.pojo.entity.PageResult;
import com.fmjava.core.pojo.entity.Result;
import com.fmjava.core.pojo.good.Brand;
import com.fmjava.core.pojo.good.BrandQuery;
import com.fmjava.core.pojo.good.BrandQuery.Criteria;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;

@Service
@Transactional
public class BrandServiceImpl implements BrandService{
	@Autowired
	private BrandDao branddao;	
	@Override
	public List<Brand> findAllBrand() {
		// TODO Auto-generated method stub
		return branddao.selectByExample(null);
	}
	@Override
	public PageResult findPage(Integer page, Integer pageSize,Brand brand) {
		//分页查询
		PageHelper.startPage(page,pageSize);
		BrandQuery brandQuery = new BrandQuery();
		Criteria criteria = brandQuery.createCriteria();
		if(brand!=null){
			if(brand.getName()!=null&&!"".equals(brand.getName())){
				criteria.andNameLike("%"+brand.getName()+"%");
			}
			if(brand.getFirstChar()!=null&&!"".equals(brand.getFirstChar())){
				criteria.andFirstCharLike("%"+brand.getFirstChar()+"%");
			}
		}
		brandQuery.setOrderByClause("id desc");
		Page<Brand> brands=(Page<Brand>) branddao.selectByExample(brandQuery);
		PageResult pageResult = new PageResult(brands.getTotal(), brands.getResult());
		return pageResult;
	}
	@Override
	public Result add(Brand brand) {
		try {
			branddao.insertSelective(brand);
			return new Result(true,"添加成功");
		} catch (Exception e) {
			return new Result(false,"添加失败");
		}
		
	}
	@Override
	public Brand findById(Long id) {
		Brand brand = branddao.selectByPrimaryKey(id);
		return brand;
	}
	@Override
	public Result update(Brand brand) {
		try {
			branddao.updateByPrimaryKeySelective(brand);
			return new Result(true,"更新成功");
		} catch (Exception e) {
			return new Result(false,"更新失败");
		}
	}
	
	@Override
	public Result delete(Long[] idx) {
		try {
			for (Long id : idx) {
				branddao.deleteByPrimaryKey(id);
			}
			return new Result(true,"删除成功");
		} catch (Exception e) {
			return new Result(false,"删除失败");
		}
	}
	@Override
    public List<Map> selectOptionList() {
        return branddao.selectOptionList();
    }

}
