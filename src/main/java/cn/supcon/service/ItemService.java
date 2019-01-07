package cn.supcon.service;

import cn.supcon.error.BusinessException;
import cn.supcon.service.model.ItemModel;

import java.util.List;

public interface ItemService {
    // 创建商品
    ItemModel createItem(ItemModel itemModel) throws BusinessException;
    // 商品列表浏览
    List<ItemModel> listItem();
    // 商品详情浏览
    ItemModel getItemById(Integer id);
    // 库存减少
    boolean decreaseStock(Integer itemId, Integer amount);
    // 销量增加
    void increaseSales(Integer itemId, Integer amount);
}
