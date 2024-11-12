package org.tudai.userservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.tudai.userservice.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {


}
