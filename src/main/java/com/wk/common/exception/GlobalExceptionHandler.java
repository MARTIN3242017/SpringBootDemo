package com.wk.common.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindException;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.UnexpectedTypeException;
import java.util.List;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MyException.class)
    public ResultVO myException(HttpServletRequest r, MyException e) {
        log.debug("[捕获到全局异常MyExceptionHandler]", e);
        return new ResultVO(e.getCode(), e.getMessage(), null);
    }

    @ExceptionHandler(MissingServletRequestParameterException.class)
    public ResultVO missingServletRequestParameterExceptionHandler(HttpServletRequest r, MissingServletRequestParameterException e) {
        log.debug("[捕获到全局异常missingServletRequestParameterExceptionHandler]", e);
        return new ResultVO(CodeEnum.PARAM_ERROR.getCode(),
                CodeEnum.PARAM_ERROR.getMessage(), null);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResultVO constraintViolationExceptionHandler(HttpServletRequest r, ConstraintViolationException e) {
        log.debug("[捕获到全局异常constraintViolationExceptionHandler]", e);
        // 拼接错误
        StringBuilder detailMessage = new StringBuilder();
        for (ConstraintViolation<?> constraintViolation : e.getConstraintViolations()) {
            if (detailMessage.length() > 0) {
                detailMessage.append(";");
            }
            detailMessage.append(constraintViolation.getMessage());
        }

        return new ResultVO(CodeEnum.PARAM_ERROR.getCode(),
                CodeEnum.PARAM_ERROR.getMessage() + ":" + detailMessage.toString(), null);
    }

    @ExceptionHandler(BindException.class)
    public ResultVO bindExceptionHandler(HttpServletRequest r, BindException e) {
        log.debug("[捕获到全局异常 bindExceptionHandler]", e);
        // 拼接错误
        StringBuilder detailMessage = new StringBuilder();
        for (ObjectError objectError : e.getAllErrors()) {
            // 使用 ; 分隔多个错误
            if (detailMessage.length() > 0) {
                detailMessage.append(";");
            }
            // 拼接内容到其中
            detailMessage.append(objectError.getDefaultMessage());
        }
        return new ResultVO(CodeEnum.PARAM_ERROR.getCode(), CodeEnum.PARAM_ERROR.getMessage(), detailMessage.toString());
    }

    @ExceptionHandler(IllegalStateException.class)
    public ResultVO illegalStateExceptionHandler(HttpServletRequest r, Exception e) {
        log.error("[捕获到全局异常 IllegalStateException]", e);
        return new ResultVO(CodeEnum.FAIL.getCode(), CodeEnum.FAIL.getMessage(), e.getMessage());
    }

    @ExceptionHandler({ArrayIndexOutOfBoundsException.class, IndexOutOfBoundsException.class})
    public ResultVO arrayIndexOutOfBoundsExceptionHandler(HttpServletRequest r, Exception e) {
        log.error("[捕获到全局异常 ArrayIndexOutOfBoundsException]", e);
        return new ResultVO(CodeEnum.FAIL.getCode(), "数组下标越界", e.getMessage());
    }

    @ExceptionHandler({MethodArgumentNotValidException.class})
    public ResultVO methodArgumentNotValidExceptionHandler(HttpServletRequest r, MethodArgumentNotValidException e) {
        StringBuilder detailMessage = new StringBuilder();
        List<ObjectError> allErrors = e.getBindingResult().getAllErrors();
        for (ObjectError error : allErrors) {
            detailMessage.append(error.getDefaultMessage());
        }
        log.error("[捕获到全局异常MethodArgumentNotValidException] {}", detailMessage.toString());
        return new ResultVO(400, "请求参数有误", detailMessage.toString());
    }

    @ExceptionHandler({UnexpectedTypeException.class, HttpMessageNotReadableException.class})
    public ResultVO paramExceptionHandler(HttpServletRequest r, Exception e) {
        log.error("[捕获到全局异常 paramException] {}", e.getMessage());
        return new ResultVO(400, "请求参数有误", e.getMessage());
    }

    @ExceptionHandler(Exception.class)
    public ResultVO exceptionHandler(HttpServletRequest r, Exception e) {
        // 记录异常日志
        log.error("[捕获到全局异常Exception]", e);
        if (e.toString().contains("Missing columns"))
            return new ResultVO(CodeEnum.COLUMN_ERR.getCode(), CodeEnum.COLUMN_ERR.getMessage(), null);
        if (e.toString().contains("String index out of range: -1"))
            return new ResultVO(CodeEnum.PARAM_ERROR.getCode(), CodeEnum.PARAM_ERROR.getMessage(), null);
        if (e.toString().contains("Connection refused"))
            return new ResultVO(CodeEnum.DB_ERR.getCode(), CodeEnum.DB_ERR.getMessage(), null);
        if (e.toString().contains("database"))
            return new ResultVO(CodeEnum.SQL_ERR.getCode(), CodeEnum.SQL_ERR.getMessage(), e.getMessage());
        return new ResultVO(CodeEnum.FAIL.getCode(), CodeEnum.FAIL.getMessage(), e.getMessage());
    }
}
