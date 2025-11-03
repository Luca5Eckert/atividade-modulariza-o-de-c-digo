package modularizacao_atividade;

import modularizacao_atividade.view.BibliotecaView;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while(BibliotecaView.continuar()){
            BibliotecaView.mostrarMenu();
            BibliotecaView.capturarOpcao(scanner);
        }

    }
}