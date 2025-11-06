package modularizacao_atividade.model;

import com.mysql.cj.util.TimeUtil;
import org.jetbrains.annotations.NotNull;

public record Livro(long id, String titulo, String autor, int ano, boolean disponivel) {

    public Livro {
        if(titulo == null || titulo.isBlank()){
            throw new IllegalArgumentException("O título do livro não pode ser nulo");
        }

        if(autor == null || autor.isBlank()){
            throw new IllegalArgumentException("O autor do livro não pode ser nulo");
        }

        if(ano < 0){
            throw new IllegalArgumentException("O ano do livro não pode ser negativo");
        }

    }

    public static Livro toInstance(long id, String titulo, String autor, int ano, boolean disponivel){
        return new Livro(id, titulo, autor, ano, disponivel);
    }

    public static Livro toInstance(String titulo, String autor, int ano, boolean disponivel){
        return new Livro(-1, titulo, autor, ano, disponivel);
    }

    @Override
    public @NotNull String toString() {
        return "Titulo: " + titulo + "; Autor: " + autor + "; Disponivel: " + disponivel;
    }

}
