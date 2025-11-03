package modularizacao_atividade.service;

import modularizacao_atividade.infraestrutura.repository.EmprestimoRepository;
import modularizacao_atividade.model.Emprestimo;

public class EmprestimoService {

    private final EmprestimoRepository emprestimoRepository;

    public EmprestimoService(EmprestimoRepository emprestimoRepository) {
        this.emprestimoRepository = emprestimoRepository;
    }


    public void realizarEmprestimo(Emprestimo emprestimo){
        emprestimoRepository.realizarEmprestimo(emprestimo);
    }

    public void realizarDevolucaoLivro(long livro){
        emprestimoRepository.realizarDevolucaoLivro();
    }

}
