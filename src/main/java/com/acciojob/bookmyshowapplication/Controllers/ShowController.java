package com.acciojob.bookmyshowapplication.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(name = "shows")
public class ShowController {
    @Autowired
    private ShowService showService;

    @PostMapping("addShow")
    public string addShow(@RequestBody AddShowRequest addShowRequest){

    }
}
