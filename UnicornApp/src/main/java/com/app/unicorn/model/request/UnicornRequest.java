package com.app.unicorn.model.request;

import com.app.unicorn.entity.Marks;
import lombok.*;

import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UnicornRequest {

  private Long id;

  private String name;
  private String hairColor;
  private int hornLength;
  private String hornColor;
  private String eyeColor;
  private int height;
  private String heightUnit;
  private int weight;
  private String weightUnit;
  private Set<Marks> identifiableMarks;
}
