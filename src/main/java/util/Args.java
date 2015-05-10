package util;

import entity.User;
import entity.security.Url;

import java.util.HashMap;

/**
 * Created by guofan on 2015/5/8.
 */
public class Args {
    public static CrunchifyInMemoryCache<String, User> TokenUser;
    public static CrunchifyInMemoryCache<String, Boolean> PermissionCache;
}
