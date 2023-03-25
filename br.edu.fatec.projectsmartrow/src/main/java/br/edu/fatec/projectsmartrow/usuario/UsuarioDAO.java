package br.edu.fatec.projectsmartrow.usuario;

import java.util.ArrayList;
import java.util.List;

public class UsuarioDAO {
    private List<Usuario> usuarios;

    public UsuarioDAO() {
        this.usuarios = new ArrayList<>();
    }

    public void create(Usuario usuario) {
        usuarios.add(usuario);
    }

    public Usuario read(String nome) {
        for (Usuario usuario : usuarios) {
            if (usuario.getNome().equals(nome)) {
                return usuario;
            }
        }
        return null;
    }

    public List<Usuario> readAll() {
        return usuarios;
    }

    public void update(Usuario usuarioAtualizado) {
        Usuario usuario = read(usuarioAtualizado.getNome());
        if (usuario != null) {
            usuario.setNome(usuarioAtualizado.getNome());
            usuario.setSenha(usuarioAtualizado.getSenha());
            usuario.setCPF(usuarioAtualizado.getCPF());
        }
    }

    public void delete(String nome) {
        Usuario usuario = read(nome);
        if (usuario != null) {
            usuarios.remove(usuario);
        }
    }
}

