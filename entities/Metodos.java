package entities;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.io.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Scanner;

public class Metodos {
    Scanner sc = new Scanner(System.in);

    private final String FILE_NAME = "contatos.json";

    ArrayList<Contato> contatos;

    public Metodos() {
        contatos = carregarContatos();
    }

    public void addContato() {
        System.out.println("Escolha um nome: ");
        String nome = sc.nextLine();
        System.out.println("Escolha um Telefone: ");
        String telefone = sc.nextLine();
        System.out.println("Escolha um Email: ");
        String email = sc.nextLine();
        String dataNascimento;
        DateTimeFormatter entradaFormatter = DateTimeFormatter.ofPattern("ddMMyyyy");
        DateTimeFormatter saidaFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        try {
            System.out.println("Informe a sua data de nascimento: (ddMMyyyy)");

            String dataNascimentoRaw = sc.nextLine();

            LocalDate data = LocalDate.parse(dataNascimentoRaw, entradaFormatter);
            dataNascimento = data.format(saidaFormatter);

        } catch (DateTimeParseException e) {
            System.out.println("Formato inválido ou data inexistente. Exemplo correto: 05102000");
            return;
        }
        Contato contato = new Contato(nome, telefone, email, dataNascimento);
        contatos.add(contato);
        System.out.println();
        System.out.println("Contato Adicionado com Sucesso:");
        System.out.println(contato);
        System.out.println();
        salvarContatos();


    }

    public void editContato() {
        try {
            System.out.println("O que você deseja editar?");
            System.out.println("Opções: Nome, Telefone, Email ou Data de nascimento");
            String case2 = sc.nextLine();
            case2 = case2.substring(0, 1).toUpperCase() + case2.substring(1).toLowerCase();
            System.out.println();

            switch (case2) {

                case "Nome":

                    System.out.println("Escreva o nome do contato que deseja editar: ");
                    String n = sc.nextLine();
                    n = n.substring(0, 1).toUpperCase() + n.substring(1).toLowerCase();

                    boolean encontrado = false;

                    for (Contato c : contatos) {
                        if (c.getNome().equalsIgnoreCase(n)) {
                            System.out.println("Qual o novo nome de contato?");
                            String nomea = sc.nextLine();
                            c.setNome(nomea);
                            System.out.println("Contato editado com sucesso: " + c);
                            encontrado = true;
                            break;
                        }
                    }

                    if (!encontrado) {
                        System.out.println("Contato não encontrado.");
                    }

                    break;

                case "Telefone":
                    System.out.println("Escreva o Telefone do contato que deseja editar: ");
                    String t = sc.nextLine();
                    encontrado = false;

                    for (Contato c : contatos) {

                        if (c.getTelefone().equals(t)) {
                            System.out.println("Qual o novo telefone de contato?");
                            String telefonea = sc.nextLine();
                            c.setTelefone(telefonea);
                            System.out.println("Contato editado com sucesso: " + c);
                            encontrado = true;
                            break;
                        }
                    }
                    if (!encontrado) {
                        System.out.println("Telefone não encontrado.");
                    }
                    break;

                case "Email":
                    System.out.println("Escreva o Email do contato que deseja editar: ");
                    String e = sc.nextLine();
                    for (Contato c : contatos) {

                        if (c.getEmail().equals(e)) {
                            System.out.println("Qual o novo email de contato?");
                            String emaila = sc.nextLine();
                            c.setEmail(emaila);
                            System.out.println("Contato editado com sucesso: " + c);
                        }
                    }
                    break;

                case "Data de nascimento":
                    System.out.println("Escreva a data de nascimento do contato que deseja editar: ");
                    String d = sc.nextLine();
                    for (Contato c : contatos) {

                        if (c.getDataNascimento().equals(d)) {
                            System.out.println("Qual a nova data de nascimento do contato?");
                            String dataa = sc.nextLine();
                            c.setDataNascimento(dataa);
                            System.out.println("Contato editado com sucesso: " + c);
                        }
                    }
                    break;

                default:
                    System.out.println("Opção Inválida");
            }
        } catch(StringIndexOutOfBoundsException e) {
            System.out.println("Resultado Nulo, tente novamente.");
            System.out.println();
        }

    salvarContatos();

}

public void excluirContato() {

    System.out.println("Escreva o nome do contato que quer remover?");
    String nome = sc.nextLine();
    boolean removido = contatos.removeIf(contato -> contato.getNome().equalsIgnoreCase(nome));

    if (removido) {
        System.out.println("Contato removido com sucesso");
        salvarContatos();
    } else {
        System.out.println("Contato " + nome + " não encontrado");
    }


}

public void buscarContato() {
    System.out.println("Digite o nome do contato que quer buscar: ");
    String nomeProcurado = sc.next();

    for (Contato c : contatos) {
        if (c.getNome().equalsIgnoreCase(nomeProcurado)) {
            System.out.println("Contato buscado com sucesso: " + c);
        } else {
            System.out.println("Contato " + nomeProcurado + " não encontrado");
            break;
        }

    }

}

public void listContatos() {

    for (Contato listacontato : contatos) {
        System.out.println(listacontato);
    }
}

public boolean isEmpty() {
    if (contatos.isEmpty()) {
        System.out.println("Agenda está vazia, clique \"1\" no menu para adicionar um contato");
        System.out.println();
        return false;

    }
    return true;

}


private void salvarContatos() {
    try (Writer writer = new FileWriter(FILE_NAME)) {
        Gson gson = new Gson();
        gson.toJson(contatos, writer);
    } catch (IOException e) {
        System.out.println("Erro ao salvar contatos: " + e.getMessage());
    }
}

private ArrayList<Contato> carregarContatos() {
    File file = new File(FILE_NAME);
    if (!file.exists()) {
        return new ArrayList<>();
    }

    try (Reader reader = new FileReader(file)) {
        Gson gson = new Gson();
        return gson.fromJson(reader, new TypeToken<ArrayList<Contato>>() {
        }.getType());
    } catch (IOException e) {
        System.out.println("Erro ao carregar contatos: " + e.getMessage());
        return new ArrayList<>();
    }
}

}