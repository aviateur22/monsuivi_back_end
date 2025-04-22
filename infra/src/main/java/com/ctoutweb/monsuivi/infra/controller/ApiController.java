package com.ctoutweb.monsuivi.infra.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
@RestController
@RequestMapping("${api.version}/api")
public class ApiController {
  private static final Logger LOGGER = LogManager.getLogger();
  @GetMapping
  ResponseEntity<String> getApiVersion() {
    LOGGER.debug(()->"[ProduApiControllerctController]-[getApiVersion] - Affichage de la version API");
    return new ResponseEntity<>("Api Monsuivi", HttpStatus.OK);
  }

}
