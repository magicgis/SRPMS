package service.imp;

import entity.Attachment;
import org.springframework.stereotype.Service;
import service.AttachmentService;

import java.util.List;

@Service
public class AttachmentServiceImp extends BaseServiceImp<Attachment> implements AttachmentService {
    @Override
    public List<Attachment> search(String keyword, String sort, String order) {
        return null;
    }
}