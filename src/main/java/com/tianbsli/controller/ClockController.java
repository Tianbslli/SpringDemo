package com.tianbsli.controller;

import com.tianbsli.business.constant.Code;
import com.tianbsli.business.constant.TimeStyle;
import com.tianbsli.business.response.BaseResult;
import com.tianbsli.business.response.BaseResultFactory;
import com.tianbsli.model.TimeVO;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author Jorsh
 * @version 1.0
 * @date 2020/3/21 10:24 下午
 *
 * 此接口实现报时功能
 */
@RestController
public class ClockController {

    /**
    * GET方法
    * 直接获取当前日期+时间
    */
    @GetMapping(value = "/timeTelling")
    public BaseResult timeTelling(@RequestParam(value = "test") String test){
        System.out.println(test);
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
        return BaseResultFactory.produceResult(Code.SUCCESS,df.format(new Date()));
    }

    /**
     * POST方法
     * 根据参数里的Style来返回指定日期时间格式
     */
    @PostMapping(value = "/timeTellingAsRequire")
    public BaseResult timeTellingAsRequire(
            @RequestBody TimeVO timeVO){

        Integer timeStyle = timeVO.getTimeStyle();
        SimpleDateFormat df;

        if(timeStyle == TimeStyle.DATE.getCode()){
            df = new SimpleDateFormat("yyyy-MM-dd");//设置格式为日期
        }
        else if(timeStyle == TimeStyle.TIME.getCode()){
            df = new SimpleDateFormat("HH:mm:ss");//设置格式为时间
        }
        else if(timeStyle == TimeStyle.DATETIME.getCode()){
            df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置格式为日期与时间
        }
        else{
            return BaseResultFactory.produceResult(Code.PARAM_ERROR,timeVO);
        }
        return BaseResultFactory.produceResult(Code.SUCCESS,df.format(new Date()));
    }




}
