package service.imp;

import entity.Instrument;
import org.springframework.stereotype.Service;
import service.InstrumentService;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by guofan on 2015/11/19.
 */
@Service
public class InstrumentServiceImp extends BaseServiceImp<Instrument> implements InstrumentService {
    @Override
    public List<Instrument> search(String keyword, String sort, String order) {
        return new ArrayList<>();
    }
}

