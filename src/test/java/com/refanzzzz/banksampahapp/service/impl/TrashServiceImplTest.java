package com.refanzzzz.banksampahapp.service.impl;

import com.refanzzzz.banksampahapp.dto.request.trash.TrashRequest;
import com.refanzzzz.banksampahapp.repository.TrashRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class TrashServiceImplTest {

    @Mock
    private TrashRepository trashRepository;

    @InjectMocks
    private TrashServiceImpl trashService;

    @Test
    public void saveTrash() {
        TrashRequest trashRequest = new TrashRequest();
        trashRequest.setName("Plastik");
        trashRequest.setUnit("kg");
        trashRequest.setPrice(2000L);

        trashService.saveTrash(trashRequest);

        Mockito.doNothing().when(trashRepository).saveTrash("1", trashRequest.getName(), trashRequest.getUnit(), trashRequest.getPrice(), Mockito.mock(LocalDateTime.class), Mockito.mock(LocalDateTime.class));
        Mockito.verify(trashRepository, Mockito.times(1)).saveTrash("1", trashRequest.getName(), trashRequest.getUnit(), trashRequest.getPrice(), Mockito.any(LocalDateTime.class), Mockito.any(LocalDateTime.class));
    }
}