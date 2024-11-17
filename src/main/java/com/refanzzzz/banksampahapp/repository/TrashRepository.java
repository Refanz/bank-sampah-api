package com.refanzzzz.banksampahapp.repository;

import com.refanzzzz.banksampahapp.constant.query.TrashQuery;
import com.refanzzzz.banksampahapp.entity.Trash;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface TrashRepository extends JpaRepository<Trash, String> {

    @Modifying
    @Query(nativeQuery = true, value = TrashQuery.INSERT_TRASH)
    void saveTrash(String id, String name, String unit, Long price, LocalDateTime createdAt, LocalDateTime updatedAt);

    @Modifying
    @Query(nativeQuery = true, value = TrashQuery.DELETE_TRASH_BY_ID)
    void deleteTrashById(String id);

    @Modifying
    @Query(nativeQuery = true, value = TrashQuery.UPDATE_TRASH)
    void updateTrash(String name, String unit, Long price, LocalDateTime updatedAt, String id);

    @Query(nativeQuery = true, value = TrashQuery.GET_ALL_TRASH_WITH_PAGINATION)
    List<Trash> getAllTrash(int limit, int offset);

    @Query(nativeQuery = true, value = TrashQuery.GET_TRASH_BY_ID)
    List<Object[]> getTrashById(String id);

    @Query(nativeQuery = true, value = TrashQuery.GET_TRASH_COUNT)
    int getTrashCount();
 }
