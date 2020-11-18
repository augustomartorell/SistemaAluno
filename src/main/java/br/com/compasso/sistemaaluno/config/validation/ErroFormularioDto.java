package br.com.compasso.sistemaaluno.config.validation;

public class ErroFormularioDto {

    private String campo;
    private String erro;

    public ErroFormularioDto(String campo,
                             String erro) {
        this.campo = campo;
        this.erro = erro;
    }

    public void setCampo(String campo) {
        this.campo = campo;
    }

    public void setErro(String erro) {
        this.erro = erro;
    }
}
