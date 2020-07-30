package com.imooc.mapper;

import com.imooc.my.mapper.MyMapper;
import com.imooc.pojo.Category;

import java.util.List;

public interface CategoryMapperCustom   {

      List getSubCatList(Integer rootCatId);
}