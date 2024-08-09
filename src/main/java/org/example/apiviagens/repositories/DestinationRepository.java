package org.example.apiviagens.repositories;

import org.example.apiviagens.models.Destination;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DestinationRepository extends JpaRepository<Destination, Long> {
}
