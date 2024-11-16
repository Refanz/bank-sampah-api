package com.refanzzzz.banksampahapp.service;

import com.refanzzzz.banksampahapp.dto.request.TrashPagingRequest;
import com.refanzzzz.banksampahapp.dto.request.TrashRequest;
import com.refanzzzz.banksampahapp.dto.response.TrashResponse;
import com.refanzzzz.banksampahapp.dto.response.TrashWithPagingResponse;

public interface TrashService {
    TrashResponse saveTrash(TrashRequest request);

    TrashWithPagingResponse getAllTrashWithPagination(TrashPagingRequest request);

    TrashResponse updateTrash(String id, TrashRequest request);

    void deleteTrashById(String id);
}
