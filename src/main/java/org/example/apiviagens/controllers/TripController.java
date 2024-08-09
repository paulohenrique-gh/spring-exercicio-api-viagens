package org.example.apiviagens.controllers;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.apiviagens.dtos.TripDto;
import org.example.apiviagens.exceptions.ResourceNotFoundException;
import org.example.apiviagens.models.Destination;
import org.example.apiviagens.models.Trip;
import org.example.apiviagens.repositories.DestinationRepository;
import org.example.apiviagens.repositories.TripRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/trips")
@RequiredArgsConstructor
public class TripController {

    private final TripRepository tripRepository;
    private final DestinationRepository destinationRepository;

    @GetMapping
    public ResponseEntity<List<Trip>> findAll() {
        return ResponseEntity.status(HttpStatus.OK).body(tripRepository.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Trip> findById(@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(tripRepository.findById(id).orElseThrow(ResourceNotFoundException::new));
    }

    @PostMapping
    public ResponseEntity<Trip> saveTrip(@RequestBody @Valid TripDto tripDto) {
        Trip trip = new Trip();
        Destination destination = destinationRepository
                .findById(tripDto.destinationId())
                .orElseThrow(ResourceNotFoundException::new);
        trip.setDestination(destination);
        BeanUtils.copyProperties(tripDto, trip, "destinationId");
        return ResponseEntity.status(HttpStatus.CREATED).body(tripRepository.save(trip));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Trip> updateTrip(@PathVariable Long id, @RequestBody TripDto tripDto) {
        Trip trip = tripRepository.findById(id).orElseThrow(ResourceNotFoundException::new);
        Destination destination = destinationRepository.findById(tripDto.destinationId()).orElseThrow(ResourceNotFoundException::new);
        trip.setDestination(destination);
        BeanUtils.copyProperties(tripDto, trip, "destinationId");
        return ResponseEntity.status(HttpStatus.OK).body(tripRepository.save(trip));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, String>> deleteTrip(@PathVariable Long id) {
        Trip trip = tripRepository.findById(id).orElseThrow(ResourceNotFoundException::new);
        tripRepository.delete(trip);
        Map<String, String> responseMessage = new HashMap<>();
        responseMessage.put("message", "Trip deleted");
        return ResponseEntity.status(HttpStatus.OK).body(responseMessage);
    }

    @GetMapping("/destinations/{destinationId}")
    public ResponseEntity<List<Trip>> findAllTripsByDestinationId(@PathVariable Long destinationId) {
        return ResponseEntity.status(HttpStatus.OK).body(tripRepository.findAllByDestinationId(destinationId));
    }

    /**
     *  Retorna todas as viagens a partir do nome de um destino
     */
    @GetMapping("/destinations/name/{destinationName}")
    public ResponseEntity<List<Trip>> findAllTripsByDestinationName(@PathVariable String destinationName) {
        return ResponseEntity.status(HttpStatus.OK).body(tripRepository.findAllByDestinationName(destinationName));
    }

    /**
     *  Retorna todas as viagens a partir do país de destino
     */
    @GetMapping("/destinations/country/{destinationCountry}")
    public ResponseEntity<List<Trip>> findAllTripsByDestinationCountry(@PathVariable String destinationCountry) {
        return ResponseEntity.status(HttpStatus.OK).body(tripRepository.findAllByDestinationCountry(destinationCountry));
    }
}
