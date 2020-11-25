package br.com.compasso.sistemaaluno.config.security;

import br.com.compasso.sistemaaluno.model.Usuario;
import br.com.compasso.sistemaaluno.service.IUsuarioService;
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
    private IUsuarioService usuarioService;

    @Override
    public UserDetails loadUserByUsername(String username)
            throws
            UsernameNotFoundException {
        Optional<Usuario> usuario = usuarioService.findByNome(username);
        if (usuario.isPresent())
            return usuario.get();

        throw new UsernameNotFoundException("Usuário ou senha incorretos.");
    }
}
