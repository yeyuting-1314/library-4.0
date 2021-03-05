package library.system.dao;

import library.system.dto.Book;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author yeyuting
 * @create 2021/2/26
 */
@Repository
@Mapper
public interface BookMapper {
    //查询所有书籍
    public List<Book> selectAllBooks() ;

    //新增一本书
    public Boolean insertOneBook (Book book) ;

    //新增若干本书
    public Boolean insertManyBooks(List<Book> books) ;

}
