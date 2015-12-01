package service.imp;

import dao.BookDao;
import entity.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import service.BookService;

import java.util.Arrays;
import java.util.List;

/**
 * Created by guofan on 2015/10/11.
 */
@Service
public class BookServiceImp extends BaseServiceImp<Book> implements BookService {
    @Autowired
    BookDao bookDao;

    @Override
    public List<Book> search(String keyword, String sort, String order) {
        List<String> keys = Arrays.asList("name", "isbn");
        return bookDao.findByArrayFuz(keys, keyword, sort, order);
    }
}
