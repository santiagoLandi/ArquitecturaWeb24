package org.tudai.userservice.service;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.tudai.scooter_trip.dto.ScooterTripDTO;
import org.tudai.userservice.dto.AccountDTO;
import org.tudai.userservice.entity.Account;
import org.tudai.userservice.feign.ScooterTripClient;
import org.tudai.userservice.repository.AccountRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class AccountService {

    private AccountRepository accountRepository;
    private ScooterTripClient scooterTripClient;

    @Autowired
    public AccountService(AccountRepository accountRepository){
        this.accountRepository = accountRepository;
    }

    public AccountDTO save(AccountDTO account){
        Account accountToSave = new Account();
        accountToSave.setFounds(account.getFounds());
        accountToSave.setDischargeDate(account.getDischargeDate());
        accountToSave.setUsersIds(account.getUsersIds());
        accountToSave.setScooterTripsIds(account.getScooterTripsIds());
        accountRepository.save(accountToSave);
        return convertToDTO(accountToSave);

    }

    public void deleteById(Long id){
        accountRepository.deleteById(id);
    }

    public AccountDTO findById(Long id){
        return  accountRepository.findById(id)
                .map(this::convertToDTO)  // Convertir el Estudiante a EstudianteDTO
                .orElseThrow(() -> new EntityNotFoundException("Cuenta no encontrado con id " + id));
    }

    public List<AccountDTO> findAll(){
        List<Account> accounts = accountRepository.findAll();
        List<AccountDTO> accountDTOS = new ArrayList<>();
        for(Account account : accounts){
            accountDTOS.add(convertToDTO(account));
        }
        return accountDTOS;
    }

    public List<ScooterTripDTO> getScooterTripsForAccount(Long accountId) {
        return scooterTripClient.getScooterTripsByAccountId(accountId);
    }

    public AccountDTO updateAccount(Long id, AccountDTO account){
        Account accountToUpdate = accountRepository.findById(id)
                                .orElseThrow(() -> new EntityNotFoundException("Cuenta no encontrada con id " + id));
        accountToUpdate.setFounds(account.getFounds());
        accountToUpdate.setDischargeDate(account.getDischargeDate());
        accountToUpdate.setActive(account.getActive());
        accountToUpdate.setUsersIds(account.getUsersIds());
        accountToUpdate.setScooterTripsIds(account.getScooterTripsIds());
        accountRepository.save(accountToUpdate);
        return convertToDTO(accountToUpdate);
    }

    public void deactivateAccount(Long id){
        Account account = accountRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Cuenta no encontrado con id " + id));
        account.setActive(false);
    }
    public void activateAccount(Long id){
        Account account = accountRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Cuenta no encontrado con id " + id));
        account.setActive(true);
    }

    public AccountDTO convertToDTO(Account account){
        return new AccountDTO(account.getFounds(),account.getDischargeDate(),account.getActive(),account.getUsersIds(), account.getScooterTripsIds());
    }
}
