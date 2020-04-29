package com.lbx.core.controller;

import java.util.List;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.dubbo.config.annotation.Reference;
import com.fmjava.core.pojo.good.Brand;
import com.lbx.core.service.TestService;

@RestController
public class GetNameController {
	@Reference
	private TestService testservice; 
	//wdnmd
	@RequestMapping("/getname")
	public List<Brand> getname(){
		return testservice.getName();
	}
}
