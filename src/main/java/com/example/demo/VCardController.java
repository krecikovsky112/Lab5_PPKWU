package com.example.demo;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "VCardController/")
public class VCardController {

    @GetMapping("{scrap}")
    public String getSpecialistsList(@PathVariable String scrap){
        return "test";
    }

}
