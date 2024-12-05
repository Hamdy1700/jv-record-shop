package com.record_shop_backend.repository;

import com.record_shop_backend.model.Album;
import org.springframework.data.repository.CrudRepository;

public interface AlbumRepository extends CrudRepository<Album, Long> {
}
