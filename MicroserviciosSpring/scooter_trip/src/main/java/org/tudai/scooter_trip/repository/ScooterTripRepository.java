package org.tudai.scooter_trip.repository;

import feign.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.tudai.scooter_trip.dto.ScooterTripDTO;
import org.tudai.scooter_trip.entity.ScooterTrip;

import java.util.List;

@Repository
public interface ScooterTripRepository extends JpaRepository<ScooterTrip, Long> {


        @Query("SELECT new org.tudai.scooter_trip.dto.ScooterTripDTO(t.startDateTime, t.endDateTime, t.distanceTraveled, t.duration, t.creditsConsumed) FROM ScooterTrip t WHERE t.accountId=:account")
        List<ScooterTripDTO> getScooterTripByAccountId(Long account);

        @Query("SELECT COUNT(st) FROM ScooterTrip st WHERE st.scooterId = :scooterId " +
                "AND FUNCTION('YEAR', st.startDateTime) = :year")
        Long countScooterTripByScooterAndYear(@Param("scooterId") Long scooterId, @Param("year") int year);
}
