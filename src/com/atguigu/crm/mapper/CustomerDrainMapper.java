package com.atguigu.crm.mapper;

import org.apache.ibatis.annotations.Update;

public interface CustomerDrainMapper {
	@Update("{call check_drain()}")
	void callCheckCustomerDrain();
	
}
