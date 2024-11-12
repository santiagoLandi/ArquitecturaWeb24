package org.tudai.admin.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.tudai.admin.entity.Admin;

@Repository
public interface AdminRepository extends JpaRepository<Admin, Long> {

}
