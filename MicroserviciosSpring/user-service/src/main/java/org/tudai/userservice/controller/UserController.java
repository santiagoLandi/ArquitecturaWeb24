package org.tudai.userservice.controller;

import io.swagger.annotations.Api;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.tudai.userservice.dto.UserDTO;
import org.tudai.userservice.entity.User;
import org.tudai.userservice.service.UserService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/users")
@Api(value="UserController", description = "REST API User description")
public class UserController {

    private final UserService userService;
    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public ResponseEntity<UserDTO>registerUser(@RequestBody UserDTO user) {
        try{
            userService.save(user);
            return ResponseEntity.ok(user);
        } catch (IllegalArgumentException e) {
            // Maneja errores específicos, por ejemplo, si los datos del estudiante son inválidos
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();  // 400 Bad Request
        } catch (RuntimeException e) {
            // Cualquier otro error inesperado
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();  // 500 Internal Server Error
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void>deleteUser(@PathVariable("id") Long id) {
        try{
            userService.delete(id);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }catch(IllegalArgumentException e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDTO> getUserById(@PathVariable("id") Long id){
        try{
            UserDTO userDTO = userService.getUserById(id);
            if(userDTO != null){
                return ResponseEntity.status(HttpStatus.OK).body(userDTO);
            }else{
                return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
            }
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build() ;
        }
    }

    @GetMapping("/")
    public ResponseEntity<List<UserDTO>>getAllUsers(){
        try{
            List<UserDTO>users = userService.getAllUsers();
            if(users.isEmpty()){
                return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
            }
            return ResponseEntity.status(HttpStatus.OK).body(users);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserDTO>updateUser(@PathVariable("id") Long id, @RequestBody UserDTO user){
        try{
            userService.updateUser(id,user);
            return ResponseEntity.ok(user);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

}
