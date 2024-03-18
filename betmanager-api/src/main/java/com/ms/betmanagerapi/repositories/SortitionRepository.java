package com.ms.betmanagerapi.repositories;

import com.ms.betmanagerapi.models.SortitionModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface SortitionRepository extends JpaRepository<SortitionModel, Integer> {
    @Query("SELECT s from SortitionModel s where s.id = ?1")
    SortitionModel getById(Integer id);
}
