package com.algaworks.socialbooks.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.algaworks.socialbooks.domain.Autor;
import com.algaworks.socialbooks.exception.ServiceException;
import com.algaworks.socialbooks.repository.AutorRepository;

@Service
public class AutorService {

  @Autowired
  private AutorRepository autorRepository;

  public List<Autor> listar() {
    return autorRepository.findAll();
  }

  public Autor listarPorId(Long id) {
    return autorRepository.findById(id).orElseThrow(() -> new ServiceException("O autor não pôde ser encontrado."));
  }

  public Autor salvar(Autor autor) {
    autor.setId(null);
    return autorRepository.save(autor);
  }

  public void editar(Autor autor) {
    listarPorId(autor.getId());
    autorRepository.save(autor);
  }

  public void remover(Long id) {
    listarPorId(id);
    autorRepository.deleteById(id);
  }

}
