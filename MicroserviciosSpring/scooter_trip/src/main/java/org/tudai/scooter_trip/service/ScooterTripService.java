package org.tudai.scooter_trip.service;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.tudai.scooter_trip.dto.ScooterTripDTO;
import org.tudai.scooter_trip.entity.ScooterTrip;
import org.tudai.scooter_trip.feign.AccountClient;
import org.tudai.scooter_trip.repository.ScooterTripRepository;
import org.tudai.userservice.dto.AccountDTO;

import java.util.ArrayList;
import java.util.List;

@Service
public class ScooterTripService {


    private ScooterTripRepository scooterTripRepository;
    private AccountClient accountClient;

    @Autowired
    public ScooterTripService(ScooterTripRepository scooterTripRepository, AccountClient accountClient) {
        this.scooterTripRepository = scooterTripRepository;
        this.accountClient = accountClient;
    }

    public void save(ScooterTripDTO scooterTripDTO) {
        ScooterTrip scooterTrip = new ScooterTrip();
        scooterTrip.setAccountId(scooterTripDTO.getId());
        scooterTrip.setDuration(scooterTripDTO.getDuration());
        scooterTrip.setStartDateTime(scooterTripDTO.getStartDateTime());
        scooterTrip.setEndDateTime(scooterTripDTO.getEndDateTime());
        scooterTrip.setDuration(scooterTripDTO.getDuration());
        scooterTrip.setCreditsConsumed(scooterTripDTO.getCreditsConsumed());
        scooterTripRepository.save(scooterTrip);
    }

    public void deleteById(Long id){
        scooterTripRepository.deleteById(id);
    }

    public ScooterTripDTO findById(Long id){
        ScooterTrip scooterTrip = scooterTripRepository.findById(id).get();
        return convertToDTO(scooterTrip);
    }


    public ScooterTripDTO getTripWithAccount(Long scooterTripId) {
        ScooterTrip scooterTrip = scooterTripRepository.findById(scooterTripId)
                .orElseThrow(() -> new EntityNotFoundException("Trip not found with id " + scooterTripId));
        AccountDTO account = accountClient.getAccountById(scooterTrip.getAccountId());

        ScooterTripDTO ScooterTripDTO = new ScooterTripDTO(scooterTrip.getStartDateTime(), scooterTrip.getEndDateTime(), scooterTrip.getDistanceTraveled(), scooterTrip.getDuration(), scooterTrip.getCreditsConsumed(), account);
        return ScooterTripDTO;
    }

    public List<ScooterTripDTO> getAllScooterTrips(){
        List<ScooterTrip> list = scooterTripRepository.findAll();
        List<ScooterTripDTO> ScooterTripDTOList = new ArrayList<>();
        for (ScooterTrip scooterTrip : list) {
            ScooterTripDTO ScooterTripDTO = convertToDTO(scooterTrip);
            ScooterTripDTOList.add(ScooterTripDTO);
        }
        return ScooterTripDTOList;
    }

    public List<ScooterTripDTO> findTripsByAccountId(Long accountId) {
        AccountDTO account = accountClient.getAccountById(accountId);
        List<ScooterTripDTO> result = scooterTripRepository.getScooterTripByAccountId(accountId);
        for (ScooterTripDTO scooterTrip : result) {
            scooterTrip.setAccount(account);
            result.add(scooterTrip);
        }
        return result;
    }

    public Long countScooterTripByScooterAndYear(Long scooterId,int year){
       return scooterTripRepository.countScooterTripByScooterAndYear(scooterId,year);
    }

    public void addPauseToTrip(Long scooterTripId, Long pauseId){
        ScooterTrip trip = scooterTripRepository.findById(scooterTripId).get();
        trip.getPauseIds().add(pauseId);
    }

    private ScooterTripDTO convertToDTO(ScooterTrip trip) {
        ScooterTripDTO dto = new ScooterTripDTO(trip.getStartDateTime(), trip.getEndDateTime(), trip.getDistanceTraveled(), trip.getDuration(), trip.getCreditsConsumed());
        return dto;
    }
}
