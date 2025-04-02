package com.ctoutweb.monsuivi.infra.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
@RestController
@RequestMapping("")
public class homeController {
  @GetMapping("")
  ResponseEntity<String> getHomeController() {
    return new ResponseEntity<>("Api Monsuivi", HttpStatus.OK);
  }

}
