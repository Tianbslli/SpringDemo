package com.tianbsli.business.response;

import com.tianbsli.business.constant.Code;

/**
 * @author Jorsh
 * @version 1.0
 * @date 2020/3/25 5:06 下午
 *
 *  baseResult工程方法
 */
public final class BaseResultFactory {

    public static BaseResult<Object> produceResult(Code code, Object data) {
        return new BaseResult<>(code.getCode(), code.getMsg(), data);
    }

}
