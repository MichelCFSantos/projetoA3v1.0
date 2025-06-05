package entities;

public class Contato {

    private String nome, email, dataNascimento,telefone;

    public Contato() {

    }

    public Contato(String nome, String telefone, String email, String dataNascimento) {
        this.nome = nome;
        this.telefone = telefone;
        this.email = email;
        this.dataNascimento = dataNascimento;
    }

    public String getNome() {
        return nome;
    }

    public String getTelefone() {
        return telefone;
    }

    public String getEmail() {
        return email;
    }

    public String getDataNascimento() {
        return dataNascimento;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setDataNascimento(String dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public String toString() {
        return ("Nome : " + nome + " /Telefone : " + telefone + " /Email: " + email + " /Data de Nascimento : "
                + dataNascimento);

    }

}
