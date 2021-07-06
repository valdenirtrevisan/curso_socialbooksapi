package com.algaworks.socialbooks.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.algaworks.socialbooks.domain.Comentario;
import com.algaworks.socialbooks.domain.Livro;
import com.algaworks.socialbooks.exception.ServiceException;
import com.algaworks.socialbooks.repository.ComentarioRepository;
import com.algaworks.socialbooks.repository.LivroRepository;

@Service
public class LivroService {

  @Autowired
  private LivroRepository livroRepository;
  @Autowired
  private ComentarioRepository comentarioRepository;

  public List<Livro> listar() {
    return livroRepository.findAll();
  }

  public Livro listarPorId(Long id) {
    return livroRepository.findById(id).orElseThrow(() -> new ServiceException("O livro não pôde ser encontrado."));
  }

  public Livro salvar(Livro livro) {
    livro.setId(null);
    return livroRepository.save(livro);
  }

  public void editar(Livro livro) {
    listarPorId(livro.getId());
    livroRepository.save(livro);
  }

  public void remover(Long id) {
    listarPorId(id);
    livroRepository.deleteById(id);
  }

  public Comentario adicionarComentario(Long idLivro, Comentario comentario) {
    Livro livro = listarPorId(idLivro);

    comentario.setLivro(livro);
    comentario.setData(LocalDate.now());
    return comentarioRepository.save(comentario);
  }

  public List<Comentario> listarComentario(Long idLivro) {
    Livro livro = listarPorId(idLivro);
    return livro.getComentarios();
  }

}
