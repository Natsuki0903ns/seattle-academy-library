package jp.co.seattle.library.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import jp.co.seattle.library.dto.BookDetailsInfo;
import jp.co.seattle.library.dto.BookInfo;
import jp.co.seattle.library.rowMapper.BookDetailsInfoRowMapper;
import jp.co.seattle.library.rowMapper.BookInfoRowMapper;

/**
 * 書籍サービス
 * 
 * booksテーブルに関する処理を実装する
 */
@Service
public class BooksService {
	final static Logger logger = LoggerFactory.getLogger(BooksService.class);
	@Autowired
	private JdbcTemplate jdbcTemplate;

	/**
	 * 書籍リストを取得する
	 *
	 * @return 書籍リスト
	 */
	public List<BookInfo> getBookList() {

		// TODO 取得したい情報を取得するようにSQLを修正
		List<BookInfo> getedBookList = jdbcTemplate.query(
				"select id,title,author,publisher,publish_date, thumbnail_url, isbn,explain from books order by title asc",
				new BookInfoRowMapper());

		return getedBookList;
	}

	public BookDetailsInfo getBookInfo(int bookId) {

		// JSPに渡すデータを設定する
		String sql = "select * from books left join rentbooks on books.id = rentbooks.book_id where books.id = "
				+ bookId;

		BookDetailsInfo bookDetailsInfo = jdbcTemplate.queryForObject(sql, new BookDetailsInfoRowMapper());

		return bookDetailsInfo;
	}

	public BookDetailsInfo getLatestBookInfo() {

		// JSPに渡すデータを設定する
		String sql = "select * from books left join rentbooks on books.id = rentbooks.book_id where books.id = (select max(id)from books);";

		BookDetailsInfo latestBookDetailsInfo = jdbcTemplate.queryForObject(sql, new BookDetailsInfoRowMapper());

		return latestBookDetailsInfo;
	}

	/**
	 * 書籍を登録する
	 *
	 * @param bookInfo 書籍情報
	 */
	public void registBook(BookDetailsInfo bookInfo) {

		String sql = "INSERT INTO books (title, author,publisher,publish_date,thumbnail_name,thumbnail_url,isbn,explain,reg_date,upd_date) VALUES ('"
				+ bookInfo.getTitle() + "','" + bookInfo.getAuthor() + "','" + bookInfo.getPublisher() + "','"
				+ bookInfo.getPublishDate() + "','" + bookInfo.getThumbnailName() + "','" + bookInfo.getThumbnailUrl()
				+ "','" + bookInfo.getIsbn() + "','" + bookInfo.getExplain() + "'," + "now()," + "now())";

		jdbcTemplate.update(sql);

	}

	public void deleteBook(Integer bookId) {

		String sql = "DELETE FROM books WHERE id = " + bookId + ";";
		jdbcTemplate.update(sql);
	}

	/**
	 * 書籍を更新する
	 * 
	 * @param id       書籍id
	 * @param bookInfo 書籍情報
	 **/

	public void updateBook(BookDetailsInfo bookInfo, int id) {

		String sql = "update books set title = " + "'" + bookInfo.getTitle() + "', " + "author = '"
				+ bookInfo.getAuthor() + "', " + "publisher = '" + bookInfo.getPublisher() + "', "
				+ "thumbnail_name = '" + bookInfo.getThumbnailName() + "', " + "thumbnail_url = '"
				+ bookInfo.getThumbnailUrl() + "', " + "publish_date = '" + bookInfo.getPublishDate() + "', "
				+ "isbn = '" + bookInfo.getIsbn() + "', " + "explain = '" + bookInfo.getExplain() + "' where id = "
				+ id;

		jdbcTemplate.update(sql);
	}

	public List<BookInfo> getsearchBookList(String searchtitle) {

		List<BookInfo> getedsearchBookList = jdbcTemplate.query(

				"select id,title,author,publisher,publish_date, thumbnail_url, isbn,explain from books where title like '%"
						+ searchtitle + "%'",
				new BookInfoRowMapper());
		return getedsearchBookList;
	}

}
