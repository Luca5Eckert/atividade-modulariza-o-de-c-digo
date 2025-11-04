package modularizacao_atividade.infraestrutura.repository;

import modularizacao_atividade.infraestrutura.conexao.Conexoes;
import modularizacao_atividade.model.Livro;
import modularizacao_atividade.model.Usuario;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UsuarioRepository {

    public List<Usuario> buscarTodos() {
        List<Usuario> usuarios = new ArrayList<>();
        String consulta = """
                SELECT
                    id,
                    nome,
                    email
                FROM
                    usuarios
                """;

        try (Connection connection = Conexoes.toInstance();
             PreparedStatement statement = connection.prepareStatement(consulta);
             ResultSet resultSet = statement.executeQuery()){

            while(resultSet.next()){
                long id = resultSet.getLong("id");
                String nome = resultSet.getString("nome");
                String email = resultSet.getString("email");

                Usuario usuario = Usuario.toInstance(id, nome, email);
                usuarios.add(usuario);
            }

        } catch (SQLException sqlException){
            throw new RuntimeException("[ERRO] BANCO DE DADOS: " + sqlException.getMessage());
        }

        return usuarios;
    }
}
