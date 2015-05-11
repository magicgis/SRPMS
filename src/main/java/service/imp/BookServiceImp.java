package service.imp;

import entity.Book;
import service.BookService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookServiceImp extends BaseServiceImp<Book> implements BookService {
    @Override
    public List<Book> search(String keyword, String sort, String order) {
        return null;
    }
}