package info.xsh.done.core;

import info.yannxia.java.chameleon.ConvertFactory;
import info.yannxia.java.chameleon.ConvertFactoryImpl;
import info.yannxia.java.chameleon.SpringConvertFactoryImplLoader;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 * Created by xiaohuo on 16/11/28.
 */
@SpringBootApplication(scanBasePackageClasses = SpringConvertFactoryImplLoader.class, scanBasePackages = "info.xsh.done.core")
@EnableJpaRepositories
public class DoneApplication {
    public static void main(String[] args) {
        SpringApplication.run(DoneApplication.class, args);
    }
}


