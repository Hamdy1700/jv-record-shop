package com.record_shop_backend.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "albums")
public class Album {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(updatable = false, nullable = false)
    Long id;
    @Column(nullable = false)
    String name;
    @Column
    String artist;
    @Column
    @Enumerated(EnumType.STRING)
    Genre genre;
    @Column(name = "release_year")
    int releaseYear;
}
