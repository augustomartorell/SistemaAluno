package br.com.compasso.sistemaaluno.repository;

import br.com.compasso.sistemaaluno.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public
interface UsuarioRepository
        extends JpaRepository<Usuario, Long> {

    Optional<Usuario> findByNome(String nome);
}
