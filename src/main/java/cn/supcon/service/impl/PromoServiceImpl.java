package cn.supcon.service.impl;

import cn.supcon.dao.PromoDOMapper;
import cn.supcon.dto.PromoDO;
import cn.supcon.service.PromoService;
import cn.supcon.service.model.PromoModel;
import org.joda.time.DateTime;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class PromoServiceImpl implements PromoService {

    @Autowired
    private PromoDOMapper promoDOMapper;

    @Override
    public PromoModel getPromoByItemId(Integer itemId) {
        // 获取秒杀商品的信息
        PromoDO promoDO = promoDOMapper.selectByItemId(itemId);
        if (promoDO == null) {
            // 改商品没有秒杀活动
            return null;
        }
        // dataObject -> dataModel
        PromoModel promoModel = convertFromDataObject(promoDO);
        // 判断当前时间，秒杀活动是否正在进行或者已经结束
        // 如果开始时间在现在时间的后面，那么活动还没开始
        if (promoModel.getStartDate().isAfterNow()) {
            // 活动还未开始
            promoModel.setStatus(1);
        } else if (promoModel.getEndDate().isBeforeNow()) {
            // 如果结束时间在现在时间的前面，那么活动已经结束
            promoModel.setStatus(3);
        } else {
            promoModel.setStatus(2);
        }
        return promoModel;
    }

    private PromoModel convertFromDataObject(PromoDO promoDO) {
        if (promoDO == null) {
            return null;
        }
        PromoModel promoModel = new PromoModel();
        BeanUtils.copyProperties(promoDO, promoModel);
        promoModel.setStartDate(new DateTime(promoDO.getStartDate()));
        promoModel.setEndDate(new DateTime(promoDO.getEndDate()));
        promoModel.setPromoItemPrice(new BigDecimal(promoDO.getPromoItemPrice()));
        return promoModel;
    }
}
