package org.tudai.reportservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.tudai.reportservice.dto.ScooterDTO;
import org.tudai.reportservice.dto.ScooterReportTripYearDTO;
import org.tudai.reportservice.feign.ScooterClient;
import org.tudai.reportservice.feign.ScooterTripClient;
import org.tudai.reportservice.feign.UserClient;

import java.util.ArrayList;
import java.util.List;

@Service
public class ReportService {

    private final ScooterTripClient scooterTripClient;
    private final UserClient accountClient;
    private final ScooterClient scooterClient;

    @Autowired
    public ReportService(ScooterTripClient scooterTripClient, UserClient accountClient,ScooterClient scooterClient) {
        this.scooterTripClient = scooterTripClient;
        this.accountClient = accountClient;
        this.scooterClient = scooterClient;
    }

    public void disableAccount(Long accountId){
        accountClient.deactivateAccount(accountId);
    }
    public void enableAccount(Long accountId){
        accountClient.activateAccount(accountId);
    }

    public List<ScooterReportTripYearDTO> getScootersWithMoreThanXTrips(int year, int mintrips){
        List<ScooterDTO>scooters = scooterClient.getScooters();
        List<ScooterReportTripYearDTO>scoots= new ArrayList<ScooterReportTripYearDTO>();
        for(ScooterDTO scooterDTO : scooters){
            Long count = scooterTripClient.countScooterTripByScooterAndYear(scooterDTO.getId(),year);
            if(count >= mintrips){
                ScooterReportTripYearDTO scootDTO = new ScooterReportTripYearDTO(Math.toIntExact(scooterDTO.getId()),count,scooterDTO.getKilometersTraveled());
                scoots.add(scootDTO);
            }
        }
        return scoots;

    }




}
