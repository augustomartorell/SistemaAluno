package br.com.compasso.sistemaaluno.config.security;

import br.com.compasso.sistemaaluno.modelo.Usuario;
import br.com.compasso.sistemaaluno.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthService
        implements UserDetailsService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    public UserDetails loadUserByUsername(String username)
            throws
            UsernameNotFoundException {
        Optional<Usuario> usuario = usuarioRepository.findByNome(username);
        if (usuario.isPresent())
            return usuario.get();

        throw new UsernameNotFoundException("Usuário ou senha incorretos.");
    }
}
