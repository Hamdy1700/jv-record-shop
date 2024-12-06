package com.record_shop_backend.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.record_shop_backend.model.Album;
import com.record_shop_backend.model.Genre;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import com.record_shop_backend.service.AlbumService;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;

@AutoConfigureMockMvc
@SpringBootTest
public class RecordShopControllerTest {

    @Mock
    private AlbumService mockAlbumService;

    @InjectMocks
    private AlbumController albumController;

    @Autowired
    private MockMvc mockMvcController;

    private ObjectMapper mapper;

    @BeforeEach
    public void setup() {
        mockMvcController = MockMvcBuilders.standaloneSetup(albumController).build();
        mapper = new ObjectMapper();
    }

    @Test
    @DisplayName("Get all albums")
    void testGetAllAlbums() throws Exception {
        List<Album> albums = new ArrayList<>();

        albums.add(new Album(1L, "album1", "artist1", Genre.POP, 1994));
        albums.add(new Album(2L, "album2", "artist2", Genre.ROCK, 2004));
        albums.add(new Album(3L, "album3", "artist3", Genre.METAL, 2008));

        when(mockAlbumService.getAllAlbums()).thenReturn(albums);

        this.mockMvcController.perform(
                MockMvcRequestBuilders.get("/api/v1/albums"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].id").value(1))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].name").value("album1"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].id").value(2))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].name").value("album2"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[2].id").value(3))
                .andExpect(MockMvcResultMatchers.jsonPath("$[2].name").value("album3"));
    }

    @Test
    @DisplayName("Get album by id")
    void testGetAlbumById() throws Exception {
        Long id = 1L;
        Album album = new Album(1L, "album1", "artist1", Genre.POP, 1994);

        when(mockAlbumService.getAlbumById(id)).thenReturn(album);

        this.mockMvcController.perform(
                MockMvcRequestBuilders.get("/api/v1/albums/1"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(1))
                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value("album1"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.artist").value("artist1"));
    }

}
