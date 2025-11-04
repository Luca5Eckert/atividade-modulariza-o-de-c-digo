package modularizacao_atividade.service;

import modularizacao_atividade.infraestrutura.repository.EmprestimoRepository;
import modularizacao_atividade.infraestrutura.repository.LivroRepository;
import modularizacao_atividade.model.Emprestimo;

public class EmprestimoService {

    private final EmprestimoRepository emprestimoRepository;
    private final LivroRepository livroRepository;

    public EmprestimoService(EmprestimoRepository emprestimoRepository, LivroRepository livroRepository) {
        this.emprestimoRepository = emprestimoRepository;
        this.livroRepository = livroRepository;
    }


    public void realizarEmprestimo(Emprestimo emprestimo){
        emprestimoRepository.realizarEmprestimo(emprestimo);

        livroRepository.mudarDisponibilidade(emprestimo.idLivro(), false);
    }

    public void realizarDevolucaoLivro(Emprestimo emprestimo){
        emprestimoRepository.realizarDevolucaoLivro(emprestimo.id());

        livroRepository.mudarDisponibilidade(emprestimo.idLivro(), true);
    }

}
