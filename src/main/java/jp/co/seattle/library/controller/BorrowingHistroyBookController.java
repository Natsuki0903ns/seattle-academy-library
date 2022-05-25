package jp.co.seattle.library.controller;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import jp.co.seattle.library.service.BooksService;
import jp.co.seattle.library.service.RentBooksService;

@Controller
 public class BorrowingHistroyBookController {
	 final static Logger logger = LoggerFactory.getLogger(BooksService.class);

	@Autowired
	private RentBooksService rentBooksService;
	 @Autowired
	    private BooksService booksService;
	 /**
	  * 履歴一覧画面に遷移
	  * @param locale
	  * @param model
	  * @return
	  */

 		 @RequestMapping(value = "/borrowinghistoryBook", method = RequestMethod.GET) //value＝actionで指定したパラメータ
 		    //RequestParamでname属性を取得
 		    public String BorrowingHistory(Locale locale,
 		    		Model model) {
 		
 			model.addAttribute("BorrowingHistoryList", rentBooksService.BorrowingHistoryList());
 			
 	         return "BorrowingHistory";}
 		/**
 		 * 詳細表示	 	
 		 * @param locale
 		 * @param bookId
 		 * @param model
 		 * @return
 		 */
 		@Transactional
 	 	@RequestMapping(value = "/historyBook", method = RequestMethod.GET)
 	 	public String history(Locale locale, @RequestParam("bookId") int bookId, Model model) {
 	 		// デバッグ用ログ
 	 		logger.info("Welcome detailsControler.java! The client locale is {}.", locale);

 	 		model.addAttribute("bookDetailsInfo", booksService.getBookInfo(bookId));

 	 		return "details";
 	 	}

 	  
 		       
 		 
 }
