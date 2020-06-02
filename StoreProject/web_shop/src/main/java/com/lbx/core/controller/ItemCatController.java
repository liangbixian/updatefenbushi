package com.lbx.core.controller;

import java.util.List;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.dubbo.config.annotation.Reference;
import com.fmjava.core.pojo.entity.PageResult;
import com.fmjava.core.pojo.item.ItemCat;
import com.lbx.core.service.ItemCatService;

@RestController
@RequestMapping("/shopitemCat")
public class ItemCatController {

    @Reference
    private ItemCatService catService;

    @RequestMapping("/findByParentIdeasy")
    public List<ItemCat> findByParentId(Long parentId) {
    	List<ItemCat> itemCats = catService.findByParentIdeasy(parentId);
        return itemCats;
    }
    
    @RequestMapping("/findOneCategory")
    public ItemCat findOneCategory(Long id) {
    	ItemCat itemCat = catService.findOneCategory(id);
        return itemCat;
    }
}