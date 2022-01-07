package com.app.unicorn.service;

import com.app.unicorn.entity.Marks;
import com.app.unicorn.entity.Unicorn;
import com.app.unicorn.model.request.UnicornRequest;
import com.app.unicorn.repository.MarksRepository;
import com.app.unicorn.repository.UnicornRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class UnicornService {

  @Autowired
  UnicornRepository unicornRepository;

  @Autowired
  MarksRepository marksRepository;

  public Unicorn saveUnicorn(UnicornRequest unicornAddRequest) {
      Unicorn unicorn = Unicorn.builder().name(unicornAddRequest.getName())
        .hairColor(unicornAddRequest.getHairColor())
        .hornColor(unicornAddRequest.getHornColor())
        .hornLength(unicornAddRequest.getHornLength())
        .eyeColor(unicornAddRequest.getEyeColor())
        .height(unicornAddRequest.getHeight())
        .heightUnit(unicornAddRequest.getHeightUnit())
        .weight(unicornAddRequest.getWeight())
        .weightUnit(unicornAddRequest.getWeightUnit())
        .build();
    Unicorn finalUnicorn = unicornRepository.save(unicorn);
    unicornAddRequest.getIdentifiableMarks().forEach(e -> {
        e.setUnicorn(finalUnicorn);
      });
    marksRepository.saveAll(unicornAddRequest.getIdentifiableMarks());
    return finalUnicorn;
  }

  public List<Unicorn> getUnicornsList() {
    return unicornRepository.findAll();
  }

  public Unicorn getUnicorn(Long unicornId) {
    return unicornRepository.findUnicornById(unicornId);
  }

  public void updateUnicorn(UnicornRequest unicornUpdateRequest, Unicorn unicorn) {
      unicorn.setName(unicornUpdateRequest.getName());
      unicorn.setHairColor(unicornUpdateRequest.getHairColor());
      unicorn.setHornColor(unicornUpdateRequest.getHornColor());
      unicorn.setHornLength(unicornUpdateRequest.getHornLength());
      unicorn.setEyeColor(unicornUpdateRequest.getEyeColor());
      unicorn.setHeight(unicornUpdateRequest.getHeight());
      unicorn.setHeightUnit(unicornUpdateRequest.getHeightUnit());
      unicorn.setWeight(unicornUpdateRequest.getWeight());
      unicorn.setWeightUnit(unicornUpdateRequest.getWeightUnit());
      unicornRepository.save(unicorn);

      List<Marks> identifiableMarks = unicornUpdateRequest.getIdentifiableMarks().stream().collect(Collectors.toList());
      List<Marks> marks = marksRepository.findByUnicornIdIn(Arrays.asList(unicorn.getId()));

      for (int index = 0; index < marks.size(); index++) {
        Marks mark = marks.get(index);
        Marks newMark = identifiableMarks.get(index);
        mark.setMark(newMark.getMark());
        mark.setColor(newMark.getColor());
        mark.setSide(newMark.getSide());
        mark.setLocation(newMark.getLocation());
        marksRepository.save(mark);
      }

  }
}
