package service;

import dao.*;
import model.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


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
        /*
        direccionDAO.insert(d1);
        direccionDAO.insert(d2);
        direccionDAO.insert(d3);
        */
        System.out.println(direccionDAO.findById(2));
        Persona p1 = new Persona("Juan", 32, d1);
        Persona p2 = new Persona("Pedro", 55, d2);
        Persona p3 = new Persona("Maria", 25, d2);
        Persona p4 = new Persona("Mariela", 44, d3);
        Persona p5 = new Persona("Luis", 68, d3);
        /*
        personaDAO.insert(p1);
        personaDAO.insert(p2);
        personaDAO.insert(p3);
        personaDAO.insert(p4);
        personaDAO.insert(p5);
        */
        // Después de insertar
        System.out.println("La Persona p1: " + personaDAO.findById(1));
        System.out.println("La persona p2 es: " + personaDAO.findById(2) );

        /*

        d1.addPersonasHabitantes(p1);
        d1.addPersonasHabitantes(p2);
        d2.addPersonasHabitantes(p3);
        d3.addPersonasHabitantes(p4);
        d3.addPersonasHabitantes(p5);
        */
        Socio s1 = new Socio("no socio", p1);
        Socio s2 = new Socio("pleno", p2);
        Socio s3 = new Socio("no socio", p3);
        Socio s4 = new Socio("semi-pleno", p4);
        Socio s5 = new Socio("honor", p5);
        /*
        socioDAO.insert(s1);
        socioDAO.insert(s2);
        socioDAO.insert(s3);
        socioDAO.insert(s4);
        socioDAO.insert(s5);
        */
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");

        LocalDateTime dia3 = LocalDateTime.parse("25-11-2024 20:00", formatter);
        LocalDateTime dia4 = LocalDateTime.parse("26-10-2024 17:00", formatter);

        Turno t1 = new Turno(dia3);
        Turno t2 = new Turno(dia4);
        /*
        turnoDAO.insert(t1);
        turnoDAO.insert(t2);

        turnoDAO.agregarJugador(t1,p1);

        turnoDAO.agregarJugador(t1,p2);

        turnoDAO.agregarJugador(t1,p3);

        turnoDAO.agregarJugador(t2,p4);

        turnoDAO.agregarJugador(t2,p5);

        */

        // Ejercicio 2
        // Obtener el ID del turno recién creado
        int idTurno = turnoDAO.getIdFromTurno(t1);
        System.out.println("pruebo si funciona y me trae el id del turno 1 es : " + idTurno);

        // Recuperar el turno por ID
        Turno turnoDesdeBD = turnoDAO.findById(idTurno);
        if (turnoDesdeBD != null) {
            // Obtener los jugadores asociados al turno
            System.out.println(((MysqlTurnoDAO) turnoDAO).getJugadoresPorTurno(turnoDesdeBD));
        } else {
            System.out.println("Turno no encontrado en la base de datos");
        }
        conn.close();

    }
}