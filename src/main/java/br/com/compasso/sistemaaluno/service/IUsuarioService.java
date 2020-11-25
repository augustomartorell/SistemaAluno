package br.com.compasso.sistemaaluno.service;

import br.com.compasso.sistemaaluno.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface IUsuarioService
        extends JpaRepository<Usuario, Long> {

    Optional<Usuario> findByNome(String nome);

}
