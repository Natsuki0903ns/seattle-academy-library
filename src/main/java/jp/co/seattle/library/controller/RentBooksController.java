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

import jp.co.seattle.library.dto.BorrowingHistoryInfo;
import jp.co.seattle.library.service.BooksService;
import jp.co.seattle.library.service.RentBooksService;

@Controller // APIの入り口
public class RentBooksController {
	final static Logger logger = LoggerFactory.getLogger(RentBooksController.class);
	@Autowired
	private BooksService booksService;
	@Autowired
	private RentBooksService rentBooksService;

	/**
	 * 対象書籍削除
	 * 
	 * @param locale ロケール情報
	 * @param bookId 書籍ID
	 * @param model  モデル情報
	 * @return 遷移先画面名
	 */

	@Transactional
	@RequestMapping(value = "/rentBook", method = RequestMethod.POST)
	public String rentBook(Locale locale, @RequestParam("bookId") Integer bookId, Model model) {
		logger.info("Welcome rentBooks! The client locale is {}.", locale);
		BorrowingHistoryInfo historybook = rentBooksService.history(bookId);

		
		
		
		if (historybook == null) {
			rentBooksService.rentBook(bookId);

		} else {
			Integer rentdate = rentBooksService.rentdate(bookId);
			if (rentdate == 0) {

				booksService.updaterent(bookId);

			} else {
				model.addAttribute("errorMessage", "貸し出済です。");

			}
		}

		model.addAttribute("bookDetailsInfo", booksService.getBookInfo(bookId));

		return "details";

	}
}
