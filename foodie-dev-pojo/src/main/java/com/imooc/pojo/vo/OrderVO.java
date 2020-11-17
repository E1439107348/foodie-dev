package com.imooc.pojo.vo;

import com.imooc.pojo.bo.ShopcartBO;

import java.util.List;

public class OrderVO {

    private String orderId;
    private MerchantOrdersVO merchantOrdersVO;

    public List<ShopcartBO> getToBeRemovedShopCatdList() {
        return toBeRemovedShopCatdList;
    }

    public void setToBeRemovedShopCatdList(List<ShopcartBO> toBeRemovedShopCatdList) {
        this.toBeRemovedShopCatdList = toBeRemovedShopCatdList;
    }

    private List<ShopcartBO> toBeRemovedShopCatdList;

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public MerchantOrdersVO getMerchantOrdersVO() {
        return merchantOrdersVO;
    }

    public void setMerchantOrdersVO(MerchantOrdersVO merchantOrdersVO) {
        this.merchantOrdersVO = merchantOrdersVO;
    }
}