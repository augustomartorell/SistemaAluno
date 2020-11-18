package br.com.compasso.sistemaaluno.repository;

import br.com.compasso.sistemaaluno.modelo.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public
interface UsuarioRepository
        extends JpaRepository<Usuario, Long> {

    Optional<Usuario> findByNome(String nome);
}
