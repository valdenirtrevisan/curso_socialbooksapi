package com.algaworks.socialbooks.controller;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.algaworks.socialbooks.domain.Autor;
import com.algaworks.socialbooks.service.AutorService;

@RestController
@RequestMapping("/autores")
public class AutorController {

  @Autowired
  private AutorService autorService;

  @GetMapping(produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
  public ResponseEntity<List<Autor>> listar() {
    return ResponseEntity.ok().body(autorService.listar());
  }

  @GetMapping("/{id}")
  public ResponseEntity<Autor> listarPorId(@PathVariable Long id) {
    return ResponseEntity.ok(autorService.listarPorId(id));
  }

  @PostMapping
  public ResponseEntity<Void> salvar(@Valid @RequestBody Autor autor) {
    autor = autorService.salvar(autor);
    URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(autor.getId()).toUri();

    return ResponseEntity.created(uri).build();
  }

  @PutMapping("/{id}")
  public ResponseEntity<Void> editar(@Valid @RequestBody Autor autor, @PathVariable Long id) {
    autor.setId(id);
    autorService.editar(autor);

    return ResponseEntity.noContent().build();
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> remover(@PathVariable Long id) {
    autorService.remover(id);

    return ResponseEntity.noContent().build();
  }

}
