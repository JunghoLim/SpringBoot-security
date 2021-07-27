package com.jungbook.controller;

import com.jungbook.service.NaverBookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.UnsupportedEncodingException;

@Controller
public class BookController {

    private final NaverBookService service;

    @Autowired
    public BookController(NaverBookService service) {
        this.service = service;
    }

    @GetMapping("bookList.do")
    public String bookList(@RequestParam(required=false)String keyword, Model model) throws UnsupportedEncodingException {
        if(keyword !=null)
        {
            model.addAttribute("bookList",service.searchBook(keyword,10,1));
        }
        return "booklist";
    }
}
