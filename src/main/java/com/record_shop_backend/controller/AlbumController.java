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
    AlbumService albumService;

    @GetMapping
    public ResponseEntity<List<Album>> getAllTodoItems() {
        return new ResponseEntity<>(albumService.getAlbums(), HttpStatus.OK);
    }

}
