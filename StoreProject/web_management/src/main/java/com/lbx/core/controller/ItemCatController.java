package com.lbx.core.controller;

import java.util.List;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.dubbo.config.annotation.Reference;
import com.fmjava.core.pojo.entity.PageResult;
import com.lbx.core.service.ItemCatService;

@RestController
@RequestMapping("/itemCat")
public class ItemCatController {

    @Reference
    private ItemCatService catService;

    @RequestMapping("/findByParentId")
    public PageResult findByParentId(Integer page,Integer pageSize,Long parentId) {
        PageResult pageresult = catService.findByParentId(page,pageSize,parentId);
        return pageresult;
    }
}