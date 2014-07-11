package org.hejin.newapp.open.test;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Field;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLDecoder;
import java.net.URLEncoder;


import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.hejin.newapp.model.open.Book;
import org.hejin.newapp.model.open.CultureBook;
import org.hejin.newapp.util.POIExcelUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.transaction.annotation.Transactional;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:META-INF/spring/applicationContext.xml")
@ActiveProfiles("development")
@Transactional
@TestExecutionListeners({ DependencyInjectionTestExecutionListener.class})
public class ExcelExportTest {
	private Logger log = LoggerFactory.getLogger(getClass());
	
	@Autowired
	SessionFactory sessionFactory;
	
	//@Test
	public void test1(){
		String test = "별추가*";
		test = test.substring(0,test.lastIndexOf("*"));
		System.out.println(test);
	}
	
	@Test
	public void importExcel2DB() throws MalformedURLException, UnsupportedEncodingException{
		URL fileUrl =  ClassLoader.getSystemResource("org/hejin/open/data/우수교양도서선정목록(1968~2013).xlsx");
		String decodeUrl = URLDecoder.decode(fileUrl.getPath(),"UTF-8");
		
		
		POIExcelUtil excel = new POIExcelUtil(decodeUrl);
		Session session = sessionFactory.openSession();
		Transaction t = session.beginTransaction();
		int sheetNum = excel.getLastSheetNum();
		int sheetIdx = 0;
		System.out.println("sheetNumber : "+sheetNum);
		while(sheetIdx<sheetNum){
			excel.setSheet(sheetIdx);
			String sheetName = excel.getSheetName();
			
			int rowNum = excel.getLastRowNum();
			int rowIndex = 2;
			while(rowIndex<rowNum){
				Row row = excel.getRow(rowIndex);
				Book book = new CultureBook();
					book.setYear(sheetName);
					book.setType(excel.getCellValue(row, 1));
					book.setName(excel.getCellValue(row, 2));
					book.setAuthor(excel.getCellValue(row, 3));
					book.setPublisher(excel.getCellValue(row, 4));
				//session.save(book);
					
				saveBook(book,session);	
				rowIndex++;
			}
			
			sheetIdx++;
		}//end sheet
		t.commit();
		session.close();
		System.out.println("success");
	}

	private void saveBook(Book book, Session session) {
		// TODO Auto-generated method stub
		if(book.getName()!=null && !"도서명".equals(book.getName())){
			System.out.println(book);
			session.save(book);
		}
			
	}
	
}
