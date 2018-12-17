package demo.reactAdmin.crud.entities;

import com.fasterxml.jackson.annotation.JsonCreator;
import org.hibernate.annotations.Where;
import javax.persistence.*;

@Entity
@Where(clause="published=true")
public class NotificationDef {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer id;

    @Column(name = "uid",columnDefinition = "text")
    public String uid;

    @Column(name = "object_notif_def", columnDefinition = "text")
    public String object;

    @Column(name = "from_notif_def", columnDefinition = "text")
    public String from;

    @Column(name = "to_notif_def", columnDefinition = "text")
    public String to;

    public boolean published = true;

    @ManyToOne(cascade = {CascadeType.DETACH})
    public Template templateId;

    public NotificationDef() {}

    @JsonCreator
    public NotificationDef(Integer id) {
        this.id = id;
    }
}