package org.tudai.userservice.service;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.tudai.userservice.dto.UserDTO;
import org.tudai.userservice.entity.User;
import org.tudai.userservice.repository.UserRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Transactional
    public UserDTO save(UserDTO user) {
        User newUser = new User();
        newUser.setUsername(user.getUserName());
        newUser.setName(user.getName());
        newUser.setLastName(user.getLastName());
        newUser.setPhoneNumber(user.getPhoneNumber());
        newUser.setEmail(user.getEmail());

        newUser = userRepository.save(newUser);
        return new UserDTO(newUser);
    }

    public void delete(Long id) {
        userRepository.deleteById(id);
    }

    public UserDTO getUserById(Long id) {
        return  userRepository.findById(id)
               .map(this::convertToDTO)  // Convertir el Estudiante a EstudianteDTO
                .orElseThrow(() -> new EntityNotFoundException("Usuario no encontrado con id " + id));
    }

    public List<UserDTO> getAllUsers() {
        List<User> users = userRepository.findAll();
        List<UserDTO> userDTOs = new ArrayList<>();
        for (User user : users) {
            UserDTO userDTO = this.convertToDTO(user);
            userDTOs.add(userDTO);
        }
        return userDTOs;
    }

    public UserDTO updateUser(Long id, UserDTO user){
        User userSearched= userRepository.findById(id)
                          .orElseThrow(() -> new EntityNotFoundException("Usuario no encontrado con id " + id));
        userSearched.setName(user.getName());
        userSearched.setLastName(user.getLastName());
        userSearched.setPhoneNumber(user.getPhoneNumber());
        userSearched.setEmail(user.getEmail());
        userSearched.setAccountsId(user.getAccountDTOList());
        userRepository.save(userSearched);
        return new UserDTO(userSearched);
    }

    public UserDTO convertToDTO(User updatedUser) {
        return new UserDTO(updatedUser.getUsername(),updatedUser.getName(),
                updatedUser.getLastName(),updatedUser.getPhoneNumber(),updatedUser.getEmail());
    }
}
