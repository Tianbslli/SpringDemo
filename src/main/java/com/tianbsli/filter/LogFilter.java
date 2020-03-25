package com.tianbsli.filter;

import com.tianbsli.filter.common.RequestWrapper;
import com.tianbsli.filter.common.ResponseWrapper;
import lombok.extern.log4j.Log4j2;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;


import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

/**
 * @author Jorsh
 * @version 1.0
 * @date 2020/3/25 3:28 下午
 *
 *  POST、PUT等方法的参数能放在body中，而request.getParameterMap()无法读到body中的参数，所以需要直接读取request的流来获取参数。
 *  但是由于ServletRequest中getReader()和getInputStream()只能调用一次。[todo:至于为什么只能调用一次的底层原理，将在博客中阐述]
 *  所以需要新建一个 filter 实现对传入的 httpServletRequest 的包装转换。
 *  重写HttpServletRequestWrapper把request保存下来，读出request的body的数据，然后把保存下来的request再填充进去，
 *  即可保证能多次读取到这个request的数据了。
 *  Response基本同理。
 */
@Order(0)
@WebFilter(filterName = "LogFilter", urlPatterns = "/*")
@Log4j2
public class LogFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response,
                         FilterChain chain) throws IOException, ServletException {
        ServletRequest requestWrapper = null;
        //首先获取request中的流，读取出body中的数据并存在静态变量中。
        if(request instanceof HttpServletRequest) {
            requestWrapper = new RequestWrapper((HttpServletRequest)request);
        }

        // 最后在chain.doFilter方法中传递新的request对象 requestWrapper，以供后面的@RequestBody能够读取requset的流。
        if(requestWrapper == null) {
            chain.doFilter(request, response);
        } else {
            chain.doFilter(requestWrapper, response);
        }

        /**
         * 打印response的body信息
         * 由于filter和interceptor的执行顺序关系，response的body信息将正好打印在response的header信息下面(由interceptor打印)。
         */
        if(response instanceof HttpServletResponse) {

            ResponseWrapper responseWrapper =
                    new ResponseWrapper((HttpServletResponse) response);

            chain.doFilter(requestWrapper , responseWrapper);

            byte[] responseBody = responseWrapper.getBody();

            log.info("response body：" + new String(responseBody, StandardCharsets.UTF_8));

        }

    }

    @Override
    public void init(FilterConfig arg0) throws ServletException {
    }

    @Override
    public void destroy() {
    }
}
