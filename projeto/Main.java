package projeto;

import java.util.Scanner;

import entities.Metodos;

public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Metodos contatos = new Metodos();
        String opcao;


        do {

            System.out.println("Menu Agenda");
            System.out.println("1. Adicionar Contato");
            System.out.println("2. Editar Contato");
            System.out.println("3. Remover Contato");
            System.out.println("4. Buscar Contato");
            System.out.println("5. Listar Contatos");
            System.out.println("0. SAIR");
            System.out.println("Escolha uma opção");
            opcao = sc.nextLine();

            switch (opcao) {

                case "1":
                    contatos.addContato();
                    break;

                case "2":
                    if (contatos.isEmpty()) { // Se existir contato na lista, o metodo Editar é chamado
                        contatos.editContato();
                    }
                    break;


                case "3":
                    if (contatos.isEmpty()) {
                        contatos.excluirContato();
                    }
                    break;

                case "4":
                    if (contatos.isEmpty()) {
                        contatos.buscarContato();
                    }
                    break;


                case "5":

                    if (contatos.isEmpty()) {
                        contatos.listContatos();
                    }
                    break;


                case "0":
                    System.out.println("Saindo..."); // encerra o programa imediatamente
                    break;
                default:
                    System.out.println("Opção Inválida");
            }

        } while (!opcao.equals("0"));

        sc.close();

    }
}
