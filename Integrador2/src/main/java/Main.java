import dtos.CarreraConCantInscriptosDTO;
import dtos.ReporteCarreraDTO;
import repository.CarreraRepository;
import repository.EstudianteRepository;
import repository.InscripcionRepository;
import entities.Carrera;
import entities.Estudiante;
import entities.Inscripcion;
import factories.RepositoryFactory;
import factories.MySqlRepositoryFactory;

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

        RepositoryFactory factory= MySqlRepositoryFactory.getRepositoryFactory(1);

        assert factory != null;
        EstudianteRepository estudianteRepo=  factory.getEstudianteRepository();
        InscripcionRepository inscripcionRepo=factory.getInscripcionRepository();
        CarreraRepository carreraRepo=factory.getCarreraRepository();
    /*
        estudianteRepo.insert(e);
        estudianteRepo.insert(e1);
        estudianteRepo.insert(e2);
        estudianteRepo.insert(e3);
        estudianteRepo.insert(e4);
        estudianteRepo.insert(e5);
        estudianteRepo.insert(e6);
        estudianteRepo.insert(e7);
        estudianteRepo.insert(e8);
        estudianteRepo.insert(e9);

        carreraRepo.insert(c1);
        carreraRepo.insert(c2);
        carreraRepo.insert(c3);

        inscripcionRepo.insert(i);
        inscripcionRepo.insert(i1);
        inscripcionRepo.insert(i2);
        inscripcionRepo.insert(i3);
        inscripcionRepo.insert(i4);
        inscripcionRepo.insert(i5);
        inscripcionRepo.insert(i6);
        inscripcionRepo.insert(i7);
        inscripcionRepo.insert(i8);
        inscripcionRepo.insert(i9);
        inscripcionRepo.insert(i10);
        inscripcionRepo.insert(i11);

        inscripcionRepo.fijarAnioDeGraduacion(i,2018);
        inscripcionRepo.fijarAnioDeGraduacion(i1,2024);
        inscripcionRepo.fijarAnioDeGraduacion(i3,2022);
        inscripcionRepo.fijarAnioDeGraduacion(i5,2023);
        inscripcionRepo.fijarAnioDeGraduacion(i7,2018);
        inscripcionRepo.fijarAnioDeGraduacion(i9,2019);
        inscripcionRepo.fijarAnioDeGraduacion(i10,2017);

*/
        List<ReporteCarreraDTO> reporte=carreraRepo.generarReporteCarreras();
        if(reporte!=null){
            for(ReporteCarreraDTO r:reporte){
                System.out.println(r.toString());
            }
        }else{
            System.out.println("No hay carreras");
        }

        System.out.println("Listado de estudiantes ordenados por nombre");
        List<Estudiante>estudiantesOrdenados=estudianteRepo.obtenerEstudiantesOrdenadosPorNombre();
        for(Estudiante estudiante:estudiantesOrdenados){
            System.out.println(estudiante.toString());
        }

        System.out.println("Recuperar un estudiante por su numero de libreta");

        long libreta=12456;
        Estudiante recuperado= estudianteRepo.obtenerEstudiantePorLu(libreta);
        System.out.println(recuperado.toString());

        System.out.println("buscar por genero: femenino");
        List<Estudiante>mujeres= estudianteRepo.obtenerEstudiantesPorGenero("femenino");
        for(Estudiante estudiante:mujeres){
            System.out.println(estudiante.toString());
        }
        System.out.println("buscar por genero: masculino");
        List<Estudiante>hombres= estudianteRepo.obtenerEstudiantesPorGenero("masculino");
        for(Estudiante estudiante:hombres){
            System.out.println(estudiante.toString());
        }

        System.out.println("Listado de carreras ordenada por cantidad de inscriptos");
        List<CarreraConCantInscriptosDTO>carreras= inscripcionRepo.listarCarrerasPorCantidadInscriptos();
        for(CarreraConCantInscriptosDTO r:carreras){
            System.out.println(r.toString());
        }

        System.out.println("Listar los alumnos que estudian Licenciatura en Astronomia y son de Tandil");
        Carrera carrera=carreraRepo.selectById(1);
        List<Estudiante>estudiantesBuscados= estudianteRepo.recuperarEstudiantesPorCarreraYCiudad(carrera,"Tandil");
        for(Estudiante estudiante:estudiantesBuscados){
            System.out.println(estudiante.toString());
        }

        List<ReporteCarreraDTO>informe=carreraRepo.generarReporteCarreras();
        if(informe!=null){
            for(ReporteCarreraDTO r:informe){
                System.out.println(r.toString());
            }
        }else{
            System.out.println("No hay carreras");
        }


        System.out.println("Vamos a buscar un estudiante por nombre Alejo");
        Estudiante est= estudianteRepo.selectByName("Alejo");
        System.out.println(est.toString());



    }
}
