package com.imooc.service.impl;

import com.imooc.mapper.CategoryMapper;
import com.imooc.mapper.CategoryMapperCustom;
import com.imooc.pojo.Category;
import com.imooc.pojo.vo.CategoryVO;
import com.imooc.service.CategoryService;
import com.sun.corba.se.spi.ior.ObjectKey;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class CategoryServiceImpl  implements CategoryService {
    @Autowired
    private CategoryMapper categoryMapper;
    @Autowired
    private CategoryMapperCustom categoryMapperCustom ;
    /**
     *  查询所有一级分类
     * @param
     * @return
     */
    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public List<Category> queryAllRootLevelCat() {
        Example example = new Example(Category.class);
        Example.Criteria criteria=example.createCriteria();
        criteria.andEqualTo("type",1);
        List<Category> categoryList=categoryMapper.selectByExample(example);
        return categoryList;
    }


    /**
     *  根据一级分类获取子集
     * @param
     * @return
     * rootCateId
     */
    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public    List<CategoryVO> getSubCatList(Integer rootCateId){
        return   categoryMapperCustom.getSubCatList(rootCateId);
    }

    /**
     * 查询首页每一个一级分类下的6条最新商品数据
     * @param rootCatId
     * @return
     */
    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public     List getSixNewItemsLazy(Integer rootCatId){
        Map<String, Object> map=new HashMap<>();
        map.put("rootCatId",rootCatId);

        return categoryMapperCustom.getSixNewItemsLazy(map);
    }
}
