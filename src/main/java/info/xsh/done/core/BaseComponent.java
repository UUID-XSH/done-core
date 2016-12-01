package info.xsh.done.core;

import info.yannxia.java.chameleon.ConvertFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;

/**
 * Created by yann on 2016/12/1.
 */
public abstract class BaseComponent {

    @Autowired
    private ApplicationContext applicationContext;

    public ConvertFactory convertFactory() {
        return applicationContext.getBean(ConvertFactory.class);
    }

}
