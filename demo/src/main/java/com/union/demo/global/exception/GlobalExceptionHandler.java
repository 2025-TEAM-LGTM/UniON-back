package com.union.demo.global.exception;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

//ErrorDto 이용해서 에러 만들면 될듯!
@RestControllerAdvice
public class GlobalExceptionHandler {
    //1. validation 에러(dto 검증 실패)
    //2. 로그인 안되어있는 상태 오류 처리(401,403에러)
    //3. 404 에러
    //4. path, query 파라미터 타입 오류
    //5. db 제약 위반 처리
    //6. 알 수 없는 예외.

}
