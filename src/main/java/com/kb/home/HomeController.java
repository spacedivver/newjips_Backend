package com.kb.home;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

//@Controller
@Slf4j
public class HomeController {

    @GetMapping("/")
    public String home() {
        log.info("================> HomeController /");
        return "index";		// View의 이름
    }

}
