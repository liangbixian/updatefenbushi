package com.lbx.core.service;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.dubbo.config.annotation.Service;
import com.fmjava.core.dao.seller.SellerDao;
import com.fmjava.core.pojo.entity.PageResult;
import com.fmjava.core.pojo.seller.Seller;
import com.fmjava.core.pojo.seller.SellerQuery;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;

@Service
@Transactional
public class SellerServiceImpl implements SellerService{

	@Autowired
	private SellerDao sellerdao;
	@Override
	public void add(Seller seller) {
		seller.setCreateTime(new Date());
		//审核状态注册的时候默认为0 ,未审核
		seller.setStatus("0");
		sellerdao.insertSelective(seller);

	}
	@Override
	public PageResult findPage(Integer page, Integer pageSize, Seller seller) {
		PageHelper.startPage(page, pageSize);
		SellerQuery query = new SellerQuery();
		SellerQuery.Criteria criteria = query.createCriteria();
		if (seller != null) {
			if (seller.getStatus() != null && !"".equals(seller.getStatus())) {
				criteria.andStatusEqualTo(seller.getStatus());
			}
			if (seller.getName() != null && !"".equals(seller.getName())){
				criteria.andNameLike("%"+seller.getName()+"%");
			}
			if (seller.getNickName() != null && !"".equals(seller.getNickName())) {
				criteria.andNickNameLike("%"+seller.getNickName()+"%");
			}
		}
		Page<Seller> sellerList = (Page<Seller>)sellerdao.selectByExample(query);
		return new PageResult(sellerList.getTotal(), sellerList.getResult());
	}
	@Override
	public Seller findOne(String id) {
		return   sellerdao.selectByPrimaryKey(id);
	}
	 @Override
	    public void updateStatus(String sellerId, String status) {
	        Seller seller = new Seller();
	        seller.setStatus(status);
	        seller.setSellerId(sellerId);
	        //下面一定加Selective，否则除了status其他的都更新为空
	        sellerdao.updateByPrimaryKeySelective(seller);
	    }

}
