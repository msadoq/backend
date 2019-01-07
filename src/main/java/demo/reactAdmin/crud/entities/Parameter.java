package demo.reactAdmin.crud.entities;

import com.fasterxml.jackson.annotation.JsonCreator;

import javax.persistence.*;

@Entity
public class Parameter {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer id;

    @Column(name = "type", columnDefinition = "varchar")
    public String type;

    @Column(name ="key", columnDefinition = "varchar")
    public String key;

    @Column(name = "value", columnDefinition = "varchar")
    public String value;

    public Parameter() {}

    @JsonCreator
    public Parameter(Integer id) { this.id = id; }

}
