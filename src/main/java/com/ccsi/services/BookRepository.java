package com.ccsi.services;

import com.ccsi.models.Book;
import com.ccsi.models.Category;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;

@Service
public class BookRepository {
    EntityManagerFactory emf = Persistence.createEntityManagerFactory("Bookstore");
    EntityManager em = emf.createEntityManager();

    public List<Book> getBookList(){
        return em.createQuery("from Book").getResultList();
    }

    public List<Category> getCategoryList(){
        return em.createQuery("from Category").getResultList();
    }

    public Book getSelectedBook(int bookId){
        List<Book> bookList= em.createQuery("from Book b where b.id=:id").setParameter("id", bookId).getResultList();
        return bookList.get(0);
    }

    public List<Book> getSelectedCategoryBooks(int categoryId){
        List<Book> bookList=em.createQuery("from Book b where b.categoryId=:categoryId").setParameter("categoryId", categoryId).getResultList();
        return bookList;
    }
}
