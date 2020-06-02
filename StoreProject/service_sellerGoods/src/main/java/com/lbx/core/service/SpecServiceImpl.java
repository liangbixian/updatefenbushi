package com.lbx.core.service;

import java.util.List;
import java.util.Map;

import org.apache.jasper.tagplugins.jstl.core.ForEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.dubbo.config.annotation.Service;
import com.fmjava.core.dao.specification.SpecificationDao;
import com.fmjava.core.dao.specification.SpecificationOptionDao;
import com.fmjava.core.pojo.entity.PageResult;
import com.fmjava.core.pojo.entity.Result;
import com.fmjava.core.pojo.entity.specEntity;
import com.fmjava.core.pojo.specification.Specification;
import com.fmjava.core.pojo.specification.SpecificationOption;
import com.fmjava.core.pojo.specification.SpecificationOptionQuery;
import com.fmjava.core.pojo.specification.SpecificationQuery;
import com.fmjava.core.pojo.specification.SpecificationQuery.Criteria;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;

@Service
@Transactional
public class SpecServiceImpl implements SpecService{
	@Autowired
	private SpecificationDao specificationdao;
	@Autowired
	private SpecificationOptionDao specificationoptiondao;
	
	@Override
	public PageResult findPage(Integer page, Integer pageSize,
			Specification specification) {
		PageHelper.startPage(page,pageSize);
		SpecificationQuery specificationQuery = new SpecificationQuery();
		Criteria criteria = specificationQuery.createCriteria();
		
		if(specification!=null){
			if(specification.getSpecName()!=null&&!"".equals(specification.getSpecName())){
				criteria.andSpecNameLike("%"+specification.getSpecName()+"%");
			}
		}
		
		Page<Specification> specifications = (Page<Specification>) specificationdao.selectByExample(specificationQuery);
		PageResult pageResult = new PageResult(specifications.getTotal(), specifications.getResult());
		return pageResult;
	}
	
	@Override
	public void save(specEntity specentity) {
		//1.添加规格对象
		specificationdao.insertSelective(specentity.getSpec());
		//2.遍历添加规格选项的规格id
		for (SpecificationOption specificationOption: specentity.getSpecOption()) {
			specificationOption.setSpecId(specentity.getSpec().getId());
			//3.添加规格选项
			specificationoptiondao.insertSelective(specificationOption);
		}
	}

	/**
	 * 根据规格id查找规格名称和规格选项
	 */
	@Override
	public specEntity findSpecWithId(Long id) {
		//1.查找规格
		Specification specification = specificationdao.selectByPrimaryKey(id);
		//2.查找规格选项
		SpecificationOptionQuery specificationOptionQuery = new SpecificationOptionQuery();
		com.fmjava.core.pojo.specification.SpecificationOptionQuery.Criteria criteria = specificationOptionQuery.createCriteria();
		criteria.andSpecIdEqualTo(id);
		List<SpecificationOption> list = specificationoptiondao.selectByExample(specificationOptionQuery);
		specEntity specEntity = new specEntity();
		specEntity.setSpec(specification);
		specEntity.setSpecOption(list);
		return specEntity;
	}
	/**
	 * 更新规格
	 */
	@Override
	public void update(specEntity specentity) {
		//1.更新规格对象
		specificationdao.updateByPrimaryKeySelective(specentity.getSpec());
		//2.打破规格和规格选项的关系
		SpecificationOptionQuery specificationOptionQuery = new SpecificationOptionQuery();
		com.fmjava.core.pojo.specification.SpecificationOptionQuery.Criteria criteria = specificationOptionQuery.createCriteria();
		criteria.andSpecIdEqualTo(specentity.getSpec().getId());
		specificationoptiondao.deleteByExample(specificationOptionQuery);
		//3.再一次遍历保存每一个规格选项
		for (SpecificationOption specificationOption : specentity.getSpecOption()) {
			specificationOption.setSpecId(specentity.getSpec().getId());
			specificationoptiondao.insertSelective(specificationOption);
		}
		
	}

	@Override
	public Result delete(Long[] idx) {
		try {
			//1.删除规格对象
			if(idx!=null){
				for (Long id:idx) {
					//(根据id删除)
					specificationdao.deleteByPrimaryKey(id);
					//2.删除规格选项
					//(根据id条件删除)
					SpecificationOptionQuery specificationOptionQuery = new SpecificationOptionQuery();
					com.fmjava.core.pojo.specification.SpecificationOptionQuery.Criteria criteria = specificationOptionQuery.createCriteria();
					criteria.andSpecIdEqualTo(id);
					specificationoptiondao.deleteByExample(specificationOptionQuery);
				}
			}
			return new Result(true,"删除成功");
		} catch (Exception e) {
			return new Result(false,"删除失败");
		}
	}

	@Override
	public List<Map> selectOptionList() {
		// TODO Auto-generated method stub
		 return specificationoptiondao.selectOptionList();
	}

}
