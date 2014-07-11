package org.hejin.newapp.model.open;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table
public class Book {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer id;
	
	@ExcelExportColumn(index=0)
	@Getter @Setter
	private String type;
	
	@ExcelExportColumn(index=1)
	@Getter 
	private String name;
	
	@ExcelExportColumn(index=2)
	@Column(length=1000)
	@Getter @Setter
	private String publisher;
	
	@ExcelExportColumn(index=3)
	@Column(length=1000)
	@Getter @Setter
	private String author;
	
	@Getter @Setter
	private boolean bestBook=false;
	
	@Getter 
	private Integer year;
	
	/**
	 * 책이름에 *이 있을 경우, 최우수도서 선정.
	 */
	public void setName(String name){
		
		if(name!=null && name.contains("*")){
			setBestBook(true);
			name = name.substring(0,name.lastIndexOf("*"));
		}
		this.name = name;
	}
	
	public void setYear(String year){
		if(year.contains("(")){
			year = year.substring(0,year.lastIndexOf("("));
		}
		this.year = Integer.valueOf(year);
	}
	
	@Override
	public String toString() {
		String result = "year="+year+", type="+type+", name="+name+", publisher="+publisher+", author="+author;
		return result;
	}
}
