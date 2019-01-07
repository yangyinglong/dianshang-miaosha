package cn.supcon.service;

import cn.supcon.error.BusinessException;
import cn.supcon.service.model.OrderModel;

public interface OrderService {
    // 创建订单
    OrderModel createOrder(Integer userId, Integer itemId, Integer amount) throws BusinessException;
}
