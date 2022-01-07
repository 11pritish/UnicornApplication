package com.app.unicorn.controller;

import com.app.unicorn.entity.Unicorn;
import com.app.unicorn.model.request.UnicornRequest;
import com.app.unicorn.model.response.NewUnicornResponse;
import com.app.unicorn.service.UnicornService;
import com.sun.istack.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/unicorns")
public class UnicornController {

  @Autowired
  UnicornService unicornService;

  @RequestMapping(value = "/", method = RequestMethod.GET)
  public List<Unicorn> getUnicornsList() {
    List<Unicorn> unicorns = unicornService.getUnicornsList();
    return unicorns;
  }

  @RequestMapping(value = "/{unicornId}", method = RequestMethod.GET)
  public Unicorn getUnicorn(@PathVariable @NotNull Long unicornId) {
    Unicorn unicorn = unicornService.getUnicorn(unicornId);
    return unicorn;
  }

  @RequestMapping(value = "/", method = RequestMethod.POST)
  public NewUnicornResponse saveUnicorn(@RequestBody UnicornRequest unicornAddRequest) {
    Unicorn unicorn = unicornService.saveUnicorn(unicornAddRequest);
    NewUnicornResponse unicornResponse = NewUnicornResponse.builder().unicornId(unicorn.getId()).build();
    return
     unicornResponse;

  }

  @RequestMapping(value = "/{unicornId}", method = RequestMethod.PUT)
  public void updateUnicorn(@PathVariable @NotNull Long unicornId, @RequestBody UnicornRequest unicornUpdateRequest) {
    Unicorn unicorn = unicornService.getUnicorn(unicornId);
    if (unicorn != null) {
      unicornService.updateUnicorn(unicornUpdateRequest, unicorn);
    }
  }

}
