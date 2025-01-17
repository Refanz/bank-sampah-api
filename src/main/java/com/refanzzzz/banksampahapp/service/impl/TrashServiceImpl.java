package com.refanzzzz.banksampahapp.service.impl;

import com.refanzzzz.banksampahapp.dto.request.PagingRequest;
import com.refanzzzz.banksampahapp.dto.request.trash.TrashRequest;
import com.refanzzzz.banksampahapp.dto.response.PagingResponse;
import com.refanzzzz.banksampahapp.dto.response.trash.TrashResponse;
import com.refanzzzz.banksampahapp.dto.response.trash.TrashWithPagingResponse;
import com.refanzzzz.banksampahapp.entity.Trash;
import com.refanzzzz.banksampahapp.repository.TrashRepository;
import com.refanzzzz.banksampahapp.service.TrashService;
import com.refanzzzz.banksampahapp.util.DateUtil;
import com.refanzzzz.banksampahapp.util.PagingUtil;
import com.refanzzzz.banksampahapp.util.Util;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TrashServiceImpl implements TrashService {

    private final TrashRepository trashRepository;

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void saveTrash(TrashRequest request) {
        trashRepository.saveTrash(
                Util.generateUUID(),
                request.getName(),
                request.getUnit(),
                request.getPrice(),
                DateUtil.getCurrentDate(),
                DateUtil.getCurrentDate());
    }

    @Transactional(readOnly = true)
    @Override
    public TrashWithPagingResponse getAllTrashWithPagination(PagingRequest request) {
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

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void updateTrash(String id, TrashRequest request) {
        TrashResponse trash = getTrashById(id);

        if (trash != null) {
            trashRepository.updateTrash(request.getName(), request.getUnit(), request.getPrice(), DateUtil.getCurrentDate(), id);
        }
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void deleteTrashById(String id) {
        TrashResponse trash = getTrashById(id);

        if (trash != null) {
            trashRepository.deleteTrashById(id);
        }
    }

    @Transactional(readOnly = true)
    @Override
    public TrashResponse getTrashById(String id) {
        List<Object[]> trashObject = trashRepository.getTrashById(id);

        if (trashObject.isEmpty())
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Trash is not found!");

        Optional<TrashResponse> trashResponse = trashObject.stream().map(o -> TrashResponse.builder()
                .id(o[0].toString())
                .name(o[1].toString())
                .unit(o[2].toString())
                .price(o[3] != null ? (Long) o[3] : 0)
                .build()).findFirst();

        return trashResponse.get();
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
