package com.lbx.core.controller;

import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.dubbo.config.annotation.Reference;
import com.fmjava.core.pojo.entity.PageResult;
import com.fmjava.core.pojo.entity.Result;
import com.fmjava.core.pojo.good.Brand;
import com.lbx.core.service.BrandService;

@RestController
@RequestMapping("/brand")
public class BrandController {
	@Reference
	private BrandService brandservice;
	
	@RequestMapping("/findAllBrands")
	public List<Brand> getBrands(){
		return brandservice.findAllBrand();
	}
	
	/**
	 * 
	 * @param page  //当前页
	 * @param pageSize  //当前页记录数
	 */
	@RequestMapping("/findPage")
	public PageResult findPage(Integer page,Integer pageSize,@RequestBody Brand brand){
		PageResult pageresutl=brandservice.findPage(page,pageSize,brand);
		System.out.println(pageresutl);
		return pageresutl;
	}
	/**
	 * 
	 * @param brand 添加商品的数据
	 */
	@RequestMapping("/add")
	public Result addBrand(@RequestBody Brand brand){
		Result result = brandservice.add(brand);
		return result;
	}
	/**
	 * 修改品牌数据的数据回显
	 * @param id
	 * @return
	 */
	@RequestMapping("/findById")
	public Brand findById(Long id){
		return brandservice.findById(id);
	}
	/**
	 * 更新品牌
	 * @param brand 品牌实体
	 * @return
	 */
	@RequestMapping("/update")
	public Result findById(@RequestBody Brand brand){
		return brandservice.update(brand);
	}
	/**
	 * 删除品牌
	 * @param idx 选中的品牌id数组
	 * @return
	 */
	@RequestMapping("/delete")
	public Result delete(Long[] idx){
		return brandservice.delete(idx);
	}
	@RequestMapping("/selectOptionList")
    public List<Map> selectOptionList(){
        return brandservice.selectOptionList();
    }
}
