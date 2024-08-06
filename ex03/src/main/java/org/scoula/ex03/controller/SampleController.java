package org.scoula.ex03.controller;

import org.scoula.ex03.dto.SampleDTO;
import org.scoula.ex03.dto.SampleDTOList;
import org.scoula.ex03.dto.TodoDTO;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import lombok.extern.log4j.Log4j;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


import java.util.ArrayList;
import java.util.Arrays;

@Controller // 이 클래스는 Spring MVC의 컨트롤러로 동작한다는 것을 나타냅니다.
@RequestMapping("/sample") // "/sample" URL 패턴에 대한 요청을 처리하는 컨트롤러입니다.
@Log4j // 로그를 기록하기 위해 Log4j를 사용한다는 것을 나타냅니다.
public class SampleController {

    @RequestMapping("") // URL이 "/sample"일 때 호출되는 메서드입니다.
    public void basic() {
        log.info("basic............"); // 로그에 "basic............"을 기록합니다.
    }

    @RequestMapping(value="/basic", method= {RequestMethod.GET, RequestMethod.POST}) // URL이 "/sample/basic"일 때 호출됩니다. GET과 POST 요청 모두 처리합니다.
    public void basicGet(){
        log.info("basic get............"); // 로그에 "basic get............"을 기록합니다.
    }

    @GetMapping("/basicOnlyGet") // URL이 "/sample/basicOnlyGet"일 때 호출됩니다. GET 요청만 처리합니다.
    public void basicGet2(){
        log.info("basic get only get............"); // 로그에 "basic get only get............"을 기록합니다.
    }

    @GetMapping("/ex01") // URL이 "/sample/ex01"일 때 호출됩니다. GET 요청을 처리합니다.
    public String ex01(SampleDTO dto) {
        log.info("" + dto); // 전달받은 SampleDTO 객체를 로그에 기록합니다.
        return "ex01"; // "ex01"이라는 뷰 이름을 반환합니다.
    }

    @GetMapping("/ex02") // URL이 "/sample/ex02"일 때 호출됩니다. GET 요청을 처리합니다.
    public String ex02(
            @RequestParam("name") String name, // URL 쿼리 파라미터에서 "name" 값을 문자열로 받습니다.
            @RequestParam("age") int age) { // URL 쿼리 파라미터에서 "age" 값을 정수로 받습니다.
        log.info("name: " + name); // "name" 파라미터 값을 로그에 기록합니다.
        log.info("age: " + age); // "age" 파라미터 값을 로그에 기록합니다.
        return "ex02"; // "ex02"라는 뷰 이름을 반환합니다.
    }

    @GetMapping("/ex02List") // URL이 "/sample/ex02List"일 때 호출됩니다. GET 요청을 처리합니다.
    public String ex02List(@RequestParam("ids") String[] ids) { // URL 쿼리 파라미터에서 "ids" 값을 문자열 배열로 받습니다.
        log.info("array ids: " + Arrays.toString(ids)); // "ids" 배열을 로그에 기록합니다.
        return "ex02List"; // "ex02List"라는 뷰 이름을 반환합니다.
    }

    @GetMapping("/ex02Bean") // URL이 "/sample/ex02Bean"일 때 호출됩니다. GET 요청을 처리합니다.
    public String ex02Bean(SampleDTOList list) { // 쿼리 파라미터로 전달된 SampleDTOList 객체를 받습니다.
        log.info("list dtos: " + list); // SampleDTOList 객체를 로그에 기록합니다.
        return "ex02Bean"; // "ex02Bean"이라는 뷰 이름을 반환합니다.
    }

    @GetMapping("/ex03") // URL이 "/sample/ex03"일 때 호출됩니다. GET 요청을 처리합니다.
    public String ex03(TodoDTO todo) { // 쿼리 파라미터로 전달된 TodoDTO 객체를 받습니다.
        log.info("todo: " + todo); // TodoDTO 객체를 로그에 기록합니다.
        return "ex03"; // "ex03"이라는 뷰 이름을 반환합니다.
    }

    //    @GetMapping("/ex04")
//    public String ex04(SampleDTO dto, int page) {
//        log.info("dto: " + dto);
//        log.info("page: " + page);
//        return "sample/ex04";
//    }


    @GetMapping("/ex04") // URL이 "/sample/ex04"일 때 호출됩니다. GET 요청을 처리합니다.
    public String ex04(SampleDTO dto, @ModelAttribute("page") int page) { // 쿼리 파라미터로 SampleDTO 객체와 "page" 값을 받습니다.
        log.info("dto: " + dto); // SampleDTO 객체를 로그에 기록합니다.
        log.info("page: " + page); // "page" 값을 로그에 기록합니다.
        return "sample/ex04"; // "sample/ex04"라는 뷰 이름을 반환합니다.
    }

    @GetMapping("/ex05")
    public void ex05() {
        log.info("/ex05........");
    }

    @GetMapping("/ex06")
    public String ex06(RedirectAttributes ra) {
        log.info("/ex06........");
        ra.addAttribute("name", "AAA");
        ra.addAttribute("age", 10);
        return "redirect:/sample/ex06-2";
    }

    @GetMapping("/ex07")
    public @ResponseBody SampleDTO ex07() {
        log.info("/ex07........");
        SampleDTO dto = new SampleDTO();
        dto.setAge(10);
        dto.setName("홍길동");
        return dto;
    }

    @GetMapping("/ex08")
    public ResponseEntity<String> ex08() {
        log.info("/ex08..........");
        // {"name": "홍길동"}
        String msg = "{\"name\": \"홍길동\"}";
        HttpHeaders header = new HttpHeaders();
        header.add("Content-Type", "application/json;charset=UTF-8");
        return new ResponseEntity<>(msg, header, HttpStatus.OK);
    }

    @GetMapping("/exUpload")
    public void exUpload() {
        log.info("/exUpload..........");
    }

    @PostMapping("/exUploadPost")
    public void exUploadPost(ArrayList<MultipartFile> files) {
        for(MultipartFile file : files) {
            log.info("----------------------------------");
            log.info("name:" + file.getOriginalFilename());
            log.info("size:" + file.getSize());
        }
    }

}
