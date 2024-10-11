package org.example.tp3springboot.Model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Data
public class Turno {
   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private Integer idTurno;
   @Column
   private Date fechaTurno;
   @ManyToMany(fetch = FetchType.LAZY)
   private List<Persona> personas;

   public Turno() {}

   public Turno(Date fechaTurno) {
       this.fechaTurno = fechaTurno;
       this.personas = new ArrayList<Persona>();
   }

    public List<Persona> getPersonas() {
        return new ArrayList<>(personas);
    }




}
