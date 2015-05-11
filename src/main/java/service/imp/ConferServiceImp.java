package service.imp;

import entity.Confer;
import service.ConferService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ConferServiceImp extends BaseServiceImp<Confer> implements ConferService {
    @Override
    public List<Confer> search(String keyword, String sort, String order) {
        return null;
    }
}