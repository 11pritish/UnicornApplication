package com.app.unicorn.controller;

import com.app.unicorn.entity.Unicorn;
import com.app.unicorn.model.request.UnicornRequest;
import com.app.unicorn.model.response.NewUnicornResponse;
import com.app.unicorn.service.UnicornService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.*;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@RunWith(SpringRunner.class)
@WebMvcTest(UnicornController.class)
public class UnicornControllerTest {

  @Autowired
  private MockMvc mockMvc;

  @MockBean
  private UnicornService unicornService;

  private List<Unicorn> unicornList;
  private Unicorn unicorn;
  private Unicorn modifiedUnicorn;
  private UnicornRequest unicornRequest;


  @Before
  public void setup() {
    unicornList = new ArrayList<>();
    unicorn = Unicorn.builder().id(1l)
      .name("Saphire")
      .hairColor("White")
      .hornColor("Pink")
      .hornLength(56)
      .eyeColor("Brown")
      .height(6)
      .heightUnit("feet")
      .weight(200)
      .weightUnit("kg")
      .build();
    unicornList.add(unicorn);

    modifiedUnicorn = Unicorn.builder().id(1l)
      .name("Bounty")
      .hairColor("Black")
      .hornColor("Pink")
      .hornLength(56)
      .eyeColor("Brown")
      .height(6)
      .heightUnit("feet")
      .weight(200)
      .weightUnit("kg")
      .build();

    unicornRequest =UnicornRequest.builder().id(1l)
      .name("Saphire")
      .hairColor("White")
      .hornColor("Pink")
      .hornLength(56)
      .eyeColor("Brown")
      .height(6)
      .heightUnit("feet")
      .weight(200)
      .weightUnit("kg")
      .build();

    when(unicornService.getUnicornsList()).thenReturn(unicornList);
    when(unicornService.getUnicorn(Mockito.anyLong())).thenReturn(unicorn);
    when(unicornService.saveUnicorn(any())).thenReturn(unicorn);
    doNothing().when(unicornService).updateUnicorn(any(), any());
  }

  @Test
  public void shouldFetchAllUnicorns() throws Exception{

    mockMvc.perform( MockMvcRequestBuilders
      .get("/unicorns/")
      .accept(MediaType.APPLICATION_JSON))
      .andDo(print())
      .andExpect(status().isOk())
      .andExpect(MockMvcResultMatchers.jsonPath("$.[*].id").isNotEmpty());
  }

  @Test
  public void getUnicornByIdAPI() throws Exception
  {
    mockMvc.perform( MockMvcRequestBuilders
      .get("/unicorns/{id}", 1)
      .accept(MediaType.APPLICATION_JSON))
      .andDo(print())
      .andExpect(status().isOk())
      .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(1));
  }

  @Test
  public void createUnicornAPI() throws Exception
  {
    mockMvc.perform( MockMvcRequestBuilders
      .post("/unicorns/")
      .content(asJsonString(this.unicornRequest))
      .contentType(MediaType.APPLICATION_JSON)
      .accept(MediaType.APPLICATION_JSON))
      .andExpect(status().isOk())
      .andExpect(MockMvcResultMatchers.jsonPath("$.unicornId").exists());
  }

  @Test
  public void updateUnicornAPI() throws Exception
  {
    mockMvc.perform( MockMvcRequestBuilders
      .put("/unicorns/{id}", 1)
      .content(asJsonString(this.unicornRequest))
      .contentType(MediaType.APPLICATION_JSON)
      .accept(MediaType.APPLICATION_JSON))
      .andExpect(status().isOk());
  }

  public static String asJsonString(final Object obj) {
    try {
      return new ObjectMapper().writeValueAsString(obj);
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

}
