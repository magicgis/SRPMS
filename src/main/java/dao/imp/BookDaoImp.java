package dao.imp;

import entity.Book;
import dao.BookDao;
import org.springframework.stereotype.Repository;

@Repository
public class BookDaoImp extends BaseDaoImp<Book> implements BookDao {
}