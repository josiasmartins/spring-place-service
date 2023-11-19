package br.com.josiasmartins.placeservice.web;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.josiasmartins.placeservice.api.PlaceRequest;
import br.com.josiasmartins.placeservice.api.PlaceResponse;
import br.com.josiasmartins.placeservice.domain.Place;
import br.com.josiasmartins.placeservice.domain.PlaceMapper;
import br.com.josiasmartins.placeservice.domain.PlaceService;
import jakarta.validation.Valid;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/places")
public class PlaceController {

    private PlaceService placeService;

    public PlaceController(PlaceService placeService) {
        this.placeService = placeService;
    }

    @PostMapping
    public ResponseEntity<Mono<PlaceResponse>> create(@Valid @RequestBody PlaceRequest request) {
        var placeResponse = placeService.create(request).map(PlaceMapper::toResponse);
        return ResponseEntity.status(HttpStatus.CREATED).body(placeResponse);
    } 

    @PatchMapping("/{id}")
    public Mono<ResponseEntity<PlaceResponse>> edit(@PathVariable("id") Long id, @RequestBody PlaceRequest request) {
        return placeService.getById(id)
            .map(place -> ResponseEntity.ok(PlaceMapper.toResponse(place)))
            .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @GetMapping("/{id}")
    public Mono<ResponseEntity<PlaceResponse>> get(@PathVariable("id") Long id) {
        return placeService.getById(id)
            .map(place -> ResponseEntity.ok(PlaceMapper.toResponse(place)))
            .defaultIfEmpty(ResponseEntity.notFound().build());
    } 

    @GetMapping
    public Flux<PlaceResponse> list(@RequestParam(required = false) String name) {
        return placeService.list(name).map(PlaceMapper::toResponse);
    }
    
}
