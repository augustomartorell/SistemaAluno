package br.com.compasso.sistemaaluno.controller.dto;

import br.com.compasso.sistemaaluno.modelo.Usuario;
import org.springframework.data.domain.Page;

public class UsuarioDto {

    private Long id;
    private String nome;
    private String senha;

    public UsuarioDto(Usuario usuario) {
        this.id = usuario.getId();
        this.nome = usuario.getNome();
        this.senha = usuario.getSenha();
    }

    public static Page<UsuarioDto> converter(Page<Usuario> usuarios) {
        return usuarios.map(UsuarioDto::new);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }
}
