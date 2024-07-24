package com.example.demo.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import com.example.demo.model.Book;
import com.example.demo.repository.BookJpaRepository;
import com.example.demo.repository.BookRepository;


public class BookJpaService implements BookRepository {
	
	@Autowired
	private BookJpaRepository bookJpaRepository;


	@Override
	public ArrayList<Book> getBooks() {
		// TODO Auto-generated method stub
		  List<Book> bookList = bookJpaRepository.findAll();
	        ArrayList<Book> books = new ArrayList<>(bookList);
	        return books;
	}

	@Override
	public Book getBookById(int bookId) {
		// TODO Auto-generated method stub
		 try {
	            Book book = bookJpaRepository.findById(bookId).get();
	            return book;
	        } catch(Exception e) {
	            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
	        }
	    }

	   
	

	@Override
	public Book addBook(Book book) {
		// TODO Auto-generated method stub
		bookJpaRepository.save(book);
		return book;
	}

	@Override
	public Book updateBook(int bookId, Book book) {
		// TODO Auto-generated method stub
		 try {
	            Book newBook = bookJpaRepository.findById(bookId).get();

	            if (book.getName() != null) {
	                newBook.setName(book.getName());
	            }
	            if(book.getImageUrl() != null) {
	                newBook.setImageUrl(book.getImageUrl());
	            }
	            
	            bookJpaRepository.save(newBook);
	            return newBook;

	        } catch(Exception e)    {
	            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
	        }
	
	}

	@Override
	public void deleteBook(int bookId) {
		// TODO Auto-generated method stub
		 try {
	            bookJpaRepository.deleteById(bookId);
	        } catch (Exception e) {

	            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
	        }

	}

}
