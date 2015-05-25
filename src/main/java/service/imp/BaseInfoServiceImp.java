package service.imp;

import dao.BaseInfoDao;
import entity.BaseInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import service.BaseInfoService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Service
public class BaseInfoServiceImp extends BaseServiceImp<BaseInfo> implements BaseInfoService {
    @Autowired
    BaseInfoDao baseInfoDao;

    @Override
    public List<BaseInfo> search(String keyword, String sort, String order) {
        ArrayList<String> keys = new ArrayList<>();
        keys.add("tableName");
        keys.add("value");
        return baseInfoDao.findByArrayFuz(keys,keyword,sort,order);
    }

    @Override
    public List getByTableName(String tableName) {
        HashMap args = new HashMap<String,String>();
        args.put("tableName",tableName);
        return baseInfoDao.findByMapAcc(args);
    }

    @Override
    public boolean addBaseInfo(BaseInfo baseInfo) {
        return baseInfoDao.save(baseInfo);
    }

    @Override
    public String getNewID(String tableName) {
        HashMap args = new HashMap<String,String>();
        args.put("tableName",tableName);
        List<BaseInfo> list = baseInfoDao.findByMapAcc(args) ;
        if (!list.isEmpty()) {
            int num = 0;
            for (BaseInfo s : list) {
                int temp = Integer.valueOf(s.getId());
                num = num < temp ? temp : num;
            }
            return String.valueOf(num + 1);
        } else {
            List<BaseInfo> all = baseInfoDao.getAll();
            int num = 0;
            for (BaseInfo b : all) {
                int temp = Integer.valueOf(b.getId());
                num = num < temp ? temp : num;
            }
            return String.valueOf(num/10000+1)+"0001";
        }
    }


}