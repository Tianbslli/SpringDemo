package com.tianbsli.Controller;

import com.tianbsli.annotation.PrintLog;
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

    @RequestMapping(value = "/timeTelling", method = RequestMethod.GET)
    @ResponseBody
    @PrintLog
    public String timeTelling(){
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
        return df.format(new Date());
    }

}
