package org.example.tp3springboot.Service;

import jakarta.transaction.Transactional;
import org.example.tp3springboot.Model.Persona;
import org.example.tp3springboot.Repository.PersonaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service("PersonaService")
public class PersonaService {

    @Autowired
    private PersonaRepository personaRepository;

    @Transactional
    public Persona save(Persona persona) throws Exception {
        try{
            return personaRepository.save(persona);
        }catch(Exception e){
            throw new Exception(e.getMessage());
        }
    }
    @Transactional
    public Persona UpdatePersona(Long id,Persona persona) throws Exception {
        try {
            Optional<Persona> personaBuscada = personaRepository.findById(Math.toIntExact(id));
            Persona p = personaBuscada.get();
            p=personaRepository.save(p);
            return p;
        }catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }
    @Transactional
    public boolean deletePersona(Long id) throws Exception {
        try{
            if(personaRepository.existsById(Math.toIntExact(id))){
                personaRepository.deleteById(Math.toIntExact(id));
                return true;
            }else{
                throw new Exception();
            }
        }catch(Exception e){
            throw new Exception(e.getMessage());
        }
    }
    @Transactional
    public List<Persona> findAll() throws Exception {
        return personaRepository.findAll();
    }

    @Transactional
    public Optional<Persona> findById(Long id) throws Exception {
        try{
            return Optional.of((personaRepository.findById(Math.toIntExact(id)).get()));
        }catch(Exception e){
            throw new Exception(e.getMessage());
        }
    }
    @Transactional
    public Persona findByNombre(String nombre) throws Exception {
        try{
            return personaRepository.findAllByNombre(nombre).get(0);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Transactional
    public Iterable<Persona> findAllByApellido(String apellido) {
        try{
            return personaRepository.findAllByApellido(apellido);
        }catch(Exception e){
            throw new RuntimeException(e);
        }
    }
    @Transactional
    public Iterable<Persona> findAllByNombre(String nombre) {
        try{
            return personaRepository.findAllByNombre(nombre);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    @Transactional
    public void deleteById(Long id) throws Exception {
        personaRepository.deleteById(Math.toIntExact(id));
    }

}
