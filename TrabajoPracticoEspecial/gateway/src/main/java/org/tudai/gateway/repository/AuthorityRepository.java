package org.tudai.gateway.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.tudai.gateway.entity.Authority;

@Repository
public interface AuthorityRepository extends JpaRepository<Authority, String> {


}
