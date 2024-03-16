package com.ms.betmanagerapi.repositories;

import com.ms.betmanagerapi.models.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserModel, Integer> {

}
