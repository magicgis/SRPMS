package engine.filter;

import engine.entity.OrderActorDao;
import org.snaker.engine.SnakerInterceptor;
import org.snaker.engine.core.Execution;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * DATE:2015/3/22
 * TIME:17:28
 * Created by guofan on 2015/3/22
 */
public class Start implements SnakerInterceptor {

    @Override
    public void intercept(Execution execution) {
        BeanFactory factory = new ClassPathXmlApplicationContext("classpath:/applicationContext.xml");
        OrderActorDao orderActorDao =(OrderActorDao) factory.getBean("orderActorDao");
        String actor = "" ;
    }
}
