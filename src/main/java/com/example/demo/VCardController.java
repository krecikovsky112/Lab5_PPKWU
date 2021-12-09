package com.example.demo;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequestMapping(value = "VCardController/")
public class VCardController {

    @GetMapping("{scrap}")
    public String getSpecialistsList(@PathVariable String scrap) throws IOException {
        return ModelToScrap.getResults(scrap);
    }

}
