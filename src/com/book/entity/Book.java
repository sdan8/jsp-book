package com.book.entity;

import java.util.Date;
import java.util.Map;

public class Book {

	int bookId;
	String bookTitle;
	String bookAuthor;
	String bookPub;
	Date bookPubDate;
	String bookContent;
	int bookCate;
	String bookPic;
	
	public Book() {
		super();
	}
	public Book(Map<String, Object> map) {
		this.bookId = (int) map.get("bookId");
		this.bookTitle = (String) map.get("bookTitle");
		this.bookAuthor = (String) map.get("bookAuthor");
		this.bookPub = (String) map.get("bookPub");
		this.bookPubDate = (Date) map.get("bookPubDate");
		this.bookContent = (String) map.get("bookContent");
		this.bookCate = (int) map.get("bookCate");
		this.bookPic = (String) map.get("bookPic");
	}
	public int getBookId() {
		return bookId;
	}
	public void setBookId(int bookId) {
		this.bookId = bookId;
	}
	public String getBookTitle() {
		return bookTitle;
	}
	public void setBookTitle(String bookTitle) {
		this.bookTitle = bookTitle;
	}
	public String getBookAuthor() {
		return bookAuthor;
	}
	public void setBookAuthor(String bookAuthor) {
		this.bookAuthor = bookAuthor;
	}
	public String getBookPub() {
		return bookPub;
	}
	public void setBookPub(String bookPub) {
		this.bookPub = bookPub;
	}
	public Date getBookPubDate() {
		return bookPubDate;
	}
	public void setBookPubDate(Date bookPubDate) {
		this.bookPubDate = bookPubDate;
	}
	public String getBookContent() {
		return bookContent;
	}
	public void setBookContent(String bookContent) {
		this.bookContent = bookContent;
	}
	public int getBookCate() {
		return bookCate;
	}
	public void setBookCate(int bookCate) {
		this.bookCate = bookCate;
	}
	public String getBookPic() {
		return bookPic;
	}
	public void setBookPic(String bookPic) {
		this.bookPic = bookPic;
	}
	
}
