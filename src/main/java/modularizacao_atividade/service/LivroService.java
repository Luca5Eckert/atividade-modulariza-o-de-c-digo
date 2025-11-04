package modularizacao_atividade.service;

import modularizacao_atividade.infraestrutura.repository.LivroRepository;
import modularizacao_atividade.model.Livro;

import java.util.List;

public class LivroService {

    private final LivroRepository livroRepository;

    public LivroService(LivroRepository livroRepository) {
        this.livroRepository = livroRepository;
    }

    public void cadastrarLivro(Livro livro){
        livroRepository.cadastrar(livro);
    }

    public List<Livro> buscarTodos(boolean disponivel){
        return livroRepository.buscarTodosDisponivel(disponivel);
    }

}


