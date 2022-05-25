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

@Controller // APIの入り口
public class ReturnBookController {
	final Logger logger = (Logger) LoggerFactory.getLogger(ReturnBookController.class);

	@Autowired
	private BooksService booksService;

	@Autowired
	private RentBooksService rentBooksService;

	/**
	 * 対象書籍を返却する
	 *
	 * @param locale ロケール情報
	 * @param bookId 書籍ID
	 * @param model  モデル情報
	 * @return 遷移先画面名
	 */
	@Transactional
	@RequestMapping(value = "/returnBook", method = RequestMethod.POST)
	public String returnBook(Locale locale, @RequestParam("bookId") Integer bookId, Model model) {

		Integer rentdate = rentBooksService.rentdate(bookId);

		if ( rentdate > 0) {
			booksService.updatereturn(bookId);

		} else {
			
			model.addAttribute("errorMessage", "貸し出しされていません。");
		}

		model.addAttribute("bookDetailsInfo", booksService.getBookInfo(bookId));
		return "details";
	}
}
