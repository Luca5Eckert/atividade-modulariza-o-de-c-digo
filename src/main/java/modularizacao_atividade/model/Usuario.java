package modularizacao_atividade.model;

import org.jetbrains.annotations.NotNull;

public record Usuario(long id, String nome, String email) {

    public Usuario {
        if(nome == null || nome.isBlank()){
            throw new IllegalArgumentException("O nome do usuario não pode ser nulo");
        }

        if(email == null || email.isBlank()){
            throw new IllegalArgumentException("O email do usuario não pode ser nulo");
        }

    }

    public static Usuario toInstance(long id, String nome, String email){
        return new Usuario(id, nome, email);
    }

    public static Usuario toInstance(String nome, String email){
        return new Usuario(-1, nome, email);
    }

    @Override
    public @NotNull String toString() {
        return " ID: " + id + " | " + nome;
    }
}
