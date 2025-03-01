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
 @Controller
 public class SearchBookController {

 	final static Logger logger = LoggerFactory.getLogger(SearchBookController.class);

 	@Autowired
     private BooksService booksService;
 	
 	/**
 	 * 
 	 * @param locale
 	 * @param searchtitle
 	 * @param title
 	 * @param radiobutton
 	 * @param model
 	 * @return
 	 */

 	@Transactional
 	@RequestMapping(value = "/searchBook", method = RequestMethod.POST)
 	public String searchBook(Locale locale, 
 			@RequestParam("searchtitle")String searchtitle, String title,
 			@RequestParam("radiobutton")String radiobutton,
 			Model model) {
 		// デバッグ用ログ
 		logger.info("Welcome searchBookControler.java! The client locale is {}.", locale);
 		
 		if(radiobutton.equals("partial matching")) {

 		model.addAttribute("bookList", booksService.getsearchBookList(searchtitle));
 		
 		}else {
 			model.addAttribute("bookList", booksService.getperfect_matchingList(searchtitle));
 		}
 	

 		return "home";


 	}

 }
