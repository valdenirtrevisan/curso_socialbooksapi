package com.algaworks.socialbooks.domain;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@JsonInclude(Include.NON_NULL)
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
public class Livro {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  @NotNull
  private String nome;
  @JsonFormat(pattern = "dd/MM/yyyy")
  @NotNull
  private LocalDate publicacao;
  @NotNull
  private String editora;
  @NotNull
  @Size(max = 255)
  private String resumo;
  @JsonIgnore
  @OneToMany(mappedBy = "livro", fetch = FetchType.LAZY)
  private List<Comentario> comentarios;
  @ManyToOne
  @JoinColumn(name = "id_autor")
  private Autor autor;
}
