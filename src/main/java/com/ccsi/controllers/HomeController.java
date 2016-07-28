package com.ccsi.controllers;

import com.ccsi.models.Book;
import com.ccsi.models.Category;
import com.ccsi.services.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;


@Controller
@ComponentScan(basePackages = "com.ccsi")
public class HomeController {

    @Autowired
    private BookRepository bookRepository;

    @RequestMapping(value = "/")
    public String index(Model vm) {
        List<Category> categoryList=this.bookRepository.getCategoryList();
        vm.addAttribute(categoryList);
        return "/views/home/index";
    }

    @RequestMapping(value = "/books")
    public String bookList(Model vm) {
        List<Book> bookList=this.bookRepository.getBookList();
        vm.addAttribute(bookList);
        return "/views/home/books";
    }

    @RequestMapping(value = "/addbook")
    public String addBook(Model vm) {
        List<Book> bookList = this.bookRepository.getBookList();
        vm.addAttribute(bookList);
        return "/views/home/addbook";
    }

    @RequestMapping(value = "/book", method = RequestMethod.POST)
    public String book(@RequestParam(name="id") int categoryId, Model vm) {
        List<Book> bookList = this.bookRepository.getSelectedCategoryBooks(categoryId);
        vm.addAttribute(bookList);
        return "views/home/book";
    }

    @RequestMapping(value = "/{id}")
    public String bookDetail(@PathVariable(value = "id") int bookId ,Model vm){
        Book book=this.bookRepository.getSelectedBook(bookId);
        vm.addAttribute(book);
        return "views/home/bookdetail";
    }
}
