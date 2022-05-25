package jp.co.seattle.library.dto;

import org.springframework.context.annotation.Configuration;

import lombok.Data;



@Configuration
@Data


public class BorrowingHistoryInfo {
	
	 private int bookId;
	 
	 private String title;
	 
	 private String RentDate;
	 
	 private String ReturnDate;
	 
	  public BorrowingHistoryInfo() {

	    }
	  
	  public BorrowingHistoryInfo(int bookId, String title,String RentDate,String ReturnDate ) {
	        this.bookId = bookId;
	        this.title = title;
	        this.RentDate = RentDate;
	        this.ReturnDate = ReturnDate;
	        	        
	    }
}
