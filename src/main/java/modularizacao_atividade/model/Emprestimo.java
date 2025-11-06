package modularizacao_atividade.model;

import com.mysql.cj.util.TimeUtil;
import org.jetbrains.annotations.NotNull;

import java.time.LocalDate;

public record Emprestimo(long id, long idLivro, long idUsuario, LocalDate dataEmprestimo, LocalDate dataDevolucao) {

    public Emprestimo {
        if(idLivro < 0){
            throw new IllegalArgumentException("O id do livro não pode ser negativo");
        }

        if(idUsuario < 0){
            throw new IllegalArgumentException("O id do usuárop não pode ser negativo");
        }

        if(dataEmprestimo == null){
            throw new IllegalArgumentException("A data de emprestimo não pode ser nula");
        }

    }

    public static Emprestimo toInstance(long id, long idLivro, long idUsuario, LocalDate dataEmprestimo, LocalDate dataDevolucao){
        return new Emprestimo(id, idLivro, idUsuario, dataEmprestimo, dataDevolucao);
    }

    public static Emprestimo toInstance(long idLivro, long idUsuario, LocalDate dataEmprestimo, LocalDate dataDevolucao){
        return new Emprestimo(-1, idLivro, idUsuario, dataEmprestimo, dataDevolucao);
    }

    @Override
    public @NotNull String toString() {
        String emprestimo = dataEmprestimo.format(TimeUtil.DATE_FORMATTER);
        String devolucao = dataDevolucao != null ? dataDevolucao.format(TimeUtil.DATE_FORMATTER) : " ainda não devolvido";

        return "Livro: " + idLivro + "; Emprestimo: " + emprestimo + "; Devolução: " + devolucao;
    }
}
