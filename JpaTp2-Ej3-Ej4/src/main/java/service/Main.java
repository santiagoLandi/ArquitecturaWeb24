package service;

import dao.Connect;
import dao.MySqlEquipoDAO;
import dao.MySqlJugadorDAO;
import dao.MySqlTorneoDAO;
import model.Equipo;
import model.Jugador;
import model.Torneo;

public class Main {
    public static void main(String[] args) {
        Connect conn = Connect.getConnection();

        MySqlEquipoDAO equipoDAO = new MySqlEquipoDAO(conn);
        MySqlTorneoDAO torneoDAO = new MySqlTorneoDAO(conn);
        MySqlJugadorDAO jugadorDAO = new MySqlJugadorDAO(conn);

        Torneo torneo = new Torneo("Copa de Campeones");



        //torneoDAO.insertTorneo(torneo);



        Equipo equipo = new Equipo("Independiente","MICROSOFT",torneo);
        Equipo equipo1 = new Equipo("Boca Juniors","KO",torneo);
        Equipo equipo2 = new Equipo("River Plate","GOOGLE",torneo);
        Equipo equipo3 = new Equipo("Racing Club","APPLE",torneo);


        /*
        equipoDAO.insertEquipo(equipo);
        equipoDAO.insertEquipo(equipo1);
        equipoDAO.insertEquipo(equipo2);
        equipoDAO.insertEquipo(equipo3);


        torneoDAO.insertEquipoEnTorneo(equipo, torneo);
        torneoDAO.insertEquipoEnTorneo(equipo1, torneo);
        torneoDAO.insertEquipoEnTorneo(equipo2, torneo);
        torneoDAO.insertEquipoEnTorneo(equipo3, torneo);

        */
        Jugador j= new Jugador("Rodrigo Rey","arquero",equipo);
        Jugador j1= new Jugador("Kevin Lomonaco","defensor",equipo);
        Jugador j2= new Jugador("Felipe Loyola","mediocampista",equipo);
        Jugador j3= new Jugador("Ivan Marcone","mediocampista",equipo);
        Jugador j4= new Jugador("Ivan Marcone","delantero",equipo);
        Jugador j5= new Jugador("Sergio Romero","arquero",equipo1);
        Jugador j6= new Jugador("Marcos Rojo","defensor",equipo1);
        Jugador j7= new Jugador("Pol Fernandez","mediocampista",equipo1);
        Jugador j8= new Jugador("Leandro Zenon","mediocampista",equipo1);
        Jugador j9= new Jugador("Edison Cavani","delantero",equipo1);
        Jugador j10= new Jugador("Franco Armani","arquero",equipo2);
        Jugador j11= new Jugador("Paulo Diaz","defensor",equipo2);
        Jugador j12= new Jugador("Maxi Meza","mediocampista",equipo2);
        Jugador j13= new Jugador("Ignacio Fernandez","mediocampista",equipo2);
        Jugador j14= new Jugador("Miguel Borja","delantero",equipo2);
        Jugador j15= new Jugador("Gabriel Arias","arquero",equipo3);
        Jugador j16= new Jugador("Leonardo Sigali","defensor",equipo3);
        Jugador j17= new Jugador("Baltasar Rodriguez","mediocampista",equipo3);
        Jugador j18= new Jugador("Juan Nardoni","mediocampista",equipo3);
        Jugador j19= new Jugador("Roger Martinez","delantero",equipo3);
        /*
        jugadorDAO.insertJugador(j);
        jugadorDAO.insertJugador(j1);
        jugadorDAO.insertJugador(j2);
        jugadorDAO.insertJugador(j3);
        jugadorDAO.insertJugador(j4);
        jugadorDAO.insertJugador(j5);
        jugadorDAO.insertJugador(j6);
        jugadorDAO.insertJugador(j7);
        jugadorDAO.insertJugador(j8);
        jugadorDAO.insertJugador(j9);
        jugadorDAO.insertJugador(j10);
        jugadorDAO.insertJugador(j11);
        jugadorDAO.insertJugador(j12);
        jugadorDAO.insertJugador(j13);
        jugadorDAO.insertJugador(j14);
        jugadorDAO.insertJugador(j15);
        jugadorDAO.insertJugador(j16);
        jugadorDAO.insertJugador(j17);
        jugadorDAO.insertJugador(j18);
        jugadorDAO.insertJugador(j19);

        equipoDAO.insertJugadorEnEquipo(j,equipo);
        equipoDAO.insertJugadorEnEquipo(j1,equipo);
        equipoDAO.insertJugadorEnEquipo(j2,equipo);
        equipoDAO.insertJugadorEnEquipo(j3,equipo);
        equipoDAO.insertJugadorEnEquipo(j4,equipo);
        equipoDAO.insertJugadorEnEquipo(j5,equipo1);
        equipoDAO.insertJugadorEnEquipo(j6,equipo1);
        equipoDAO.insertJugadorEnEquipo(j7,equipo1);
        equipoDAO.insertJugadorEnEquipo(j8,equipo1);
        equipoDAO.insertJugadorEnEquipo(j9,equipo1);
        equipoDAO.insertJugadorEnEquipo(j10,equipo2);
        equipoDAO.insertJugadorEnEquipo(j11,equipo2);
        equipoDAO.insertJugadorEnEquipo(j12,equipo2);
        equipoDAO.insertJugadorEnEquipo(j13,equipo2);
        equipoDAO.insertJugadorEnEquipo(j14,equipo2);
        equipoDAO.insertJugadorEnEquipo(j15,equipo3);
        equipoDAO.insertJugadorEnEquipo(j16,equipo3);
        equipoDAO.insertJugadorEnEquipo(j17,equipo3);
        equipoDAO.insertJugadorEnEquipo(j18,equipo3);
        equipoDAO.insertJugadorEnEquipo(j19,equipo3);
        */
        System.out.println("Vamos a traer los jugadores de cada equipo");
        System.out.println(equipoDAO.getJugadores(equipo));



    }



}
