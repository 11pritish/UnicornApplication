package com.app.unicorn.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "marks")
public class Marks {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private long id;

  private String side;
  private String location;
  private String mark;
  private String color;

  @ManyToOne(fetch = FetchType.LAZY, optional = false)
  @JoinColumn(name = "unicorn_id", nullable = false)
  @JsonIgnore
  private Unicorn unicorn;

}
