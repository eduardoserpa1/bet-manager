package com.ms.betmanagerapi.repositories;

import com.ms.betmanagerapi.models.BetModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BetRepository extends JpaRepository<BetModel, Integer> {
}
