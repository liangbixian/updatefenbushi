package com.fmjava.core.pojo.entity;

import java.io.Serializable;
import java.util.List;

import com.fmjava.core.pojo.specification.Specification;
import com.fmjava.core.pojo.specification.SpecificationOption;

public class specEntity implements Serializable{
	private Specification spec;
	private List<SpecificationOption> specOption;
	public Specification getSpec() {
		return spec;
	}
	public void setSpec(Specification spec) {
		this.spec = spec;
	}
	public List<SpecificationOption> getSpecOption() {
		return specOption;
	}
	public void setSpecOption(List<SpecificationOption> specOption) {
		this.specOption = specOption;
	}
	
	
}
