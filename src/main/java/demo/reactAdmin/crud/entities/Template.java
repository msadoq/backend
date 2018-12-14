package demo.reactAdmin.crud.entities;

import com.fasterxml.jackson.annotation.JsonCreator;
import org.hibernate.annotations.Where;

import javax.persistence.*;

@Entity
public class Template {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Integer id;

    @Column(name = "uid",columnDefinition = "text")
    public String UID;

    @Column(name = "texte", columnDefinition = "text")
    public String texte;

    public Template() {}

    @JsonCreator
    public Template(Integer id) {
        this.id = id;
    }
}