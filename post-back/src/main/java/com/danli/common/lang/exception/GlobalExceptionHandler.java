package com.danli.common.lang.exception;

import com.danli.common.lang.Result;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.ShiroException;
import org.apache.shiro.authz.UnauthorizedException;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * This class does global exception handling
 *
 *
 * @author Yicong Wang
 * @date 2022/3/22
 */
@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    /**
     * Catch Shiro's authorization exception
     */
    @ExceptionHandler(value = UnauthorizedException.class)
    @ResponseBody
    public Result jsonExceptionHandler(HttpServletRequest req, Exception e) {
        log.error("Insufficient permissions:-------------->{}", e.getMessage());
        return Result.fail(403, "Your level is not high enough.",null);
    }

    /**
     * Catch Shiro's authentication exception
     */
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ExceptionHandler(ShiroException.class)
    public Result handle401(ShiroException e) {
        log.error("Unlogged access:-------------->{}", e.getMessage());
        return Result.fail(401, e.getMessage(), null);
    }

    /**
     * Handle exceptions for Assert
     */
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(value = IllegalArgumentException.class)
    public Result handler(IllegalArgumentException e) throws IOException {
        log.error("Abnormal Assert:-------------->{}", e.getMessage());
        return Result.fail(e.getMessage());
    }

    /**
     * @Validated Method parameter exception handling
     */
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public Result handler(MethodArgumentNotValidException e) throws IOException {
        log.error("Abnormal method parameter:-------------->", e);
        BindingResult bindingResult = e.getBindingResult();
        ObjectError objectError = bindingResult.getAllErrors().stream().findFirst().get();
        return Result.fail(objectError.getDefaultMessage());
    }

    /**
     * @Validated Verification error exception processing
     */
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(value = RuntimeException.class)
    public Result handler(RuntimeException e) throws IOException {
        log.error("Runtime exception:-------------->", e);
        return Result.fail(e.getMessage());
    }
}
