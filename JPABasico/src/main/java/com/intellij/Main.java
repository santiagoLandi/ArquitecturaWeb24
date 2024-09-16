package com.intellij;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        PersonaImp personaDao= new PersonaImp();
        DireccionImp direccionDao = new DireccionImp();
        /*
        System.out.println( "Vamos a insertar una persona y su direccion");
        Direccion d= direccionDao.getObject(2);
        Persona p= new Persona("Hector",49,d);
        personaDao.insert(p);
        302
         */
        System.out.println("Antes de eliminar a Hector id 52 ");
        List<Persona> todes=personaDao.getAll();
        for (Persona persona : todes) {
            System.out.println(persona.toString());
        }
        personaDao.delete(52);
        System.out.println("Despues de eliminar a Hector id 52 ");
        List<Persona> people=personaDao.getAll();
        for (Persona persona : people) {
            System.out.println(persona.toString());
        }


        System.out.println( "Antes de eliminar la direc id 302");
        List<Direccion> direcciones = direccionDao.getAll();
        for (Direccion dir : direcciones) {
            System.out.println(dir.toString());
        }
        direccionDao.delete(302);
        System.out.println( "Despues de eliminar la direc id 302");
        List<Direccion> directions = direccionDao.getAll();
        for (Direccion dir : directions) {
            System.out.println(dir.toString());
        }
        direccionDao.close();
        personaDao.close();

    }
}
