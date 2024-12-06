package com.record_shop_backend.service;

import com.record_shop_backend.model.Album;

import java.util.List;

public interface AlbumService {
    List<Album> getAllAlbums();
    Album getAlbumById(Long id);
    Album addAlbum(Album album);
    Album updateAlbum(Long id, Album album);
    String deleteAlbum(Long id);
    String deleteAllAlbums();
}
