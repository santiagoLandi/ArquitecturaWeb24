package org.tudai.userservice.controller;

import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.tudai.scooter_trip.dto.ScooterTripDTO;
import org.tudai.userservice.dto.AccountDTO;
import org.tudai.userservice.entity.Account;
import org.tudai.userservice.service.AccountService;

import java.util.List;

@RestController
@RequestMapping("/accounts")
@Api(value="AccountController", description = "REST API Account description")
public class AccountController {

    private final AccountService accountService;

    @Autowired
    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @PostMapping("/register")
    public ResponseEntity<AccountDTO>saveAccount(AccountDTO account) {
        try {
            accountService.save(account);
            return ResponseEntity.ok(account);
        } catch (IllegalArgumentException e) {

            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();  // 400 Bad Request
        } catch (RuntimeException e) {
            // Cualquier otro error inesperado
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();  // 500 Internal Server Error
        }
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void>deleteAccount(@PathVariable Long id) {
        try{
            accountService.deleteById(id);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }catch(IllegalArgumentException e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
    @GetMapping("/{id}")
    public ResponseEntity<AccountDTO>getAcountById(@PathVariable Long id) {
        try{
            AccountDTO account = accountService.findById(id);
            if (account != null) {
                return ResponseEntity.status(HttpStatus.OK).body(null);
            }else{
                return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
            }
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
    @PutMapping("/{id}")
    public ResponseEntity<AccountDTO>updateAccount(@PathVariable Long id, @RequestBody AccountDTO accountDTO) {
        try{
           accountService.updateAccount(id,accountDTO);
           return ResponseEntity.ok(accountDTO);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
    @GetMapping("/")
    public ResponseEntity<List<AccountDTO>>getAllAccounts() {
        try{
            List<AccountDTO>accounts = accountService.findAll();
            return ResponseEntity.ok(accounts);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/scooterTrips/{accountId}")
    public ResponseEntity<List<ScooterTripDTO>>getScooterTrips(@PathVariable Long accountId) {
        try{
            List<ScooterTripDTO>trips = accountService.getScooterTripsForAccount(accountId);
            if (trips.isEmpty()){
                return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
            }
            return ResponseEntity.ok(trips);
        }catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PatchMapping("/deactivate/{accountId}")
    public ResponseEntity<AccountDTO> deactivateAccount(@PathVariable Long accountId) {
        try {
            accountService.deactivateAccount(accountId);
            return ResponseEntity.ok(accountService.findById(accountId));
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PatchMapping("/activate/{accountId}")
    public ResponseEntity<AccountDTO> activateAccount(@PathVariable Long accountId) {
        try {
            accountService.activateAccount(accountId);
            return ResponseEntity.ok(accountService.findById(accountId));
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }



}
