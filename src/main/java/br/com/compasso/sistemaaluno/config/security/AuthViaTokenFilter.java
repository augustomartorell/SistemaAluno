package br.com.compasso.sistemaaluno.config.security;

import br.com.compasso.sistemaaluno.modelo.Usuario;
import br.com.compasso.sistemaaluno.repository.UsuarioRepository;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AuthViaTokenFilter
        extends OncePerRequestFilter {

    private TokenService tokenService;
    private UsuarioRepository repository;

    public AuthViaTokenFilter(TokenService tokenService,
                              UsuarioRepository usuarioRepository) {
        this.tokenService = tokenService;
        this.repository = usuarioRepository;
    }

    private String retrieveToken(HttpServletRequest request) {
        String token = request.getHeader("Authorization");
        if (token == null || token.isEmpty() || !token.startsWith("Bearer "))
            return null;
        return token.substring(7,
                               token.length());
    }

    private void authClient(String token) {
        Long idUsuario = tokenService.getIdUsuario(token);
        Usuario usuario = repository.findById(idUsuario)
                                    .get();
        UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(usuario,
                                                                                           null,
                                                                                           usuario.getAuthorities());
        SecurityContextHolder.getContext()
                             .setAuthentication(auth);
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain)
            throws
            ServletException,
            IOException {
        String token = retrieveToken(request);
        boolean valid = tokenService.isTokenValid(token);
        if (valid)
            authClient(token);
        filterChain.doFilter(request,
                             response);
    }

}
