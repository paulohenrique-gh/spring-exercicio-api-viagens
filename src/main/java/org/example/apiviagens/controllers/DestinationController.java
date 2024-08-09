package org.example.apiviagens.controllers;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.apiviagens.dtos.DestinationDto;
import org.example.apiviagens.exceptions.ResourceNotFoundException;
import org.example.apiviagens.models.Destination;
import org.example.apiviagens.repositories.DestinationRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/destinations")
@RequiredArgsConstructor
public class DestinationController {

    private final DestinationRepository destinationRepository;

    @GetMapping
    public ResponseEntity<List<Destination>> findAll() {
        return ResponseEntity.status(HttpStatus.OK).body(destinationRepository.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Destination> findById(@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(destinationRepository.findById(id).orElseThrow(ResourceNotFoundException::new));
    }

    @PostMapping
    public ResponseEntity<Destination> saveDestination(@RequestBody @Valid DestinationDto destinationDto) {
        Destination destination = new Destination();
        BeanUtils.copyProperties(destinationDto, destination);
        return ResponseEntity.status(HttpStatus.CREATED).body(destinationRepository.save(destination));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Destination> updateDestination(@PathVariable Long id, @RequestBody @Valid DestinationDto destinationDto) {
        Destination destination = destinationRepository.findById(id).orElseThrow(ResourceNotFoundException::new);
        BeanUtils.copyProperties(destinationDto, destination);
        return ResponseEntity.status(HttpStatus.OK).body(destinationRepository.save(destination));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, String>> deleteDestination(@PathVariable Long id) {
        Destination destination = destinationRepository.findById(id).orElseThrow(ResourceNotFoundException::new);
        destinationRepository.delete(destination);
        Map<String, String> responseMessage = new HashMap<>();
        responseMessage.put("message", "Destination deleted successfully");
        return ResponseEntity.status(HttpStatus.OK).body(responseMessage);
    }
}


