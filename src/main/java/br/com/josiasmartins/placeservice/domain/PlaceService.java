package br.com.josiasmartins.placeservice.domain;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.Sort;

import com.github.slugify.Slugify;

import br.com.josiasmartins.placeservice.PlaceServiceApplication;
import br.com.josiasmartins.placeservice.api.PlaceRequest;
import br.com.josiasmartins.placeservice.utils.QueryBuilder;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class PlaceService {
    
    private PlaceRepository placeRepository;
    private Slugify slg;

    public PlaceService(PlaceRepository placeRepository) {
        this.placeRepository = placeRepository;
        this.slg = Slugify.builder().build();
    }

    public Mono<Place> create(PlaceRequest placeRequest) {
        var place = new Place(
            null, 
            placeRequest.name(), 
            slg.slugify(placeRequest.name()),
            placeRequest.city(), 
            placeRequest.state(), 
            null, 
            null);
        return placeRepository.save(place);
    }

    public Mono<Place> getById(Long id) {
        return this.placeRepository.findById(id);
    }

    public Flux<Place> list(String name) {

        var place = new Place(null, name, null, null, null, null, null);

        Example<Place> query = QueryBuilder.makeQuery(place);

        return placeRepository.findAll(query, Sort.by("name").ascending());
    }
    
    public void delete(Long id) {
        
        placeRepository.deleteById(id);
    }

    public Mono<Place> update(Long id, PlaceRequest placeRequest) {

        return placeRepository.findById(id)
            .map(place -> PlaceMapper.updatePlaceFromDTO(placeRequest, place))
            .map(place -> place.withSlug(slg.slugify(place.name())))
            .flatMap(placeRepository::save);
      
    }

}
