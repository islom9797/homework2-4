package org.islom.dars241.controller;

import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/report")
public class RepostController {

    @GetMapping
    public HttpEntity<?> getReport() {

        return ResponseEntity.ok("REpodfsfsdst send") ;
    }
}
