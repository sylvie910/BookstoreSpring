package com.ccsi.controllers;

import com.ccsi.models.Book;
import com.ccsi.models.Category;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;


@Controller
public class HomeController {
    EntityManagerFactory emf = Persistence.createEntityManagerFactory("Bookstore");
    EntityManager em = emf.createEntityManager();

    @RequestMapping(value = "/")
    public String index(Model vm) {
        List<Category> categoryList = em.createQuery("from Category").getResultList();
        vm.addAttribute(categoryList);
        return "/views/home/index";
    }

    @RequestMapping(value = "/books", method = RequestMethod.GET)
    public String bookList(Model vm) {
        List<Book> bookList = em.createQuery("from Book").getResultList();
        vm.addAttribute(bookList);
        return "/views/home/books";
    }

    @RequestMapping(value = "/addbook", method = RequestMethod.GET)
    public String addBook() {
        return "/views/home/addbook";
    }

    @RequestMapping(value = "/book", method = RequestMethod.POST)
    public String book(@RequestParam(name="id") int categoryId, Model vm) {
        List<Book> bookList = em.createQuery("from Book b where b.categoryId=:categoryId").setParameter("categoryId", categoryId).getResultList();
        vm.addAttribute(bookList);
        return "views/home/book";
    }
}
