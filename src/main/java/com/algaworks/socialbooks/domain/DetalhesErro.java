package com.algaworks.socialbooks.domain;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Builder;
import lombok.Data;

@JsonInclude(Include.NON_NULL)
@Builder
@Data
public class DetalhesErro {

  private String titulo;
  private Long status;
  private Long timestamp;
  private String mensagemDesenvolvedor;
}
