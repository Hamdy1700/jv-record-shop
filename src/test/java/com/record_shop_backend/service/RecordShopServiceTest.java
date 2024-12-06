package com.record_shop_backend.service;

import com.record_shop_backend.repository.AlbumRepository;
import jakarta.inject.Inject;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
public class RecordShopServiceTest {

    @Mock
    private AlbumRepository mockAlbumRepository;

    @InjectMocks
    private AlbumService albumService;

}
