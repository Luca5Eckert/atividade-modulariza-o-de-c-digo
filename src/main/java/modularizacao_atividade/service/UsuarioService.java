package modularizacao_atividade.service;

import modularizacao_atividade.infraestrutura.repository.UsuarioRepository;
import modularizacao_atividade.model.Usuario;

import java.util.List;

public class UsuarioService {
    private final UsuarioRepository usuarioRepository;

    public UsuarioService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    public List<Usuario> buscarTodos() {
        return usuarioRepository.buscarTodos();
    }
}
