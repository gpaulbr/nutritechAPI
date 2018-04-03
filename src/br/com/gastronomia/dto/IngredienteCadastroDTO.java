package br.com.gastronomia.dto;

import br.com.gastronomia.util.TipoDeIngrediente;

public class IngredienteCadastroDTO {
    private String nome;
    private long idCriador;
    private String origem;
    private boolean status;
    private TipoDeIngrediente tipo;

    public IngredienteCadastroDTO() { }

    public String getNome() {
        return nome;
    }

    public long getIdCriador() {
        return idCriador;
    }

    public String getOrigem() {
        return origem;
    }

    public boolean getStatus() {
        return status;
    }

    public TipoDeIngrediente getTipo() {
        return tipo;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setIdCriador(long idCriador) {
        this.idCriador = idCriador;
    }

    public void setOrigem(String origem) {
        this.origem = origem;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public void setTipo(TipoDeIngrediente tipo) {
        this.tipo = tipo;
    }
}
