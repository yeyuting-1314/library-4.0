package library.system.dto;

/**
 * @author yeyuting
 * @create 2021/2/25
 */
public class Book {
    Integer id ;

    String bookName ;

    String bookCode ;

    String bookPublisher ;

    String bookType ;

    String extendedField1 ;

    String extendedField2 ;

    String extendedField3 ;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public String getBookCode() {
        return bookCode;
    }

    public void setBookCode(String bookCode) {
        this.bookCode = bookCode;
    }

    public String getBookPublisher() {
        return bookPublisher;
    }

    public void setBookPublisher(String bookPublisher) {
        this.bookPublisher = bookPublisher;
    }

    public String getBookType() {
        return bookType;
    }

    public void setBookType(String bookType) {
        this.bookType = bookType;
    }
}
