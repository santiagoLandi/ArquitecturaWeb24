package org.tudai.scooter.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.tudai.scooter.entity.Scooter;

@Repository
public interface ScooterRepository extends JpaRepository<Scooter,Long> {


}
