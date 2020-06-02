package com.lbx.core.service;

import java.util.List;
import java.util.Map;

import com.fmjava.core.pojo.entity.PageResult;
import com.fmjava.core.pojo.entity.Result;
import com.fmjava.core.pojo.entity.specEntity;
import com.fmjava.core.pojo.specification.Specification;

public interface SpecService {

	PageResult findPage(Integer page, Integer pageSize,
			Specification specification);

	void save(specEntity specentity);

	specEntity findSpecWithId(Long id);

	void update(specEntity specentity);

	Result delete(Long[] idx);

	List<Map> selectOptionList();

}
