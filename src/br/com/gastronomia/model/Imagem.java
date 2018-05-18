package br.com.gastronomia.model;

import java.awt.image.BufferedImage;
import java.io.Serializable;
import java.sql.Blob;
import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name= "Imagem")
@JsonIgnoreProperties(ignoreUnknown = true)
public class Imagem implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "IdImagem")
    private long id;

    @NotEmpty
    @Column(name = "FilePath", unique = true)
    private String filePath;

    private Blob image;

    /**
     * Construtor vazio.
     *
     **/

    public Imagem() {

    }

    public Imagem(String filePath) {
        this.filePath = filePath;
    }

    public long getId() {
        return id;
    }

    public String getFilePath() {
        return filePath;
    }

    public Blob getImagem() {
        return image;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public void setImagem(Blob image) {
        this.image = image;
    }


    @Override
    public String toString() {
        return "Imagem {" +
                "id='" + id +
                ", filepath='" + filePath +
                '}';
    }
}