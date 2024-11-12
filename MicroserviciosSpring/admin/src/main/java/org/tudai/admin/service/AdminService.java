package org.tudai.admin.service;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.tudai.admin.dto.AdminDTO;
import org.tudai.admin.entity.Admin;
import org.tudai.admin.repository.AdminRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class AdminService {

    @Autowired
    private AdminRepository adminRepository;

    public void save(AdminDTO admin) {
        Admin newAdmin = new Admin(admin.getName(),admin.getEmail(),admin.getPricePerKm(),admin.getExtraPricePause());
        adminRepository.save(newAdmin);
    }

    public void deleteById(Long id) {
        adminRepository.deleteById(id);
    }

    public List<AdminDTO> findAll() {
        List<Admin> admins = adminRepository.findAll();
        List<AdminDTO> adminDTOs = new ArrayList<AdminDTO>();
        for (Admin admin : admins) {
            AdminDTO dto = new AdminDTO(admin.getName(),admin.getEmail(),admin.getPricePerKm(),admin.getExtraPricePause());
            adminDTOs.add(dto);
        }
        return adminDTOs;
    }
    public AdminDTO findById(Long id) {
        Admin admin = adminRepository.findById(id).orElseThrow(()-> new EntityNotFoundException("Admin no encontrado con id " + id));
        return convertToDTO(admin);
    }

    public void updateAdmin(Long id,AdminDTO admin) {
        Admin adminSearched = adminRepository.findById(id).orElseThrow(()-> new EntityNotFoundException("Admin no encontrado con id " + id));
        adminSearched.setName(admin.getName());
        adminSearched.setEmail(admin.getEmail());
        adminSearched.setPricePerKm(admin.getPricePerKm());
        adminSearched.setExtraPricePause(admin.getExtraPricePause());
        adminRepository.save(adminSearched);
    }

    public AdminDTO convertToDTO(Admin admin){
        return new AdminDTO(admin.getName(),admin.getEmail(),admin.getPricePerKm(),admin.getExtraPricePause());
    }


}
