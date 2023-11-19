package br.com.josiasmartins.placeservice.domain;

import java.time.LocalDateTime;

public record Place(
    Long id, String name, String slug, String state, LocalDateTime createAt, LocalDateTime updatedAt
) {}
