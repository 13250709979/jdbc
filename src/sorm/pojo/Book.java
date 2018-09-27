package sorm.pojo;

import java.util.*;
import java.sql.*;

public class Book{

	private Integer price;
	private String isbn;
	private Integer id;
	private String bookname;
	private Integer stock;

	public void setPrice(Integer price){
		this.price=price;
	}
	public void setIsbn(String isbn){
		this.isbn=isbn;
	}
	public void setId(Integer id){
		this.id=id;
	}
	public void setBookname(String bookname){
		this.bookname=bookname;
	}
	public void setStock(Integer stock){
		this.stock=stock;
	}
	public Integer getPrice(){
		return price;
	}
	public String getIsbn(){
		return isbn;
	}
	public Integer getId(){
		return id;
	}
	public String getBookname(){
		return bookname;
	}
	public Integer getStock(){
		return stock;
	}

}