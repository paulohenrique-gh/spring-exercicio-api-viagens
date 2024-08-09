package org.example.apiviagens.repositories;

import org.example.apiviagens.models.Trip;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TripRepository extends JpaRepository<Trip, Long> {
    @Query("select t from Trip t inner join t.destination d where d.id = :destinationId")
    List<Trip> findAllByDestinationId(@Param("destinationId") Long destinationId);

    @Query("select t from Trip t inner join t.destination d where d.name ILIKE :destinationName")
    List<Trip> findAllByDestinationName(@Param("destinationName") String destinationName);

    @Query("select t from Trip t inner join t.destination d where d.country ILIKE :destinationCountry")
    List<Trip> findAllByDestinationCountry(@Param("destinationCountry") String destinationCountry);
}
