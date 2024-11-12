package org.tudai.userservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.tudai.userservice.entity.Account;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {
}
