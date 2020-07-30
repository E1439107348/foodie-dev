package com.imooc.service;

import com.imooc.pojo.Carousel;
import com.imooc.pojo.Category;
import com.imooc.pojo.vo.CategoryVO;
import com.imooc.pojo.vo.NewItemsVO;
import io.swagger.models.auth.In;

import java.util.List;

public interface CategoryService {


    /**
     * 查询所有一级分类
     *
     * @param
     * @return
     */
    List<Category> queryAllRootLevelCat();

    /**
     * 根据一级id获取二三级
     * @param rootCateId
     * @return
     */
    List<CategoryVO> getSubCatList(Integer rootCateId);

    /**
     * 查询首页每一个一级分类下的6条最新商品数据
     * @param rootCatId
     * @return
     */
    List<NewItemsVO> getSixNewItemsLazy(Integer rootCatId);
}
