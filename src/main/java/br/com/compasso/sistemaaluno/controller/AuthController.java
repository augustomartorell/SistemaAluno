package br.com.compasso.sistemaaluno.controller;

import br.com.compasso.sistemaaluno.config.security.TokenService;
import br.com.compasso.sistemaaluno.controller.dto.TokenDto;
import br.com.compasso.sistemaaluno.controller.form.LoginForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthenticationManager authManager;

    @Autowired
    private TokenService tokenService;

    @PostMapping
    public ResponseEntity<TokenDto> authenticate(
            @RequestBody
            @Valid LoginForm form) {
        UsernamePasswordAuthenticationToken login = form.converter();

        try {
            Authentication auth = authManager.authenticate(login);
            String token = tokenService.gerarToken(auth);
            HttpHeaders headers = new HttpHeaders();
            headers.setBearerAuth(token);
            return ResponseEntity.ok(new TokenDto(token,
                                                  "Bearer"));
        } catch (AuthenticationException e) {
            return ResponseEntity.badRequest()
                                 .build();
        }
    }

//    @PostMapping("/cadastrar")
//    @Transactional
//    public ResponseEntity<UsuarioDto> cadastrar(@RequestBody @Valid UsuarioForm form)
}
