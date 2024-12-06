package com.record_shop_backend.controller;

import com.record_shop_backend.model.Album;
import com.record_shop_backend.service.AlbumService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.util.List;

@RestController
@RequestMapping("/api/v1/albums")
public class AlbumController {

    @Autowired
    private AlbumService albumService;

    @GetMapping
    public ResponseEntity<List<Album>> getAllAlbums() {
        return new ResponseEntity<>(albumService.getAllAlbums(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Album> getAlbumById(@PathVariable("id") Long id) {
        return new ResponseEntity<>(albumService.getAlbumById(id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Album> addAlbum(@RequestBody Album album) {
        Album newAlbum = albumService.addAlbum(album);
        return new ResponseEntity<>(newAlbum, HttpStatus.CREATED);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Album> updateAlbum(@PathVariable("id") Long id, @RequestBody Album album) {
        return new ResponseEntity<>(albumService.updateAlbum(id, album), HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteAlbum(@PathVariable("id") Long id) {
        return new ResponseEntity<>(albumService.deleteAlbum(id), HttpStatus.ACCEPTED);
    }

    @DeleteMapping()
    public ResponseEntity<String> deleteAllAlbums() {
        return new ResponseEntity<>(albumService.deleteAllAlbums(), HttpStatus.ACCEPTED);
    }

}
