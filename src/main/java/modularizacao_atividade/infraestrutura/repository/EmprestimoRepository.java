package modularizacao_atividade.infraestrutura.repository;

import modularizacao_atividade.infraestrutura.conexao.Conexoes;
import modularizacao_atividade.model.Emprestimo;
import modularizacao_atividade.model.Livro;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class EmprestimoRepository {

    public void realizarEmprestimo(Emprestimo emprestimo) {
        String consulta = """
                INSERT INTO emprestimos
                    (
                        livro_id,
                        usuario_id,
                        data_emprestimo
                    )
                VALUES
                    (
                        ?,
                        ?,
                        ?
                    )
                """;

        try (Connection connection = Conexoes.toInstance();
             PreparedStatement statement = connection.prepareStatement(consulta)){

            statement.setLong(1, emprestimo.idLivro());
            statement.setLong(2, emprestimo.idUsuario());
            statement.setDate(3, Date.valueOf(emprestimo.dataEmprestimo()));

            statement.executeUpdate();

        } catch (SQLException sqlException){
            throw new RuntimeException("[ERRO] BANCO DE DADOS: " + sqlException.getMessage());
        }

    }

    public void realizarDevolucaoLivro(long idLivro) {
        String consulta = """
                UPDATE
                    emprestimos
                SET
                    data_devolucao = ?
                WHERE
                    livro_id = ? AND data_devolucao = ?
                """;

        try (Connection connection = Conexoes.toInstance();
             PreparedStatement statement = connection.prepareStatement(consulta)){

            statement.setDate(1, Date.valueOf(LocalDate.now()));
            statement.setLong(2, idLivro);
            statement.setDate(3, null);

            statement.executeUpdate();

        } catch (SQLException sqlException){
            throw new RuntimeException("[ERRO] BANCO DE DADOS: " + sqlException.getMessage());
        }

    }

    public List<Emprestimo> buscarTodos() {
        List<Emprestimo> emprestimos = new ArrayList<>();
        String consulta = """
                SELECT
                    id,
                    livro_id,
                    usuario_id,
                    data_emprestimo,
                    data_devolucao
                FROM
                    emprestimos
                """;

        try (Connection connection = Conexoes.toInstance();
             PreparedStatement statement = connection.prepareStatement(consulta);
             ResultSet resultSet = statement.executeQuery()){

            while(resultSet.next()){
                long id = resultSet.getLong("id");
                long idLivro = resultSet.getLong("livro_id");
                long idUsuario = resultSet.getLong("usuario_id");
                LocalDate dataEmprestimo = resultSet.getDate("data_emprestimo").toLocalDate();
                LocalDate dataDevolucao = resultSet.getDate("data_devolucao").toLocalDate();

                Emprestimo emprestimo = Emprestimo.toInstance(id, idLivro, idUsuario, dataEmprestimo, dataDevolucao);
                emprestimos.add(emprestimo);
            }

        } catch (SQLException sqlException){
            throw new RuntimeException("[ERRO] BANCO DE DADOS: " + sqlException.getMessage());
        }

        return emprestimos;
    }
}
