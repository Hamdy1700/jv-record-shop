package com.record_shop_backend.service;

import com.record_shop_backend.exceptions.BlankFieldException;
import com.record_shop_backend.model.Album;
import com.record_shop_backend.repository.AlbumRepository;
import com.record_shop_backend.exceptions.ItemNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AlbumServiceImpl implements AlbumService {

    @Autowired
    AlbumRepository albumRepository;

    @Override
    public List<Album> getAllAlbums() {
        List<Album> albums = new ArrayList<>();
        albumRepository.findAll().forEach(albums::add);
        return albums;
    }

    @Override
    public Album getAlbumById(Long id) {
        Optional<Album> album = albumRepository.findById(id);
        if (album.isEmpty()) {
            throw new ItemNotFoundException(String.format("Album with id %s cannot be found!", id));
        }
        else {
            return album.get();
        }
    }

    @Override
    public Album addAlbum(Album album) {
        if (album.getName() == null || album.getArtist() == null || album.getGenre() == null) {
            throw new NullPointerException("Album fields cannot be null!");
        }

        if(album.getName().isBlank() || album.getArtist().isBlank()) {
            throw new BlankFieldException("Album fields cannot be blank!");
        }
        return albumRepository.save(album);
    }

    @Override
    public Album updateAlbum(Long id, Album album) {
        Optional<Album> existingAlbumOptional = albumRepository.findById(id);

        if (existingAlbumOptional.isEmpty()) {
            throw new ItemNotFoundException(String.format("Album with id %s cannot be found!", id));
        }

        Album existingAlbum = existingAlbumOptional.get();
        album.setId(id);
        if (album.getName() == null) {
            album.setName(existingAlbum.getName());
        }
        if (album.getArtist() == null) {
            album.setArtist(existingAlbum.getArtist());
        }
        if (album.getGenre() == null) {
            album.setGenre(existingAlbum.getGenre());
        }
        if (album.getName() == null) {
            album.setReleaseYear(existingAlbum.getReleaseYear());
        }
        return albumRepository.save(album);
    }

    @Override
    public String deleteAlbum(Long id) {
        Optional<Album> album = albumRepository.findById(id);

        if (album.isEmpty()) {
            throw new ItemNotFoundException(String.format("Album with id %s cannot be found!", id));
        }
        
        albumRepository.deleteById(id);
        return String.format("Todo with item id '%s' has been deleted successfully", id);
    }

    @Override
    public String deleteAllAlbums() {
        albumRepository.deleteAll();
        return "All albums deleted successfully";
    }
}
