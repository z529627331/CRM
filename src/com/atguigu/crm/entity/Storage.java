package com.atguigu.crm.entity;

import javax.persistence.Entity;
import javax.persistence.Table;
@Table(name="storages")
@Entity
public class Storage extends IdEntity{

	//所属商品
	private Product product;
	//仓库
	private String wareHouse;
	
	//库位
	private String stockWare;
	//数量
	private int stockCount;
	
	//备注
	private String memo;

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public String getWareHouse() {
		return wareHouse;
	}

	public void setWareHouse(String wareHouse) {
		this.wareHouse = wareHouse;
	}

	public String getStockWare() {
		return stockWare;
	}

	public void setStockWare(String stockWare) {
		this.stockWare = stockWare;
	}

	public int getStockCount() {
		return stockCount;
	}

	public void setStockCount(int stockCount) {
		this.stockCount = stockCount;
	}

	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

	@Override
	public String toString() {
		return "Storage [product=" + product + ", wareHouse=" + wareHouse
				+ ", stockWare=" + stockWare + ", stockCount=" + stockCount
				+ ", memo=" + memo + "]";
	}
	
	
}
