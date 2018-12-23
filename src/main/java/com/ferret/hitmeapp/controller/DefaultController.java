package com.ferret.hitmeapp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public abstract class DefaultController {
    // MeetUp API
    protected String meetupUri = "https://api.meetup.com/2/";
    protected String key_MeetUp = "7d555759c312c5e6655f5028503637";

    // EventBrite API
    protected String eventbriteUri = "https://www.eventbriteapi.com/v3/";
    protected String OAuth_EventBrite = "QGECTYGPITUNH2AOJ6NS"; //"IEKQDJQG4OBWT2MKDPTO";
}
