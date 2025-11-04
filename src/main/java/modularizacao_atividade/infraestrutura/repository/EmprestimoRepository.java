package modularizacao_atividade.infraestrutura.repository;

import modularizacao_atividade.infraestrutura.conexao.Conexoes;
import modularizacao_atividade.model.Emprestimo;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;

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
}
