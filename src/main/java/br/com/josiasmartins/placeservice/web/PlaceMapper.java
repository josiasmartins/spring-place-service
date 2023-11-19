package br.com.josiasmartins.placeservice.web;

import br.com.josiasmartins.placeservice.api.PlaceResponse;
import br.com.josiasmartins.placeservice.domain.Place;

public class PlaceMapper {
    

    public static PlaceResponse fromPlaceToResponse(Place place) {
        return new PlaceResponse(
            place.name(),
            place.slug(),
            place.state(),
            place.createdAt(),
            place.updatedAt()
        );
    }

}
