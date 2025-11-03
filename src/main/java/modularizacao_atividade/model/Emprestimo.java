package modularizacao_atividade.model;

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

        if(dataDevolucao == null){
            throw new IllegalArgumentException("A data de devolução não pode ser nula");
        }

        if(dataEmprestimo.isAfter(dataDevolucao)){
            throw new IllegalArgumentException("A data de devolução precisa ser antes da data do emprestimo");
        }

    }

    public static Livro toInstance(long id, String titulo, String autor, int ano, boolean disponivel){
        return new Livro(id, titulo, autor, ano, disponivel);
    }

    public static Livro toInstance(String titulo, String autor, int ano, boolean disponivel){
        return new Livro(-1, titulo, autor, ano, disponivel);
    }
}
