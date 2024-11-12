package org.tudai.scooter.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.tudai.scooter.entity.Station;

@Repository
public interface StationRepository extends JpaRepository<Station, Long> {

}
