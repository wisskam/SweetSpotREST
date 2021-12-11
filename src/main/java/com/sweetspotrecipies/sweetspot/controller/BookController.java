package com.sweetspotrecipies.sweetspot.controller;

import com.sweetspotrecipies.sweetspot.model.Book;
import com.sweetspotrecipies.sweetspot.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/book")
public class BookController {
    @Autowired
    private BookService bookService;
    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("listBooks", bookService.listAll());
        return "book/index";
    }
    @RequestMapping("/new")
    public String create(Model model) {
        Book book = new Book();
        model.addAttribute("book", book);
        return "book/new";
    }
    @PostMapping("/save")
    public String saveBook(@ModelAttribute("book") Book book) {
        bookService.save(book);
        return "redirect:/book/";
    }
    @RequestMapping("/edit/{id}")
    public ModelAndView edit(@PathVariable(name = "id") int id) {
        ModelAndView modelAndView = new ModelAndView("book/edit");
        modelAndView.addObject("book", bookService.find(id));
        return modelAndView;
    }
    @RequestMapping("/delete/{id}")
    public String deleteBook(@PathVariable(name = "id") int id) {
        bookService.delete(id);
        return "redirect:/book/";
    }
}

