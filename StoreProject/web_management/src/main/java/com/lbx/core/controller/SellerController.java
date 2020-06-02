package com.lbx.core.controller;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.dubbo.config.annotation.Reference;
import com.fmjava.core.pojo.entity.PageResult;
import com.fmjava.core.pojo.entity.Result;
import com.fmjava.core.pojo.seller.Seller;
import com.lbx.core.service.SellerService;

@RestController
@RequestMapping("/seller")
public class SellerController {
	@Reference
	private	SellerService sellerservice;
	
	@RequestMapping("/findPage")
    public PageResult findPage(Integer page, Integer pageSize,@RequestBody Seller seller) {
        PageResult result = sellerservice.findPage(page, pageSize, seller);
        return result;
    }
	 @RequestMapping("/findOne")
	    public Seller findOne(String id) {
	        return sellerservice.findOne(id);
	   }
	 @RequestMapping("/updateStatus")
	    public Result updateStatus(String sellerId, String status) {
	        try {
	            sellerservice.updateStatus(sellerId, status);
	            return new Result(true, "状态修改成功!");
	        } catch (Exception e) {
	            e.printStackTrace();
	            return new Result(false, "状态修改失败!");
	        }
	    }
}
