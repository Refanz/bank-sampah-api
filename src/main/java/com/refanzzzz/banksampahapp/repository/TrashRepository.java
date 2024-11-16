package com.refanzzzz.banksampahapp.repository;

import com.refanzzzz.banksampahapp.constant.query.TrashQuery;
import com.refanzzzz.banksampahapp.entity.Trash;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TrashRepository extends JpaRepository<Trash, String> {

    @Query(nativeQuery = true, value = TrashQuery.INSERT_TRASH)
    Trash saveTrash(String id, String name, String unit, Long price);

    @Query(nativeQuery = true, value = TrashQuery.GET_ALL_TRASH_WITH_PAGINATION)
    List<Trash> getAllTrash(int limit, int offset);

    @Query(nativeQuery = true, value = TrashQuery.GET_TRASH_COUNT)
    int getTrashCount();

    @Query(nativeQuery = true, value = TrashQuery.DELETE_TRASH_BY_ID)
    void deleteTrashById(String id);

    @Query(nativeQuery = true, value = TrashQuery.UPDATE_TRASH)
    Trash updateTrash(String name, String unit, Long price, String id);
}
