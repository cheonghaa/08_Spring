package org.scoula.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Conditional;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller //java bean으로 등록이 된다.
@Slf4j
public class HomeContoller {

    @GetMapping("/")
    public String home(){
        log.info("=============> HomeContoller /");
        return "index"; //view의 이름


    }
}
