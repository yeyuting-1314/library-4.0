package library.system.dao;

import library.base.Result;
import library.system.dto.BorrowingRecords;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author yeyuting
 * @create 2021/2/26
 */
@Repository
@Mapper
public interface BorrowingRecordsMapper {

    public List<BorrowingRecords> selectAllBorrowingRecords() ;

    public Boolean borrowBooks(BorrowingRecords borrowingRecords) ;

    public Boolean returnBooks(BorrowingRecords borrowingRecords) ;
}
