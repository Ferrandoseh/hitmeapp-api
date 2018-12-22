package com.ferret.hitmeapp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public abstract class DefaultController {
    protected String meetupUri = "https://api.meetup.com/2/";
    protected String apiKey = "7d555759c312c5e6655f5028503637";

}
