package library.system.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * @author yeyuting
 * @create 2021/2/25
 */
public class BorrowingRecords {
    Integer id ;

    String userId ;

    String bookCode ;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    Date operateTime ;

    String borrowingStatus ;

    String extendedField1 ;

    String extendedField2 ;

    String extendedField3 ;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getBookCode() {
        return bookCode;
    }

    public void setBookCode(String bookCode) {
        this.bookCode = bookCode;
    }


    public String getBorrowingStatus() {
        return borrowingStatus;
    }

    public void setBorrowingStatus(String borrowingStatus) {
        this.borrowingStatus = borrowingStatus;
    }

    public Date getOperateTime() {
        return operateTime;
    }

    public void setOperateTime(Date operateTime) {
        this.operateTime = operateTime;
    }
}
