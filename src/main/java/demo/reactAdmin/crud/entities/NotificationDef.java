package demo.reactAdmin.crud.entities;

import com.fasterxml.jackson.annotation.JsonCreator;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Where(clause="published=true")
public class NotificationDef {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer id;

    @Column(name = "notifdefuid",columnDefinition = "text", unique = true)
    public String notifdefuid;

    @Column(name = "object_notif_def", columnDefinition = "text")
    public String object;

    @Column(name = "from_notif_def", columnDefinition = "text")
    public String from;

    @ElementCollection
    @Column(name = "to_notif_def")
    public List<String> to;

    public boolean published = true;

    @ManyToOne(cascade = {CascadeType.DETACH})
    public Template templateId;

    @OneToMany(cascade = {CascadeType.ALL})
    public Set<Parameter> parameters = new HashSet<>();

    public NotificationDef() {}

    @JsonCreator
    public NotificationDef(Integer id) {
        this.id = id;
    }
}