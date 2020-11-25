package br.com.compasso.sistemaaluno.service;

import br.com.compasso.sistemaaluno.model.Usuario;
import br.com.compasso.sistemaaluno.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;


public abstract class UsuarioService
        implements IUsuarioService {

    @Autowired
    UsuarioRepository usuarioRepository;

    @Override
    public Optional<Usuario> findByNome(String nome) {
        return usuarioRepository.findByNome(nome);
    }
}
