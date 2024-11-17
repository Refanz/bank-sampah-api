package com.refanzzzz.banksampahapp.controller;

import com.refanzzzz.banksampahapp.constant.Constant;
import com.refanzzzz.banksampahapp.dto.request.PagingRequest;
import com.refanzzzz.banksampahapp.dto.request.trash.TrashRequest;
import com.refanzzzz.banksampahapp.dto.response.trash.TrashResponse;
import com.refanzzzz.banksampahapp.dto.response.trash.TrashWithPagingResponse;
import com.refanzzzz.banksampahapp.entity.Trash;
import com.refanzzzz.banksampahapp.service.TrashService;
import com.refanzzzz.banksampahapp.util.ResponseUtil;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping(Constant.TRASH_API)
@Tag(name = "Trash Management", description = "API to manage Trash Data")
@SecurityRequirement(name = "Bearer Authentication")
public class TrashController {

    private final TrashService trashService;

    @GetMapping
    public ResponseEntity<?> getAllTrash(
            @RequestParam(name = "size", defaultValue = "10", required = false) int size,
            @RequestParam(name = "page", defaultValue = "1", required = false) int page

    ) {
        PagingRequest pagingRequest = PagingRequest.builder()
                .limit(size)
                .page(page)
                .build();

        TrashWithPagingResponse trashResponse = trashService.getAllTrashWithPagination(pagingRequest);

        return ResponseUtil.createResponseWithPaging(HttpStatus.OK, "Successfully get all trash", trashResponse.getTrashes(), trashResponse.getPaging());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getTrashById(@PathVariable String id) {
        TrashResponse trash = trashService.getTrashById(id);
        return ResponseUtil.createResponse(HttpStatus.OK, "Successfully get trash", trash);
    }

    @PostMapping
    public ResponseEntity<?> saveTrash(@RequestBody TrashRequest request) {
        trashService.saveTrash(request);
        return ResponseUtil.createResponse(HttpStatus.CREATED, "Successfully createUserAccount new trash", null);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateTrash(@PathVariable String id, @RequestBody TrashRequest request) {
        trashService.updateTrash(id, request);
        return ResponseUtil.createResponse(HttpStatus.OK, "Successfully update trash", null);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteTrashById(@PathVariable String id) {
        trashService.deleteTrashById(id);
        return ResponseUtil.createResponse(HttpStatus.OK, "Successfully delete trash", null);
    }
}
