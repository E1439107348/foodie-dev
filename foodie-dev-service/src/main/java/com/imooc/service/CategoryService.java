package com.imooc.service;

import com.imooc.pojo.Carousel;
import com.imooc.pojo.Category;
import com.imooc.pojo.vo.CategoryVO;

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
}
