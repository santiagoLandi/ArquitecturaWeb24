import daos.CarreraDao;
import daos.EstudianteDao;
import daos.InscripcionDao;
import dtos.Reporte;
import entidades.Carrera;
import entidades.Estudiante;
import entidades.Inscripcion;
import factories.Factory;
import factories.MySqlFactory;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        Estudiante e = new Estudiante("Santiago","Richards",1992,"masculino",36254123,"Tandil",12456);
        Estudiante e1= new Estudiante("Ignacio","Perez",2004,"masculino",42214129,"Juarez",12457);
        Estudiante e2= new Estudiante("Alejo","Martinez",1993,"masculino",37845698,"Capital Federal",12458);
        Estudiante e3= new Estudiante("Claudia","Rodriguez",1991,"femenino",35456123,"Tandil",12459);
        Estudiante e4= new Estudiante("Angel","Gomez",1995,"masculino",38456912,"Rauch",12460);
        Estudiante e5= new Estudiante("Martina","Soto",2001,"femenino",39456123,"Ayacucho",12461);
        Estudiante e6= new Estudiante("Almendra","Risso",1997,"femenino",38423123,"Olavarria",12462);
        Estudiante e7= new Estudiante("Juan","Bouysede",1990,"masculino",34456923,"Ayacucho",12463);
        Estudiante e8= new Estudiante("Agustina","Mendez",2001,"femenino",39486173,"Olavarria",12464);
        Estudiante e9= new Estudiante("Melina","Risotto",1998,"femenino",39452138,"Tandil",12465);

        Carrera c1 = new Carrera("TUDAI");
        Carrera c2 = new Carrera("Licenciatura en Fisica");
        Carrera c3 = new Carrera("Licenciatura en Astronomia");

        Inscripcion i= new Inscripcion(2011,c1,e);
        Inscripcion i1= new Inscripcion(2018,c2,e);
        Inscripcion i2= new Inscripcion(2020,c3,e1);
        Inscripcion i3= new Inscripcion(2016,c2,e2);
        Inscripcion i4= new Inscripcion(2022,c3,e1);
        Inscripcion i5= new Inscripcion(2015,c1,e3);
        Inscripcion i6= new Inscripcion(2021,c1,e4);
        Inscripcion i7= new Inscripcion(2012,c3,e5);
        Inscripcion i8= new Inscripcion(2022,c3,e6);
        Inscripcion i9= new Inscripcion(2013,c2,e7);
        Inscripcion i10= new Inscripcion(2011,c1,e8);
        Inscripcion i11= new Inscripcion(2024,c3,e9);

        Factory dao= MySqlFactory.getDAOFactory(1);
        Factory dao1= MySqlFactory.getDAOFactory(1);
        Factory dao2= MySqlFactory.getDAOFactory(1);


        assert dao != null;
        EstudianteDao estudianteDao= (EstudianteDao) dao.getEstudianteDAO();
        assert dao1 != null;
        InscripcionDao inscripcionDao= (InscripcionDao) dao1.getInscripcionDAO();
        assert dao2 != null;
        CarreraDao carreraDao= (CarreraDao) dao2.getCarreraDAO();

        estudianteDao.insert(e);
        estudianteDao.insert(e1);
        estudianteDao.insert(e2);
        estudianteDao.insert(e3);
        estudianteDao.insert(e4);
        estudianteDao.insert(e5);
        estudianteDao.insert(e6);
        estudianteDao.insert(e7);
        estudianteDao.insert(e8);
        estudianteDao.insert(e9);

        carreraDao.insert(c1);
        carreraDao.insert(c2);
        carreraDao.insert(c3);

        inscripcionDao.insert(i);
        inscripcionDao.insert(i1);
        inscripcionDao.insert(i2);
        inscripcionDao.insert(i3);
        inscripcionDao.insert(i4);
        inscripcionDao.insert(i5);
        inscripcionDao.insert(i6);
        inscripcionDao.insert(i7);
        inscripcionDao.insert(i8);
        inscripcionDao.insert(i9);
        inscripcionDao.insert(i10);
        inscripcionDao.insert(i11);

        List<Reporte> reporte=carreraDao.generarReporteCarreras();
        if(reporte!=null){
            for(Reporte r:reporte){
                System.out.println(r.toString());
            }
        }else{
            System.out.println("No hay carreras");
        }






    }
}
