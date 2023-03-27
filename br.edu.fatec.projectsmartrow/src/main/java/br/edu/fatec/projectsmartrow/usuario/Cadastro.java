package br.edu.fatec.projectsmartrow.usuario;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Cadastro {
	private Usuario[] usuarios = new Usuario[10]; 
    private int qtdUsuarios = 0; 
    
    public boolean validarNome(String nome) {
        if (nome == null || nome.isEmpty()) {
            System.out.println("Nome não pode ser vazio! Digite novamente:");
            return false;
        }
        return true;
    }

    public boolean validarSenha(String senha) {
        if (senha == null || senha.isEmpty()) {
            System.out.println("Senha não pode ser vazia! Digite novamente:");
            return false;
        }
        return true;
    }
    public boolean validarEmail(String email) {
        String emailRegex = "^[A-Za-z0-9+_.-]+@[gmail.com, hotmail.com, outlook.com, yahoo.com]+$";
        Pattern pattern = Pattern.compile(emailRegex);
        Matcher matcher = pattern.matcher(email);

        if (!matcher.matches()) {
            System.out.println("Email inválido!");
            return false;
        }
        return true;
    }
    public boolean validarCpf(String cpf) {
        if (cpf == null || cpf.isEmpty()) {
            System.out.println("CPF não pode ser vazio! Digite novamente:");
            return false;
        }
        if (!cpf.matches("\\d+")) {
            System.out.println("CPF deve conter apenas números! Digite novamente:");
            return false;
        }
        if (cpf.length() != 11) {
            System.out.println("CPF deve ter exatamente 11 dígitos! Digite novamente:");
            return false;
        }
        return true;
    }


 
    public void adicionarUsuario(Usuario usuario) {
        if (!validarNome(usuario.getNome())) {
            return;
        }
        if (!validarEmail(usuario.getEmail())) {
            return;
        }
        if (!validarSenha(usuario.getSenha())) {
            return;
        }
        if (!validarCpf(usuario.getCPF ())) {
            return;
        }
        usuarios[qtdUsuarios] = usuario;
        qtdUsuarios++;
        System.out.println("Usuário adicionado com sucesso!");
    }
    public Usuario lerUsuario(int indice) {
        if (indice >= 0 && indice < qtdUsuarios) {
            return usuarios[indice];
        } else {
            return null;
        }
    }
    public void atualizarUsuario(int indice, String nome, String email, String senha, String cpf) {
        if (indice >= 0 && indice < qtdUsuarios) {
            Usuario usuario = usuarios[indice];
            usuario.setNome(nome);
            usuario.setEmail(email);
            usuario.setSenha(senha);
			usuario.setCPF(cpf);
            System.out.println("Usuário atualizado com sucesso!");
        } else {
            System.out.println("Índice inválido!");
        }
    }
    public void deletarUsuario(int indice) {
        if (indice >= 0 && indice < qtdUsuarios) {
            for (int i = indice; i < qtdUsuarios - 1; i++) {
                usuarios[i] = usuarios[i+1];
            }
            usuarios[qtdUsuarios-1] = null;
            qtdUsuarios--;
            System.out.println("Usuário deletado com sucesso!");
        } else {
            System.out.println("Índice inválido!");
        }
    } 
}

