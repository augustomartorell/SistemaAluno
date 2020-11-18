package br.com.compasso.sistemaaluno.config.security;

import br.com.compasso.sistemaaluno.modelo.Usuario;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class TokenService {

    @Value("${sistema.jwt.expiration}")
    private String exp;

    @Value("${sistema.jwt.secret}")
    private String secret;

    public String gerarToken(Authentication auth) {
        Usuario logado = (Usuario) auth.getPrincipal();

        Date hoje = new Date();

        Date dataExp = new Date(hoje.getTime() + Long.parseLong((exp)));

        return Jwts.builder()
                   .setIssuer("Sistema de Alunos")
                   .setSubject(logado.getId()
                                     .toString())
                   .setIssuedAt(hoje)
                   .setExpiration(dataExp)
                   .signWith(SignatureAlgorithm.HS256,
                             secret)
                   .compact();
    }

    public boolean isTokenValid(String token) {
        try {
            Jwts.parser()
                .setSigningKey(this.secret)
                .parseClaimsJws(token);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public Long getIdUsuario(String token) {
        Claims claims = Jwts.parser()
                            .setSigningKey(this.secret)
                            .parseClaimsJws(token)
                            .getBody();
        return Long.parseLong(claims.getSubject());
    }
}
