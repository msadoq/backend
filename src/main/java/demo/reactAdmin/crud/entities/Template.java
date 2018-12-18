package demo.reactAdmin.crud.entities;

import com.fasterxml.jackson.annotation.JsonCreator;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Where(clause="published=true")
@Entity
public class Template {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer id;

    @Column(name = "templateuid",columnDefinition = "text", unique = true)
    public String templateuid;

    @Column(name = "texte", columnDefinition = "text")
    public String texte;

    @OneToMany(cascade = {CascadeType.ALL})
    public Set<NotificationDef> notificationsdef = new HashSet<>();


    public boolean published = true;

    public Template() {}

    @JsonCreator
    public Template(Integer id) {
        this.id = id;
    }
}