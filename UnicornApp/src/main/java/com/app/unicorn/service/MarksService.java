package com.app.unicorn.service;

import com.app.unicorn.entity.Marks;
import com.app.unicorn.repository.MarksRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MarksService {

  @Autowired
  MarksRepository marksRepository;

  public List<Marks> getIdentifiableMarksForUnicornIds(List<Long> unicornIds) {
    return marksRepository.findByUnicornIdIn(unicornIds);
  }
}
