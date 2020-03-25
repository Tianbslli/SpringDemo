package com.tianbsli.interceptor;

import com.tianbsli.filter.common.RequestWrapper;
import lombok.extern.log4j.Log4j2;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Collection;
import java.util.Map;

/**
 * @author Jorsh
 * @version 1.0
 * @date 2020/3/25 11:45 上午
 */
@Log4j2
public class LogInterceptor extends HandlerInterceptorAdapter {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        Long startTime = System.currentTimeMillis();
        request.setAttribute("startTime",startTime);
        try {
            if(handler instanceof HandlerMethod){

                //打印request的完整信息
                log.info("\n----------------------开始时间：" +
                        new SimpleDateFormat("hh:mm:ss.SSS").format(startTime) + "------------\n");
                //目标方法所属类
                log.info("Controller：" + ((HandlerMethod) handler).getBean().getClass().getName() + "\n");
                //方法名
                log.info("Method：" + ((HandlerMethod) handler).getMethod().getName() + "\n");
                //请求方式
                log.info("RequestMethod：" + request.getMethod()+"\n");
                //打印param参数
                log.info("Params：" + getParamString(request.getParameterMap()) + "\n");
                /**
                 * todo [待写入blog]
                 * log.info("BodyParams：" + (RequestUtil.readRequestBody(request)) + "\n");
                 * 换成上句也行，这正验证了过滤器可以包装Request和Response，而拦截器并不能。
                 * 这也是为什么还得用上Filter来进行对httpServletRequest的包装转换。
                 */
                //打印body中的参数
                log.info("BodyParams：" + (new RequestWrapper(request).getBody()) + "\n");
                log.info("URL：" + request.getRequestURL() + "\n");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        Long startTime = (Long)request.getAttribute("startTime");
        Long endTime = System.currentTimeMillis();
        Long costTime = endTime - startTime;
        if(handler instanceof HandlerMethod) {

            log.info("\n----------------------结束时间：" +
                    new SimpleDateFormat("hh:mm:ss.SSS").format(endTime) + "------------\n");
            log.info("\n----------------------耗时：" +
                    new SimpleDateFormat("hh:mm:ss.SSS").format(costTime) + "------------\n");
            //打印response header(响应头)的信息
            Collection<String> headerNames =  response.getHeaderNames();
            headerNames.forEach(element -> log.info((element) + "：" + response.getHeader(element) + "\n"));
            //打印Status code
            log.info("Status：" + response.getStatus());
            //打印handler
            log.info("handler：" + handler);
        }
    }

    private String getParamString(Map<String, String[]> map) {
        StringBuilder sb = new StringBuilder();
        for (Map.Entry<String, String[]> e : map.entrySet()) {
            sb.append(e.getKey()).append("=");
            String[] value = e.getValue();
            if (value != null && value.length == 1) {
                sb.append(value[0]).append("\t");
            } else {
                sb.append(Arrays.toString(value)).append("\t");
            }
        }
        return sb.toString();
    }

}
