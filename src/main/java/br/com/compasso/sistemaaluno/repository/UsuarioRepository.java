package br.com.compasso.sistemaaluno.repository;

import br.com.compasso.sistemaaluno.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public
interface UsuarioRepository
        extends JpaRepository<Usuario, Long> {
}
