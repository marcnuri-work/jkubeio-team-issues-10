package com.marcnuri.work.helm;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Controller {

  @GetMapping("/")
  public String hello() {
    return "GREETINGS PROFESSOR FALKEN";
  }
}
