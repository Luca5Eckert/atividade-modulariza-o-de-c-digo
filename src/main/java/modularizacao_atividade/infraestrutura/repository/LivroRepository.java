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
                INSERT INTO livros
                    (
                        titulo,
                        autor,
                        ano,
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

            statement.executeUpdate();

        } catch (SQLException sqlException){
            throw new RuntimeException("[ERRO] BANCO DE DADOS: " + sqlException.getMessage());
        }
    }


    public List<Livro> buscarTodosDisponivel(boolean disponivel) {
        List<Livro> livros = new ArrayList<>();
        String consulta = """
                SELECT
                    id,
                    titulo,
                    autor,
                    ano,
                    disponivel
                FROM
                    livros
                WHERE
                    disponivel = ?
                """;

        try (Connection connection = Conexoes.toInstance();
             PreparedStatement statement = criarConsultaComFiltro(consulta, disponivel, connection);
             ResultSet resultSet = statement.executeQuery()){

            while(resultSet.next()){
                long id = resultSet.getLong("id");
                String titulo = resultSet.getString("titulo");
                String autor = resultSet.getString("autor");
                int anoPublicacao = resultSet.getInt("ano");
                boolean disponivelDb = resultSet.getBoolean("disponivel");

                Livro livro = Livro.toInstance(id, titulo, autor, anoPublicacao, disponivelDb);
                livros.add(livro);
            }

        } catch (SQLException sqlException){
            throw new RuntimeException("[ERRO] BANCO DE DADOS: " + sqlException.getMessage());
        }

        return livros;
    }

    private PreparedStatement criarConsultaComFiltro(String consulta, boolean b, Connection connection) throws SQLException {
        PreparedStatement statement = connection.prepareStatement(consulta);
        statement.setBoolean(1, b);
        return statement;
    }

    public void mudarDisponibilidade(long idLivro, boolean estado) {
        String query = """
                UPDATE
                    livros
                SET
                    disponivel = ?
                WHERE
                    id = ?
                """;

        try (Connection connection = Conexoes.toInstance();
            PreparedStatement statement = connection.prepareStatement(query)){

            statement.setBoolean(1, estado);
            statement.setLong(2, idLivro);

            statement.executeUpdate();

        } catch (SQLException sqlException){
            throw new RuntimeException("[ERRO] BANCO DE DADOS: " + sqlException.getMessage());
        }

    }
}
