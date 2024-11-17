package com.refanzzzz.banksampahapp.service;

import com.refanzzzz.banksampahapp.dto.request.PagingRequest;
import com.refanzzzz.banksampahapp.dto.request.trash.TrashRequest;
import com.refanzzzz.banksampahapp.dto.response.trash.TrashResponse;
import com.refanzzzz.banksampahapp.dto.response.trash.TrashWithPagingResponse;
import com.refanzzzz.banksampahapp.entity.Trash;

public interface TrashService {
    void saveTrash(TrashRequest request);

    TrashWithPagingResponse getAllTrashWithPagination(PagingRequest request);

    void updateTrash(String id, TrashRequest request);

    void deleteTrashById(String id);

    TrashResponse getTrashById(String id);
}
