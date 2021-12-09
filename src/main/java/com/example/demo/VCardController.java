package com.example.demo;

import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping(value = "VCardController/")
public class VCardController {

    @GetMapping("{scrap}")
    public String getSpecialistsList(@PathVariable String scrap) throws IOException {
        return ModelToScrap.getResults(scrap);
    }

    @GetMapping(value = "vcard/", produces = {"text/vcard"})
    public String getVcard(@RequestParam String name,
                           @RequestParam String telephone,
                           @RequestParam String email,
                           @RequestParam String website,
                           @RequestParam String street,
                           @RequestParam String postalCode,
                           @RequestParam String city) {
        return ModelToScrap.getVcard(name, telephone, email, website, street, postalCode, city);
    }

}
