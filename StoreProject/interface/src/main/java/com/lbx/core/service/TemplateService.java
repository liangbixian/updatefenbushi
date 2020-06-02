package com.lbx.core.service;

import com.fmjava.core.pojo.entity.PageResult;
import com.fmjava.core.pojo.template.TypeTemplate;

public interface TemplateService {
    public PageResult findPage(TypeTemplate template, Integer page, Integer pageSize);

	public void add(TypeTemplate template);

	public TypeTemplate findOne(Long id);
}
