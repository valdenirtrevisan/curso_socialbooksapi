package com.algaworks.socialbooks.handler;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.algaworks.socialbooks.domain.DetalhesErro;
import com.algaworks.socialbooks.exception.ServiceException;

@ControllerAdvice
public class ResourceExceptionHandler {

  @ExceptionHandler(ServiceException.class)
  public ResponseEntity<DetalhesErro> handleServiceException(ServiceException e, HttpServletRequest request) {

    DetalhesErro erro = DetalhesErro.builder().status(404l).titulo(e.getMessage())
        .mensagemDesenvolvedor("http://erros.socialbooks.com/404").timestamp(System.currentTimeMillis()).build();

    return ResponseEntity.badRequest().body(erro);
  }

  @ExceptionHandler(Exception.class)
  public ResponseEntity<DetalhesErro> handleException(Exception e, HttpServletRequest request) {
    e.printStackTrace();

    DetalhesErro erro = DetalhesErro.builder().status(500l).titulo(e.getMessage())
        .mensagemDesenvolvedor("http://erros.socialbooks.com/500").timestamp(System.currentTimeMillis()).build();

    return ResponseEntity.internalServerError().body(erro);
  }
}
