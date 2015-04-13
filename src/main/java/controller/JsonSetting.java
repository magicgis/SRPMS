package controller;

import org.codehaus.jackson.map.ObjectMapper;

import javax.ws.rs.ext.ContextResolver;

/**
 * DATE:2015/4/9
 * TIME:19:34
 * Created by guofan on 2015/4/9
 */
public class JsonSetting implements ContextResolver<ObjectMapper> {
    @Override
    public ObjectMapper getContext(Class<?> type) {
        return null;
    }
}
