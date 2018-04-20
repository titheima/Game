package com.anoyi.config.server;

import com.anoyi.bean.ResponseBean;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 全局异常处理
 */
@ControllerAdvice
@Log4j2
@AllArgsConstructor
public class GlobalExceptionHandler {

    /**
     * 抛出错误前，打印错误日志
     * **/
    @ExceptionHandler(Exception.class)
    @ResponseBody
    public ResponseBean handleException(Exception exception){
        log.error(exception.getMessage(), exception);
        return ResponseBean.error(exception);
    }

}
