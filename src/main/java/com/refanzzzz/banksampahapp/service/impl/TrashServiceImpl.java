package com.refanzzzz.banksampahapp.service.impl;

import com.refanzzzz.banksampahapp.dto.request.TrashPagingRequest;
import com.refanzzzz.banksampahapp.dto.request.TrashRequest;
import com.refanzzzz.banksampahapp.dto.response.PagingResponse;
import com.refanzzzz.banksampahapp.dto.response.TrashResponse;
import com.refanzzzz.banksampahapp.dto.response.TrashWithPagingResponse;
import com.refanzzzz.banksampahapp.entity.Trash;
import com.refanzzzz.banksampahapp.repository.TrashRepository;
import com.refanzzzz.banksampahapp.service.TrashService;
import com.refanzzzz.banksampahapp.util.PagingUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class TrashServiceImpl implements TrashService {

    private final TrashRepository trashRepository;

    @Override
    public TrashResponse saveTrash(TrashRequest request) {
        trashRepository.saveTrash(UUID.randomUUID().toString(), request.getName(), request.getUnit(), request.getPrice());
        return null;
    }

    @Transactional(readOnly = true)
    @Override
    public TrashWithPagingResponse getAllTrashWithPagination(TrashPagingRequest request) {
        int totalItems = trashRepository.getTrashCount();
        int totalPages = PagingUtil.getTotalPage(totalItems, request.getLimit());
        int offset = PagingUtil.generateOffset(request.getLimit(), request.getPage());

        List<Trash> trashes = trashRepository.getAllTrash(request.getLimit(), offset);

        PagingResponse pagingResponse = PagingResponse.builder()
                .page(request.getPage())
                .size(request.getLimit())
                .totalPages(totalPages)
                .totalItems(totalItems)
                .build();

        List<TrashResponse> trashResponses = trashes.stream().map((this::mapToTrashResponse)).toList();

        return mapToTrashPagingResponse(trashResponses, pagingResponse);
    }

    @Override
    public TrashResponse updateTrash(String id, TrashRequest request) {
        return null;
    }

    @Override
    public void deleteTrashById(String id) {
        trashRepository.deleteTrashById(id);
    }

    private TrashResponse mapToTrashResponse(Trash trash) {
        return TrashResponse.builder()
                .id(trash.getId())
                .name(trash.getName())
                .price(trash.getPrice())
                .unit(trash.getUnit())
                .build();
    }

    private TrashWithPagingResponse mapToTrashPagingResponse(List<TrashResponse> trashResponses, PagingResponse pagingResponse) {
        return TrashWithPagingResponse.builder()
                .trashes(trashResponses)
                .paging(pagingResponse)
                .build();
    }
}
