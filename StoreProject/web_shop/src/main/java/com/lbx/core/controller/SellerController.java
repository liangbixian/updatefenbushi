package com.lbx.core.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.dubbo.config.annotation.Reference;
import com.fmjava.core.pojo.entity.Result;
import com.fmjava.core.pojo.seller.Seller;
import com.lbx.core.service.SellerService;

@RestController
@RequestMapping("/seller")
public class SellerController {
    @Reference
    private SellerService sellerService;
    
    @Autowired
    private PasswordEncoder passwordencoder;

    @RequestMapping("/add")
    public Result add(@RequestBody Seller seller){
        try {
        	String password = passwordencoder.encode(seller.getPassword());
        	seller.setPassword(password);
            sellerService.add(seller);
            return new Result(true, "注册成功!");
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, "注册失败!");
        }
    }
}
