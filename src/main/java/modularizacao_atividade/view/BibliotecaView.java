package modularizacao_atividade.view;

import modularizacao_atividade.infraestrutura.repository.EmprestimoRepository;
import modularizacao_atividade.infraestrutura.repository.LivroRepository;
import modularizacao_atividade.infraestrutura.repository.UsuarioRepository;
import modularizacao_atividade.model.Emprestimo;
import modularizacao_atividade.model.Livro;
import modularizacao_atividade.service.EmprestimoService;
import modularizacao_atividade.service.LivroService;
import modularizacao_atividade.service.UsuarioService;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

public class BibliotecaView {

    private static boolean LOGIN = true;

    private final static UsuarioService USUARIO_SERVICE = new UsuarioService(new UsuarioRepository());
    private final static LivroService LIVRO_SERVICE = new LivroService(new LivroRepository());
    private final static EmprestimoService EMPRESTIMO_SERVICE = new EmprestimoService(new EmprestimoRepository(), new LivroRepository());




    public static void mostrarMenu(){
        System.out.println("""
                ==============================
                BEM VINDO A BIBLIOTECA VIRTUAL
                ==============================
                1- Cadastrar livro
                -
                2- Cadastrar emprestimo
                -
                3- Devolução de livro
                -
                4- Buscar livros
                ------------------------------
                0- Sair
                ==============================
                """);
    }

    public static void capturarOpcao(Scanner scanner){
        int opcao = scanner.nextInt();
        scanner.nextLine();

        switch (opcao){
            case 0 -> LOGIN = false;
            case 1 -> cadastrarLivro(scanner);
            case 2 -> cadastrarEmprestimo(scanner);
            case 3 -> devolucaoLivro(scanner);
            case 4 -> buscarLivros(scanner);
        }
    }

    public static boolean continuar(){
        return LOGIN;
    }

    private static void buscarLivros(Scanner scanner) {
    }

    private static void devolucaoLivro(Scanner scanner) {
    }

    private static void cadastrarEmprestimo(Scanner scanner) {
        var usuarios = USUARIO_SERVICE.buscarTodos();
        var livros = LIVRO_SERVICE.buscarTodos();

        System.out.println("""
                ===============================
                         EMPRESTIMO
                ===============================
                """);

        System.out.println(" Usuário: ");
        exibirLista(usuarios);

        int input = pegarInputLista(usuarios, scanner);
        long idUser = usuarios.get(input).id();

        System.out.println(" Livro: ");
        exibirLista(livros);

        int inputLivro = pegarInputLista(livros, scanner);
        long idLivro = livros.get(inputLivro).id();

        Emprestimo emprestimo = Emprestimo.toInstance(idLivro, idUser, LocalDate.now(), null);
        EMPRESTIMO_SERVICE.realizarEmprestimo(emprestimo);

    }

    private static int pegarInputLista(List<?> usuarios, Scanner scanner) {
        int selecionado = scanner.nextInt() - 1;
        if(selecionado < 0 || selecionado >= usuarios.size()) throw new RuntimeException("Input invalido");
        return selecionado;
    }

    private static void exibirLista(List<?> usuarios) {
        int contador = 1;
        for (Object usuario : usuarios) {
            System.out.println(contador + " -- " +  usuario);
            contador++;
        }
    }

    private static void cadastrarLivro(Scanner scanner) {
        System.out.println("-------------------------------");
        System.out.println("        Cadastrar Livro");
        System.out.println("-------------------------------");

        System.out.println(" Título do livro: ");
        String titulo = scanner.nextLine();

        System.out.println(" Autor do livro: ");
        String autor = scanner.nextLine();

        System.out.println(" Ano de publicação: ");
        int anoPublicacao = scanner.nextInt();

        Livro livro = Livro.toInstance(titulo, autor, anoPublicacao, true);
        LIVRO_SERVICE.cadastrarLivro(livro);

        System.out.println("|| Livro cadastrado com sucesso");
    }

}
