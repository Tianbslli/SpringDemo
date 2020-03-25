package com.tianbsli.filter.common;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.servlet.ServletOutputStream;
import javax.servlet.WriteListener;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;
import java.io.*;

/**
 * @author Jorsh
 * @version 1.0
 * @date 2020/3/25 9:18 下午
 *
 * 获取到请求的输入流，从该输入流中可以读取到请求体。
 * 使用 ByteArrayOutputStream 将原 HttpSevletResponse 进行一层包装就可以实现多次读取。
 * ByteArrayOutputStream 是将数据写入到它内部的缓冲区中，这样就可以获取到这个数据了。
 */
public class ResponseWrapper extends HttpServletResponseWrapper {

    private ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
    private HttpServletResponse response;

    public ResponseWrapper(HttpServletResponse response) {
        super(response);
        this.response = response;
    }

    /**
     * 返回body
     */
    public byte[] getBody() {
        return byteArrayOutputStream.toByteArray();
    }

    @Override
    public ServletOutputStream getOutputStream() {
        return new ServletOutputStreamWrapper(this.byteArrayOutputStream , this.response);
    }

    @Override
    public PrintWriter getWriter() throws IOException {
        return new PrintWriter(new OutputStreamWriter(this.byteArrayOutputStream , this.response.getCharacterEncoding()));
    }


    //内部类，对ServletOutputStream进行包装
    @Data
    @AllArgsConstructor
    private static class ServletOutputStreamWrapper extends ServletOutputStream {

        private ByteArrayOutputStream outputStream;
        private HttpServletResponse response;

        @Override
        public boolean isReady() {
            return true;
        }

        @Override
        public void setWriteListener(WriteListener listener) {

        }

        @Override
        public void write(int b) throws IOException {
            this.outputStream.write(b);
        }

        //flush()函数是必须的，否则流中的数据无法响应到客户端。
        @Override
        public void flush() throws IOException {
            if (! this.response.isCommitted()) {
                byte[] body = this.outputStream.toByteArray();
                ServletOutputStream outputStream = this.response.getOutputStream();
                outputStream.write(body);
                outputStream.flush();
            }
        }
    }
}
