package com.alirizakaygusuz.aop_poc.order.mapper;

import com.alirizakaygusuz.aop_poc.common.mapper.BaseMapper;
import com.alirizakaygusuz.aop_poc.order.dto.OrderResponse;
import com.alirizakaygusuz.aop_poc.order.dto.OrderUpdateRequest;
import com.alirizakaygusuz.aop_poc.order.dto.internal.InternalOrderCreateRequest;
import com.alirizakaygusuz.aop_poc.order.model.Order;

public interface OrderMapper extends BaseMapper<Order , OrderResponse , InternalOrderCreateRequest ,OrderUpdateRequest>  {


}
