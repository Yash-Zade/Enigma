package com.teamarc.demo.controller;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.util.HtmlUtils;

@Controller
public class VideoStreamController {

    @MessageMapping("/video")
    @SendTo("/topic/video")
    public String handleVideoStream(String message) {
        // Process the incoming video stream data


        return HtmlUtils.htmlEscape(message);
    }
}