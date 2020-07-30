package com.imooc.service;

import com.imooc.pojo.Carousel;

import java.util.List;

public interface CarouselService {


    /**
     * 获取所有的轮播图片
     * @param isShow
     * @return
     */
      List<Carousel> queryAll(Integer isShow);
}
