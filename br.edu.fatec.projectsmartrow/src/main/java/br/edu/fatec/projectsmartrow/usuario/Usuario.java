package br.edu.fatec.projectsmartrow.usuario;

public class Usuario {
	private String nome;
    private String email;
    private String senha;
	private String cpf;
    public Usuario(String nome, String email, String senha, String cpf) {
        this.nome = nome;
        this.email = email;
        this.senha = senha;
        this.cpf = cpf;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
       
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }
    public String getCPF() {
        return cpf;
    }

    public void setCPF(String cpf) {
        this.cpf = cpf;
    }
    public String toString() {
        return "Nome: " + nome + "\n" +
               "Email: " + email + "\n" +
               "Senha: " + senha + "\n" +
               "Endereço: " + cpf + "\n";
    }

}
