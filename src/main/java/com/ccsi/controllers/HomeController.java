package com.ccsi.controllers;

import com.ccsi.models.Book;
import com.ccsi.services.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@ComponentScan(basePackages = "com.ccsi")
public class HomeController {

    @Autowired
    private BookRepository bookRepository;

    @RequestMapping(value = "/")
    public String index(Model vm) {
        vm.addAttribute("categoryList",this.bookRepository.getCategoryList());
        return "/views/home/index";
    }

    @RequestMapping(value = "/books")
    public String bookList(Model vm) {
        vm.addAttribute("bookList",this.bookRepository.getBookList());
        return "/views/home/books";
    }

    @RequestMapping(value = "/addbook")
    public String addBook(Model vm) {
        Book book=new Book();
        vm.addAttribute("book",book);
        return "/views/home/addbook";
    }

    @RequestMapping(value = "/addbook", method = RequestMethod.POST)
    public String saveBook(@ModelAttribute(value = "book") Book book, BindingResult result){
        if(result.hasErrors()){
            return "/views/home/addbook";
        }
        this.bookRepository.saveBook(book);
        return "/views/home/success";
    }

    @RequestMapping(value = "/book", method = RequestMethod.POST)
    public String book(@RequestParam(name="id") int categoryId, Model vm) {
        vm.addAttribute("bookList",this.bookRepository.getSelectedCategoryBooks(categoryId));
        return "/views/home/book";
    }

    @RequestMapping(value = "/{id}")
    public String bookDetail(@PathVariable(value = "id") int bookId ,Model vm){
        vm.addAttribute("book",this.bookRepository.getBookById(bookId));
        return "/views/home/bookdetail";
    }

    @RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
    public String editBook(@PathVariable(value = "id") int bookId, Model vm){
        vm.addAttribute("book",this.bookRepository.getBookById(bookId));
        return "/views/home/editbook";
    }

    @RequestMapping(value = "/edit/{id}", method = RequestMethod.POST)
    public String updateBook(@ModelAttribute(value = "book") Book book, @PathVariable(value = "id") int bookId, BindingResult result){
        if(result.hasErrors()){
            return "/views/home/editbook";
        }
        this.bookRepository.updateBook(book);
        return "/views/home/success";
    }
}
