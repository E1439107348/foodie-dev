package com.imooc.mapper;

import com.imooc.my.mapper.MyMapper;
import com.imooc.pojo.Category;
import com.imooc.pojo.vo.NewItemsVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface CategoryMapperCustom   {

      List getSubCatList(Integer rootCatId);

        List<NewItemsVO>   getSixNewItemsLazy(@Param("paramsMap") Map<String, Object> map);
}