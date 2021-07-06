package com.algaworks.socialbooks.controller;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.algaworks.socialbooks.domain.Comentario;
import com.algaworks.socialbooks.domain.Livro;
import com.algaworks.socialbooks.service.LivroService;

@RestController
@RequestMapping("/livros")
public class LivroController {

  @Autowired
  private LivroService livroService;

  @CrossOrigin
  @GetMapping
  public ResponseEntity<List<Livro>> listar() {
    return ResponseEntity.ok().body(livroService.listar());
  }

  @GetMapping("/{id}")
  public ResponseEntity<Livro> listarPorId(@PathVariable Long id) {
    Livro livro = livroService.listarPorId(id);

    return ResponseEntity.ok().body(livro);
  }

  @PostMapping
  public ResponseEntity<Void> salvar(@Valid @RequestBody Livro livro) {
    livro = livroService.salvar(livro);
    URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(livro.getId()).toUri();

    return ResponseEntity.created(uri).build();
  }

  @PutMapping("/{id}")
  public ResponseEntity<Void> editar(@Valid @RequestBody Livro livro, @PathVariable Long id) {
    livro.setId(id);
    livroService.editar(livro);

    return ResponseEntity.noContent().build();
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> remover(@PathVariable Long id) {
    livroService.remover(id);

    return ResponseEntity.noContent().build();
  }

  @GetMapping("/{idLivro}/comentarios")
  public ResponseEntity<List<Comentario>> listarComentarios(@PathVariable Long idLivro) {
    List<Comentario> comentarios = livroService.listarComentario(idLivro);

    return ResponseEntity.ok().body(comentarios);
  }

  @PostMapping("/{idLivro}/comentarios")
  public ResponseEntity<Void> adicionarComentario(@PathVariable Long idLivro,
      @Valid @RequestBody Comentario comentario) {
    Authentication auth = SecurityContextHolder.getContext().getAuthentication();
    comentario.setUsuario(auth.getName());

    comentario = livroService.adicionarComentario(idLivro, comentario);
    URI uri = ServletUriComponentsBuilder.fromCurrentRequest().build().toUri();

    return ResponseEntity.created(uri).build();
  }
}
