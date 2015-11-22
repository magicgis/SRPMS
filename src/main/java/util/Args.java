package util;

import entity.User;
import service.*;

import java.io.File;
import java.util.HashMap;

/**
 * Created by guofan on 2015/5/8.
 */
public class Args {
    public static CrunchifyInMemoryCache<String, User> TokenUser;
    public static CrunchifyInMemoryCache<String, Boolean> PermissionCache;
    public static File StrorePath;
    public static HashMap<String, Class> SERVICES = new HashMap<String, Class>() {
        {
            put("achAppraisal", AchAppraisalService.class);
            put("achAward", AchAwardService.class);
            put("achTran", AchTranService.class);
            put("book", BookService.class);
            put("paper", PaperService.class);
            put("patent", PatentService.class);
            put("project", ProjectService.class);
            put("others", OthersService.class);
            put("food", FoodService.class);
            put("instrument", InstrumentService.class);
            put("medicine", MedicineService.class);
        }
    };
}
