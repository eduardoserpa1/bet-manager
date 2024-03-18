package com.ms.betmanagerapi.repositories;

import com.ms.betmanagerapi.models.BetModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface BetRepository extends JpaRepository<BetModel, Integer> {
    @Query("SELECT b from BetModel b where b.idSortition = ?1")
    List<BetModel> getAllBySortitionId(Integer id);
}
