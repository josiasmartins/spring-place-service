package br.com.josiasmartins.placeservice.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.r2dbc.config.EnableR2dbcAuditing;

import br.com.josiasmartins.placeservice.domain.PlaceRepository;
import br.com.josiasmartins.placeservice.domain.PlaceService;

@Configuration
@EnableR2dbcAuditing // permitir preencher a data de criacao e atualização
public class PlaceConfig {

    @Bean
    PlaceService placeService(PlaceRepository placeRepository) {
        return new PlaceService(placeRepository);
    }

}