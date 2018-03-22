package br.com.gastronomia.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import java.util.List;

/**
 * Classe modelo para o acesso ao banco de dados.
 *
 * @author Bolivar Pereira - bolivar.pereira@acad.pucrs.br
 * @since 11/08/2017
 *
 **/
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name= "Receita")
@JsonIgnoreProperties(ignoreUnknown = true)
public class Receita implements Serializable {

    private static final long serialVersionUID = -789863172532826108L;
    private enum Tipo {
        PUBLICO, PRIVADO, NULL
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "IdReceita")
    private long id;

    @NotEmpty
    @Column(name = "Nome", unique = true)
    private String nome;

    @Column(name= "Status", nullable = false)
    private boolean status;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name="Passos", joinColumns=@JoinColumn(name="IdReceita"))
    @Column(name = "Passos", nullable = false)
    private List<String> passos = new ArrayList<String>();

    @Column(name = "Rendimento", nullable = false)
    private double rendimento;

    @Column(name = "Tempo", nullable = false)
    private String tempo;

    @Column(name = "Imagem", nullable = false)
    private String imagem;

    @Column(name = "Tipo", nullable = false)
    private Tipo tipo;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name="ReceitaUsuario", joinColumns=
            {@JoinColumn(name="IdReceita")}, inverseJoinColumns=
            {@JoinColumn(name="IdUsuario")})
    private Set<Usuario> criadores = new HashSet<>();

    @OneToMany(
            mappedBy = "receita",
            fetch = FetchType.EAGER,
            cascade = {
                    CascadeType.ALL
            })
    @JsonManagedReference
    private Set<ReceitaIngrediente> receitaIngrediente = new HashSet<>();

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "IdUsuario", nullable = false)
    private Usuario professor;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "GrupoReceita", nullable = false)
    private GrupoReceitas grupoReceita;

    public Receita() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) { this.id = id; }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public List<String> getPassos() { return passos; }

    public void setPassos(List<String> passos) {
        this.passos = passos;
    }

    public double getRendimento() {
        return rendimento;
    }

    public void setRendimento(double rendimento) {
        this.rendimento = rendimento;
    }

    public String getTempo() {
        return tempo;
    }

    public void setTempo(String tempo) {
        this.tempo = tempo;
    }

    public Set<Usuario> getCriadores() {
        return criadores;
    }

    public void setCriadores(Set<Usuario> criadores) {
        this.criadores = criadores;
    }

    public Set<ReceitaIngrediente> getReceitaIngrediente() {
        return receitaIngrediente;
    }

    public void setReceitaIngrediente(Set<ReceitaIngrediente> receitaIngrediente) {
        this.receitaIngrediente = receitaIngrediente;
    }

    public Usuario getProfessor() {
        return professor;
    }

    public void setProfessor(Usuario professor) {
        this.professor = professor;
    }

    public GrupoReceitas getGrupoReceita() {
        return grupoReceita;
    }

    public void setGrupoReceita(GrupoReceitas grupoReceita) {
        this.grupoReceita = grupoReceita;
    }

    public String getImagem() {
        return imagem;
    }

    public void setImagem(String imagem) {
        this.imagem = imagem;
    }

    public Tipo getTipo() {
        return tipo;
    }

    public void setTipo(Tipo tipo) {
        this.tipo = tipo;
    }

    @Override
    public String toString() {
        return "Ingrediente{" +
                "id=" + id +
                ", nome='" + nome +
                ", passos=" + passos.toString() +
                ", rendimento='" + rendimento  +
                ", tempo='" + tempo  +
                ", imagem='" + imagem  +
                ", tipo='" + tipo  +
                ", ingredienteReceita=" + receitaIngrediente +
                ", criadores=" + criadores +
                ", professor=" + professor +
                ", status=" + status +
                '}';
    }
}