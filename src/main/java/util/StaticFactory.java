package util;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by guofan on 2015/6/24.
 */
public class StaticFactory {
    private static BeanFactory beanFactory;

    public static Object getBean(String bean) {
        if (beanFactory == null) {
            init();
        }
        return beanFactory.getBean(bean);
    }

    public static void init() {
        beanFactory = new ClassPathXmlApplicationContext("classpath:/applicationContext.xml",
                "classpath:/applicationContext-snaker.xml");
    }
}
