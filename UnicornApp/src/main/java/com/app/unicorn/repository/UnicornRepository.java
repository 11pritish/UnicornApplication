package com.app.unicorn.repository;

import com.app.unicorn.entity.Unicorn;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UnicornRepository extends JpaRepository<Unicorn, Long> {

  Unicorn findUnicornById(Long unicornId);

}
