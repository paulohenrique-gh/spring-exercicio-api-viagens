package org.example.apiviagens.repositories;

import org.example.apiviagens.models.Destination;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface DestinationRepository extends JpaRepository<Destination, Long> {
}
