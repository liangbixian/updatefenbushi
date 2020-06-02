package com.lbx.core.controller;

import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.dubbo.config.annotation.Reference;
import com.fmjava.core.pojo.entity.PageResult;
import com.fmjava.core.pojo.entity.Result;
import com.fmjava.core.pojo.entity.specEntity;
import com.fmjava.core.pojo.specification.Specification;
import com.lbx.core.service.SpecService;

@RestController
@RequestMapping("/spec")
public class SpecController {
	@Reference
	private SpecService specservice;
	
	@RequestMapping("/search")
	public PageResult search(Integer page,Integer pageSize,@RequestBody Specification specification){
		PageResult pageresult=specservice.findPage(page,pageSize,specification);
		return pageresult;
	}
	/**
	 * 保存规格
	 * @param specentity 规格和规格选项总实体
	 * @return
	 */
	@RequestMapping("/save")
	public Result save(@RequestBody specEntity specentity){
		try {
			specservice.save(specentity);
			return new Result(true,"保存成功");
		} catch (Exception e) {
			return new Result(false,"保存失败");
		}
	}
	@RequestMapping("/findSpecWithId")
	public specEntity findSpecWithId(Long id){
		return specservice.findSpecWithId(id);
	}
	@RequestMapping("/update")
	public Result update(@RequestBody specEntity specentity){
		try {
			specservice.update(specentity);
			return new Result(true,"更新成功");
		} catch (Exception e) {
			return new Result(false,"更新失败");
		}
	}
	@RequestMapping("/delete")
	public Result delete(Long[] idx){
		System.out.println("删除==================");
		return specservice.delete(idx);
	}
	 @RequestMapping("/selectOptionList")
	    public List<Map> selectOptionList() {
	        return specservice.selectOptionList();
	    }
}
