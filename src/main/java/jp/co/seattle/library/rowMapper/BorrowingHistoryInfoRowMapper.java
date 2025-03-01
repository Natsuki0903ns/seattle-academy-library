package jp.co.seattle.library.rowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.RowMapper;

import jp.co.seattle.library.dto.BorrowingHistoryInfo;

@Configuration
public class BorrowingHistoryInfoRowMapper implements RowMapper<BorrowingHistoryInfo> {

    @Override
    public BorrowingHistoryInfo mapRow(ResultSet rs, int rowNum) throws SQLException {
        // Query結果（ResultSet rs）を、オブジェクトに格納する実装
    	BorrowingHistoryInfo bookDetailsInfo = new BorrowingHistoryInfo();

        bookDetailsInfo.setBookId(rs.getInt("book_id"));
        bookDetailsInfo.setTitle(rs.getString("title"));
        bookDetailsInfo.setRentDate(rs.getString("rent_date"));
        bookDetailsInfo.setReturnDate(rs.getString("return_date"));
        return bookDetailsInfo;
    }

}
