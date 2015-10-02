package service;

import org.snaker.engine.entity.Order;

import java.util.Map;

/**
 * Created by DELL on 2015/6/15.
 */
public interface StandardService {
    Map scoreCalculation(Order order, Map map);

    Map confirmChecking(Order order);

    Map confirmChecking(Order order, Map map);
}
