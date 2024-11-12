package org.tudai.scooter_trip.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.tudai.scooter_trip.entity.Pause;

@Repository
public interface PauseRepository extends JpaRepository<Pause, Long> {

}
