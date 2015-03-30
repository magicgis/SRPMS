package engine.utils;

import java.util.Map;

/**
 * DATE:2015/3/30
 * TIME:17:28
 * Created by guofan on 2015/3/30
 */
public class Tool {
    public static String getLatestArgs(Map<String,Object>args){
        int latestNum = 0;
        for (String key : args.keySet()) {
            if (key.matches("WF_\\d+_Submission")) {
                int n = Integer.valueOf(key.substring(key.indexOf('_') + 1, key.lastIndexOf('_')));
                if (latestNum < n) {
                    latestNum = n;
                }
            }
        }
        return "WF_"+latestNum+"_Submission";
    }
}
