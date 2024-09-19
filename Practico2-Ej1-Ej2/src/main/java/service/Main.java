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
        /*
        Direccion d1 = new Direccion("Alberdi 201", "Tandil");
        Direccion d2 = new Direccion("Maipu 2051", "Tandil");
        Direccion d3 = new Direccion("Pinto 1235", "Azul");
        Direccion d4 = new Direccion("Larrea 9051", "Tandil");
        Direccion d5 = new Direccion("Rivadavia 9051", "Capital Federal");
        Direccion d6 = new Direccion("Belgrano 1546","Junin");
        Direccion d7 = new Direccion("San Martin 1756","Esquel");
         */

        /*
        direccionDAO.insert(d1);
        direccionDAO.insert(d2);
        direccionDAO.insert(d3);
        direccionDAO.insert(d4);
        direccionDAO.insert(d5);
        direccionDAO.insert(d6);
        direccionDAO.insert(d7);
        */


       /*
        Persona p1 = new Persona("Juan", 32, d1);
        Persona p2 = new Persona("Pedro", 55, d2);
        Persona p3 = new Persona("Maria", 25, d2);
        Persona p4 = new Persona("Mariela", 44, d3);
        Persona p5 = new Persona("Luis", 68, d3);
        Persona p6 = new Persona("Tito", 45, d4);
        Persona p7 = new Persona("Jose", 29, d4);
        Persona p8 = new Persona("Leonel", 32, d5);
        Persona p9 = new Persona("Yanina", 34, d5);
        Persona p10 = new Persona("Alex", 26, d6);
        Persona p11 = new Persona("Lorena", 44, d7);
        Persona p12 = new Persona("Claudia", 65, d7);
        */



        /*
        personaDAO.insert(p1);
        personaDAO.insert(p2);
        personaDAO.insert(p3);
        personaDAO.insert(p4);
        personaDAO.insert(p5);
        personaDAO.insert(p6);
        personaDAO.insert(p8);
        personaDAO.insert(p9);
        personaDAO.insert(p10);
        personaDAO.insert(p11);
        personaDAO.insert(p12);
         */
        // Después de insertar
        /*
        System.out.println("La Persona p1: " + personaDAO.findById(1));
        System.out.println("La persona p5 es: " + personaDAO.findById(5) );
        */

        /*
        d1.addPersonasHabitantes(p1);
        d1.addPersonasHabitantes(p2);
        d2.addPersonasHabitantes(p3);
        d3.addPersonasHabitantes(p4);
        d3.addPersonasHabitantes(p5);
        d4.addPersonasHabitantes(p6);
        d4.addPersonasHabitantes(p7);
        d5.addPersonasHabitantes(p8);
        d5.addPersonasHabitantes(p9);
        d6.addPersonasHabitantes(p10);
        d7.addPersonasHabitantes(p11);
        d7.addPersonasHabitantes(p12);
        */


        /*
        Socio s1 = new Socio("no socio", p1);
        Socio s2 = new Socio("pleno", p2);
        Socio s3 = new Socio("no socio", p3);
        Socio s4 = new Socio("semi-pleno", p4);
        Socio s5 = new Socio("honor", p5);
        Socio s6 = new Socio("no socio", p8);
        Socio s7 = new Socio("pleno", p10);
        Socio s8 = new Socio("no socio", p12);



        /*
        socioDAO.insert(s1);
        socioDAO.insert(s2);
        socioDAO.insert(s3);
        socioDAO.insert(s4);
        socioDAO.insert(s5);
        socioDAO.insert(s6);
        socioDAO.insert(s7);
        socioDAO.insert(s8);
        */

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");

        LocalDateTime dia3 = LocalDateTime.parse("02-11-2024 10:00", formatter);
        LocalDateTime dia4 = LocalDateTime.parse("14-10-2024 12:00", formatter);

        /*
        Turno t1 = new Turno(dia3);
        Turno t2 = new Turno(dia4);
        Turno t3 = new Turno(dia3);
        Turno t4 = new Turno(dia4);
        */



        /*
        turnoDAO.insert(t1);
        turnoDAO.insert(t2);
        turnoDAO.insert(t3);
        turnoDAO.insert(t4);
        turnoDAO.agregarJugador(t3,p8);
        turnoDAO.agregarJugador(t3,p12);
        turnoDAO.agregarJugador(t3,p10);
        turnoDAO.agregarJugador(t4,p9);
        turnoDAO.agregarJugador(t4,p11);
        turnoDAO.agregarJugador(t4,p12);
        turnoDAO.agregarJugador(t1,p1);
        turnoDAO.agregarJugador(t1,p2);
        turnoDAO.agregarJugador(t1,p3);
        turnoDAO.agregarJugador(t2,p4);
        turnoDAO.agregarJugador(t2,p5);
        turnoDAO.agregarJugador(t1,p6);
        turnoDAO.agregarJugador(t2,p7);
        */
        // Ejercicio 2a, recuperar todas las personas asignadas a un turno.
        // Obtener el ID del turno recién creado

        //int idTurno = turnoDAO.getIdFromTurno(t2);
        //System.out.println("pruebo si funciona y me trae el id del turno 2 es : " + idTurno);

        // Recuperar el turno por ID
        Turno turnoDesdeBD = turnoDAO.findById(4);

        if (turnoDesdeBD != null) {
            //Ejercico 2a, Obtener los jugadores asociados al turno
            System.out.println(((MysqlTurnoDAO) turnoDAO).getJugadoresPorTurno(turnoDesdeBD));
            //Ejercicio 2b, recuperar todas las personas asignadas a un turno, y marcar cuales de ellas son socios.
            System.out.println("Vamos con el listado de socios del turno ");
            System.out.println(socioDAO.getSociosPorTurno(turnoDesdeBD));
            //Ejercicio 2c, recuperar todas las personas que viven en una ciudad predeterminada.
        } else {
            System.out.println("Turno no encontrado en la base de datos");
        }
        String ciudadBuscada= "Esquel";
        System.out.println("Vamos con el inciso, traer todas las personas de una ciudad: " + ciudadBuscada);
        System.out.println(personaDAO.getPersonasByCity(ciudadBuscada));



        conn.close();

    }
}
