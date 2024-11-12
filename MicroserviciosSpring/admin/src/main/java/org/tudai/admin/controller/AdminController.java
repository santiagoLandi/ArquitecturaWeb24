package org.tudai.admin.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.tudai.admin.dto.AdminDTO;
import org.tudai.admin.service.AdminService;

import java.util.List;

@RestController
@RequestMapping("/admins")
public class AdminController {

    private AdminService adminService;

    @Autowired
    public AdminController(AdminService adminService) {
        this.adminService = adminService;
    }

    @PostMapping("/register")
    public ResponseEntity<Void> saveAdmin(AdminDTO adminDTO) {
        try {
            adminService.save(adminDTO);
            return ResponseEntity.status(HttpStatus.CREATED).build();
        } catch (IllegalArgumentException e) {
            // Maneja errores específicos, por ejemplo, si los datos del estudiante son inválidos
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();  // 400 Bad Request
        } catch (RuntimeException e) {
            // Cualquier otro error inesperado
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();  // 500 Internal Server Error
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAdmin(@PathVariable Long id ) {
        try{
            adminService.deleteById(id);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/")
    public ResponseEntity<List<AdminDTO>> getAllAdmins() {
        try{
            List<AdminDTO>resultado= adminService.findAll();
            return ResponseEntity.ok(resultado);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<AdminDTO> getAdminById(@PathVariable Long id) {
        try{
            AdminDTO resultado= adminService.findById(id);
            return ResponseEntity.ok(resultado);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<AdminDTO>updateAdmin(@PathVariable Long id, @RequestBody AdminDTO admin) {
        try{
            adminService.updateAdmin(id,admin);
            return ResponseEntity.status(HttpStatus.ACCEPTED).body(admin);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
}
