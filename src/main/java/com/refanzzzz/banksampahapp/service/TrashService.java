package com.refanzzzz.banksampahapp.service;

import com.refanzzzz.banksampahapp.dto.request.TrashPagingRequest;
import com.refanzzzz.banksampahapp.dto.request.TrashRequest;
import com.refanzzzz.banksampahapp.dto.response.TrashWithPagingResponse;
import com.refanzzzz.banksampahapp.entity.Trash;

public interface TrashService {
    void saveTrash(TrashRequest request);

    TrashWithPagingResponse getAllTrashWithPagination(TrashPagingRequest request);

    void updateTrash(String id, TrashRequest request);

    void deleteTrashById(String id);

    Trash getTrashById(String id);
}
