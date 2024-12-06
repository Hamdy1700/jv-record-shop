package com.record_shop_backend.service;

import com.record_shop_backend.model.Album;
import com.record_shop_backend.model.Genre;
import com.record_shop_backend.repository.AlbumRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@DataJpaTest
public class RecordShopServiceTest {

    @Mock
    private AlbumRepository mockAlbumRepository;

    @InjectMocks
    private AlbumServiceImpl albumServiceImpl;

    @Test
    @DisplayName("GET all albums responds with all albums")
    void testGetAllAlbums() {
        List<Album> albums = new ArrayList<>();

        albums.add(new Album(1L, "album1", "artist1", Genre.POP, 1994));
        albums.add(new Album(2L, "album2", "artist2", Genre.ROCK, 2004));
        albums.add(new Album(3L, "album3", "artist3", Genre.METAL, 2008));

        when(mockAlbumRepository.findAll()).thenReturn(albums);

        List<Album> result = albumServiceImpl.getAllAlbums();

        assertThat(result).hasSize(3);
        assertThat(result).isEqualTo(albums);
    }

    @Test
    @DisplayName("Get album by Id responds with album of given id")
    void testGetAlbumById() {
        Long id = 1L;
        Optional<Album> album = Optional.of(new Album(1L, "album1", "artist1", Genre.POP, 1994));

        when(mockAlbumRepository.findById(id)).thenReturn(album);

        Album result = albumServiceImpl.getAlbumById(id);

        assertThat(result).isEqualTo(album.get());
    }

}
