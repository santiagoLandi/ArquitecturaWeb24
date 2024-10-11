package org.example.tp3springboot.Service;

import jakarta.transaction.Transactional;
import org.example.tp3springboot.Model.Direccion;
import org.example.tp3springboot.Repository.DireccionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class DireccionService {

    @Autowired
    private final DireccionRepository direccionRepository;

    public DireccionService(DireccionRepository direccionRepository) {
        this.direccionRepository = direccionRepository;
    }


    @Transactional
    public Iterable<Direccion> findAll() throws Exception {
        try{
            return direccionRepository.findAll();
        }catch(Exception e){
            throw new Exception(e.getMessage());
        }
    }
    @Transactional
    public Optional<Direccion> findById(int id) throws Exception {
        try{
            return direccionRepository.findById(id);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }
    @Transactional
    public Direccion save(Direccion newDireccion) throws Exception {
        try{
            return direccionRepository.save(newDireccion);
        }catch(Exception e){
            throw new Exception(e.getMessage());
        }
    }
    @Transactional
    public void deleteById(Integer id) {
        direccionRepository.deleteById(id);
    }
    @Transactional
    public Iterable<Direccion> findByCalle(String calle) {
        try{
            return direccionRepository.findAllByCalle(calle);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    @Transactional
    public Iterable<Direccion> findByNumero(Integer numero) {
        try{
            return direccionRepository.findAllByNumero(numero);
        }catch(Exception e) {
            throw new RuntimeException(e);
        }
    }

}
