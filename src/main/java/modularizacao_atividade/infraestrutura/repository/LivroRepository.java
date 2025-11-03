package modularizacao_atividade.infraestrutura.repository;

import modularizacao_atividade.infraestrutura.conexao.Conexoes;
import modularizacao_atividade.model.Livro;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class LivroRepository {

    public void cadastrar(Livro livro) {
        String consulta = """
                INSERT INTO livro
                    (
                        titulo,
                        autor,
                        anopublicacao,
                        disponivel
                    )
                VALUES
                    (
                        ?,
                        ?,
                        ?,
                        ?
                    )
                """;

        try (Connection connection = Conexoes.toInstance();
             PreparedStatement statement = connection.prepareStatement(consulta)){

            statement.setString(1, livro.titulo());
            statement.setString(2, livro.autor());
            statement.setInt(3, livro.ano());
            statement.setBoolean(4, livro.disponivel());

        } catch (SQLException sqlException){
            throw new RuntimeException("[ERRO] BANCO DE DADOS: " + sqlException.getMessage());
        }
    }


    public List<Livro> buscarTodos() {
        List<Livro> livros = new ArrayList<>();
        String consulta = """
                SELECT
                    id,
                    titulo, 
                    autor, 
                    anopublicacao, 
                    disponivel
                FROM 
                    livro
                """;

        try (Connection connection = Conexoes.toInstance();
            PreparedStatement statement = connection.prepareStatement(consulta);
             ResultSet resultSet = statement.executeQuery()){

            while(resultSet.next()){
                long id = resultSet.getLong("id");
                String titulo = resultSet.getString("titulo");
                String autor = resultSet.getString("autor");
                int anoPublicacao = resultSet.getInt("anopublicacao");
                boolean disponivel = resultSet.getBoolean("disponivel");

                Livro livro = Livro.toInstance(id, titulo, autor, anoPublicacao, disponivel);
                livros.add(livro);
            }

        } catch (SQLException sqlException){
            throw new RuntimeException("[ERRO] BANCO DE DADOS");
        }

        return livros;
    }
}
