package com.app.unicorn.entity;

import lombok.*;

import javax.persistence.*;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "unicorn")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Unicorn {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "unicorn_id")
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

  @OneToMany(mappedBy = "unicorn", fetch = FetchType.LAZY,
    cascade = CascadeType.ALL)
  private Set<Marks> identifiableMarks;

}
