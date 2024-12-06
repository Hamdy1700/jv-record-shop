package com.record_shop_backend.service;

import com.record_shop_backend.model.Album;
import com.record_shop_backend.repository.AlbumRepository;
import exceptions.ItemNotFoundException;
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
    public List<Album> getAlbums() {
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
        return albumRepository.save(album);
    }
}
