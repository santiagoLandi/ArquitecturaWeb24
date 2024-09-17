package service;

import dao.*;
import model.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;



public class Main {
    public static void main(String[] args) {

        Connect conn = Connect.getConnection();

        SocioDAO socioDAO = new MysqlSocioDAO(conn);
        PersonaDAO personaDAO = new MysqlPersonaDAO(conn);
        DireccionDAO direccionDAO = new MysqlDireccionDAO(conn);
        TurnoDAO turnoDAO = new MysqlTurnoDAO(conn);

        Direccion d1 = new Direccion("Alberdi 201", "Tandil");
        Direccion d2 = new Direccion("Maipu 2051", "Tandil");
        Direccion d3 = new Direccion("Pinto 1235", "Azul");

        direccionDAO.insert(d1);
        direccionDAO.insert(d2);
        direccionDAO.insert(d3);

        Persona p1 = new Persona("Juan", 32, d1);
        Persona p2 = new Persona("Pedro", 55, d2);
        Persona p3 = new Persona("Maria", 25, d2);
        Persona p4 = new Persona("Mariela", 44, d3);
        Persona p5 = new Persona("Luis", 68, d3);

        personaDAO.insert(p1);
        personaDAO.insert(p2);
        personaDAO.insert(p3);
        personaDAO.insert(p4);
        personaDAO.insert(p5);

        // Después de insertar
        System.out.println("ID Persona p1: " + p1.getIdPersona());
        System.out.println("La persona 1 es: " + personaDAO.findById(p1.getIdPersona()) );


        d1.addPersonasHabitantes(p1);
        d1.addPersonasHabitantes(p2);
        d2.addPersonasHabitantes(p3);
        d3.addPersonasHabitantes(p4);
        d3.addPersonasHabitantes(p5);

        Socio s1 = new Socio("no socio", p1);
        Socio s2 = new Socio("pleno", p2);
        Socio s3 = new Socio("no socio", p3);
        Socio s4 = new Socio("semi-pleno", p4);
        Socio s5 = new Socio("honor", p5);

        socioDAO.insert(s1);
        socioDAO.insert(s2);
        socioDAO.insert(s3);
        socioDAO.insert(s4);
        socioDAO.insert(s5);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");

        LocalDateTime dia3 = LocalDateTime.parse("25-11-2024 20:00", formatter);
        LocalDateTime dia4 = LocalDateTime.parse("26-10-2024 17:00", formatter);

        Turno t1 = new Turno(dia3);
        Turno t2 = new Turno(dia4);

        turnoDAO.insert(t1);
        turnoDAO.insert(t2);

        System.out.println("ID Turno t1: " + t1.getIdTurno());



        t1.addJugadoresATurno(p1);
        turnoDAO.agregarJugador(t1,p1);
        t1.addJugadoresATurno(p2);
        turnoDAO.agregarJugador(t1,p2);
        t1.addJugadoresATurno(p3);
        turnoDAO.agregarJugador(t1,p3);
        t2.addJugadoresATurno(p4);
        turnoDAO.agregarJugador(t2,p4);
        t2.addJugadoresATurno(p5);
        turnoDAO.agregarJugador(t2,p5);



        // Ejercicio 2


        Turno t5 = turnoDAO.findById(t1.getIdTurno());
        Persona p9 = personaDAO.findById(p1.getIdPersona());
        System.out.println("probando si existen los turnos" + t5.getIdTurno());
        System.out.println("probando si existen los personas" + p9.getIdPersona());


        conn.close();

    }
}
