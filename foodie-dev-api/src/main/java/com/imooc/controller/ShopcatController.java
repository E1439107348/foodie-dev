package com.imooc.controller;

import com.imooc.pojo.bo.ShopcartBO;
import com.imooc.utils.IMOOCJSONResult;
import com.imooc.utils.JsonUtils;
import com.imooc.utils.RedisOperator;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.spring.web.json.Json;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

@Api(value = "购物车接口", tags = {"购物车接口先难关api"})  //类似于title  在swagger页面显示
@RestController
@RequestMapping("shopcart")
public class ShopcatController extends BaseController {
    @Autowired
    private RedisOperator redisOperator;

    @ApiOperation(value = "添加商品到购物车", notes = "添加商品到购物车", httpMethod = "POST")
    @PostMapping("/add")
    public IMOOCJSONResult add(
            @RequestParam String userId,
            @RequestBody ShopcartBO shopcartBO,
            HttpServletRequest request,
            HttpServletResponse response
    ) {

        if (StringUtils.isBlank(userId)) {
            return IMOOCJSONResult.errorMsg("");
        }

        System.out.println(shopcartBO);

        //前端用户在登录的情况下，添加商品到购物车，会同时在后端同步购物车到redis缓存
        //需要判断当前购物车中包含已存在的商品，如果存在则累加购买数量


        String shopcartJson = redisOperator.get(FOODIE_SHOPCART + ":" + userId);
        List<ShopcartBO> shopcartBOList = new ArrayList<>();
        if (StringUtils.isNotBlank(shopcartJson)) {
//redis已经存在购物车
            shopcartBOList = JsonUtils.jsonToList(shopcartJson, ShopcartBO.class);
            //判断购物车是否有当前商品
            boolean isHaving = false;
            for (ShopcartBO bo : shopcartBOList) {
                String tmpSpecId = bo.getSpecId();
                if (tmpSpecId.equals(shopcartBO.getSpecId())) {
                    bo.setBuyCounts(bo.getBuyCounts() + shopcartBO.getBuyCounts());
                    isHaving = true;
                }
            }
            if (!isHaving) {
                shopcartBOList.add(shopcartBO);
            }
        } else {
            //redis没有购物车
            shopcartBOList = new ArrayList<>();
            shopcartBOList.add(shopcartBO);
        }
        redisOperator.set(FOODIE_SHOPCART + ":" + userId, JsonUtils.objectToJson(shopcartBOList));
        return IMOOCJSONResult.ok();
    }

    @ApiOperation(value = "从购物车中删除商品", notes = "从购物车中删除商品", httpMethod = "POST")
    @PostMapping("/del")
    public IMOOCJSONResult del(
            @RequestParam String userId,
            @RequestParam String itemSpecId,
            HttpServletRequest request,
            HttpServletResponse response
    ) {

        if (StringUtils.isBlank(userId) || StringUtils.isBlank(itemSpecId)) {
            return IMOOCJSONResult.errorMsg("参数不能为空");
        }

        // 用户在页面删除购物车中的商品数据，如果此时用户已经登录，则需要同步删除后端购物车中的商品
        String shopcartJson = redisOperator.get(FOODIE_SHOPCART + ":" + userId);
        if(StringUtils.isNotBlank(shopcartJson)){
            //redis中已经有购物车
            List<ShopcartBO> shopcartBOList= JsonUtils.jsonToList(shopcartJson,ShopcartBO.class);
            //判断购物车是否有商品，如果有，则删除
            for (ShopcartBO shopcartBO : shopcartBOList) {
                String tmpSpecId=shopcartBO.getSpecId();
                if(tmpSpecId.equals(itemSpecId)){
                    shopcartBOList.remove(shopcartBO);
                    break;
                }
            }
            //股改现有购物车
            redisOperator.set(FOODIE_SHOPCART+":"+userId,JsonUtils.objectToJson(shopcartBOList));
        }
        return IMOOCJSONResult.ok();
    }























}
