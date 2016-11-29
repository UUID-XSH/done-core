package info.xsh.done.core;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 * Created by xiaohuo on 16/11/28.
 */
@SpringBootApplication
@EnableJpaRepositories
public class DoneApplication {
    public static void main(String[] args) {
        SpringApplication.run(DoneApplication.class,args);
    }
}
