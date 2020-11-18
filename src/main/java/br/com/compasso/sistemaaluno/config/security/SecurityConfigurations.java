package br.com.compasso.sistemaaluno.config.security;


import br.com.compasso.sistemaaluno.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@EnableWebSecurity
@Configuration
public class SecurityConfigurations
        extends WebSecurityConfigurerAdapter {

    @Autowired
    AuthService authService;

    @Autowired
    private TokenService tokenService;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    @Bean
    protected AuthenticationManager authenticationManager()
            throws
            Exception {
        return super.authenticationManager();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder authManagerBuilder)
            throws
            Exception {
        authManagerBuilder.userDetailsService(authService)
                          .passwordEncoder(new BCryptPasswordEncoder());
    }

    @Override
    protected void configure(HttpSecurity http)
            throws
            Exception {
        http.authorizeRequests()
            .antMatchers(HttpMethod.GET,
                         "/alunos")
            .permitAll()
            .antMatchers(HttpMethod.GET,
                         "/alunos/*")
            .permitAll()
            .antMatchers(HttpMethod.POST,
                         "/auth")
            .permitAll()
            .anyRequest()
            .authenticated()
            .and()
            .csrf()
            .disable()
            .sessionManagement()
            .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            .and()
            .addFilterBefore(new AuthViaTokenFilter(tokenService,
                                                    usuarioRepository),
                             UsernamePasswordAuthenticationFilter.class);
    }

    @Override
    public void configure(WebSecurity web)
            throws
            Exception {
        web.ignoring()
           .antMatchers("/**.html",
                        "/v2/api-docs",
                        "/webjars/**",
                        "/configuration/**",
                        "/swagger-resources/**");
    }



    /*Encode manual para senha de usuário,
    necessário para inclusão no banco e posteriormente validação de acesso*/
//    public static void main(String[] args) {
//        System.out.println(new BCryptPasswordEncoder().encode(""));
//    }
}
