package service.imp;

import entity.BookSta;
import service.BookStaService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookStaServiceImp extends BaseServiceImp<BookSta> implements BookStaService {
    @Override
    public List<BookSta> search(String keyword, String sort, String order) {
        return null;
    }
}