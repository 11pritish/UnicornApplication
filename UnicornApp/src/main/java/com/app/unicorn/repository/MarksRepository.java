package com.app.unicorn.repository;

import com.app.unicorn.entity.Marks;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MarksRepository extends JpaRepository<Marks, Long> {

  List<Marks> findByUnicornIdIn(List<Long> ids);

  void deleteMarksByUnicornId(Long unicornId);
}
