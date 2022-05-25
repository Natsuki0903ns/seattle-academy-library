package jp.co.seattle.library.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import jp.co.seattle.library.dto.BorrowingHistoryInfo;
import jp.co.seattle.library.rowMapper.BorrowingHistoryInfoRowMapper;

@Service
public class RentBooksService {

	final static Logger logger = LoggerFactory.getLogger(RentBooksService.class);
	@Autowired
	private JdbcTemplate jdbcTemplate;

	/**
	 * 書籍を借りる
	 *
	 * @param bookId 書籍ID
	 */

	public void rentBook(Integer bookId) {

		String sql = "INSERT INTO rentBooks(book_id, rent_date) SELECT " + bookId
				+ " , now() WHERE NOT EXISTS ( SELECT * FROM rentBooks WHERE book_id = " + bookId + ")";

		jdbcTemplate.update(sql);

	}

	/**
	 * 
	 */

	public BorrowingHistoryInfo history(Integer bookId) {

		try {
			String sql = "select book_id, rent_date, return_date, title from rentbooks left outer join books on books.id = rentbooks.book_id where books.id ="+ bookId +";";
			BorrowingHistoryInfo historybook = jdbcTemplate.queryForObject(sql, new BorrowingHistoryInfoRowMapper());
			return historybook;

		} catch (Exception e) {
			return null;
		}
	}
	public Integer rentdate(Integer bookId) {
		try {
			String sql = "SELECT COUNT (rent_date) FROM rentbooks where rentbooks.book_id=" + bookId+";";
			Integer rentDate = jdbcTemplate.queryForObject(sql, Integer.class);
			return rentDate;
		} catch (Exception e) {
			return 0;
		}
	}

	/**
	 * 借りた書籍を数える
	 * 
	 * @param bookId 書籍ID
	 * @return 遷移先
	 */

	public Integer countRentBook(Integer bookId) {

		String sql = "select count (book_id) from rentBooks where book_id = " + bookId;

		return jdbcTemplate.queryForObject(sql, Integer.class);

	}

	/**
	 * 返却する書籍を削除する
	 * 
	 * @param bookId 書籍ID
	 */
	//public void returnBook(Integer bookId) {
		//String sql = "delete from rentBooks where book_id =" + bookId;

		//jdbcTemplate.update(sql);

	//}

	public List<BorrowingHistoryInfo> BorrowingHistoryList() {
		List<BorrowingHistoryInfo> BorrowingHistoryList = jdbcTemplate.query(
				
				"select books.title,rentbooks.book_id,rentbooks.rent_date,rentbooks.return_date FROM rentbooks LEFT OUTER JOIN books ON books.id = rentbooks.book_id",

				new BorrowingHistoryInfoRowMapper());
		return BorrowingHistoryList;
	}

}
