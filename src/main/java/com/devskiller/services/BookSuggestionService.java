package com.devskiller.services;

import java.util.*;

import com.devskiller.model.Author;
import com.devskiller.model.Book;
import com.devskiller.model.Genre;
import com.devskiller.model.Reader;
import java.util.stream.Collectors;

class BookSuggestionService {

	private final Set<Book> books;
	private final Set<Reader> readers;

	public BookSuggestionService(Set<Book> books, Set<Reader> readers) {
		this.books = books;
		this.readers = readers;
	}

	Book getBookTitleByAge(Book book,int age){
		for(Reader reader : readers){
			if (reader.getFavouriteBooks().contains(book) && reader.getAge() == age) {
				return book;
			}
		}
		return null;
	}

	Set<String> suggestBooks(Reader reader) {
		Set<Genre> favouriteGenres = reader.getFavouriteGenres();
		int age = reader.getAge();
		return  books.stream()
				.filter(b-> b.getRating() >= 4)
				.filter(b-> favouriteGenres.contains(b.getGenre()))
				.filter(b->(getBookTitleByAge(b,age))!=null)
				.map(Book::getTitle)
				.collect(Collectors.toSet());
    }

	Set<String> suggestBooks(Reader reader, int rating) {
		Set<Genre> favouriteGenres = reader.getFavouriteGenres();
		int age = reader.getAge();
		return  books.stream()
				.filter(b-> b.getRating() >= rating)
				.filter(b-> favouriteGenres.contains(b.getGenre()))
				.filter(b->(getBookTitleByAge(b,age))!=null)
				.map(Book::getTitle)
				.collect(Collectors.toSet());
	}

	Set<String> suggestBooks(Reader reader, Author author) {
		Set<Genre> favouriteGenres = reader.getFavouriteGenres();
		int age = reader.getAge();
		return books.stream()
				.filter(b-> b.getRating() >= 4)
				.filter(b-> favouriteGenres.contains(b.getGenre()))
				.filter(b->(getBookTitleByAge(b,age))!=null)
				.filter(b-> b.getAuthor().equals(author))
				.map(Book::getTitle)
				.collect(Collectors.toSet());
	}
}
