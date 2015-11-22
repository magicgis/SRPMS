package entity;

import java.util.Map;

/**
 * Created by guofan on 2015/11/22.
 */
public interface VirtualEntity {
    String getId();

    void setId(String id);

    String getName();

    void setName(String name);

    String getProcess();

    void setProcess(String process);

    Map getArgMap();

    void setArgMap(Map argMap);

    Standard getStandard();

    void setStandard(Standard standard);

    BaseInfo getDept();

    void setDept(BaseInfo dept);
}
