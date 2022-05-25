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

	/**
	 * 書籍IDに紐づく書籍詳細情報を取得する
	 * @param bookId
	 * @return
	 */
	public BookDetailsInfo getBookInfo(int bookId) {

		// JSPに渡すデータを設定する

		String sql = "select * , case when rent_date is null then '貸出可' else '貸出中' end from books left outer join rentbooks on books.id = rentbooks.book_id where books.id ="
				+ bookId;

		BookDetailsInfo bookDetailsInfo = jdbcTemplate.queryForObject(sql, new BookDetailsInfoRowMapper());

		return bookDetailsInfo;
	}

	/**
	 * 最新の書籍情報を取得する
	 * @return
	 */
	public BookDetailsInfo getLatestBookInfo() {

		// JSPに渡すデータを設定する

		String sql = "select * , case when rent_date is null then '貸出可' else '貸出中' end from books left outer join rentbooks on books.id = rentbooks.book_id where books.id =(select max(id) from books) ";

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
/**
 * 書籍削除
 * @param bookId
 */
	public void deleteBook(Integer bookId) {

		String sql = "WITH book AS ( DELETE FROM books where id = " + bookId + ") DELETE FROM rentbooks where book_id =" + bookId + ";";
			
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
	/**
	 * 部分検索
	 * @param searchtitle
	 * @return
	 */
	public List<BookInfo> getsearchBookList(String searchtitle) {

		List<BookInfo> getedsearchBookList = jdbcTemplate.query(

				"select id,title,author,publisher,publish_date, thumbnail_url, isbn,explain from books where title like '%"
						+ searchtitle + "%'",
				new BookInfoRowMapper());
		return getedsearchBookList;
	}
	/**
	 * 完全一致検索
	 * @param searchtitle
	 * @return
	 */
	public List<BookInfo> getperfect_matchingList(String searchtitle) {

		List<BookInfo>getedperfect_matchingList = jdbcTemplate.query(

				"SELECT * FROM books WHERE title='" + searchtitle + "'",
				new BookInfoRowMapper());
		return getedperfect_matchingList;
	}
	/**
	 * 貸出日付を更新
	 * @param bookId
	 */
	public void updaterent(Integer bookId) {

 		String sql ="UPDATE rentbooks set rent_date=now(),return_date=null WHERE book_id="+bookId;
 		jdbcTemplate.update(sql);
 	}
/**
 * 返却日の更新
 * @param bookId
 */
	public void updatereturn(Integer bookId) {

 		String sql ="UPDATE rentbooks set return_date=now(),rent_date=null WHERE book_id="+bookId;
 		jdbcTemplate.update(sql);
 	}
		// TODO 自動生成されたメソッド・スタブ
		
	}


