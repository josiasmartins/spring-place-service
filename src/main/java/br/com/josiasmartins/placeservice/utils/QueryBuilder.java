package br.com.josiasmartins.placeservice.utils;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;

import br.com.josiasmartins.placeservice.domain.Place;

public class QueryBuilder {

    private QueryBuilder() {}

    public static Example<Place> makeQuery(Place planet) {
        ExampleMatcher exampleMatcher = ExampleMatcher.matchingAll().withIgnoreCase().withIncludeNullValues();
        return Example.of(planet, exampleMatcher);
    }
    
}
