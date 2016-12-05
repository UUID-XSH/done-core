package info.xsh.done.core;

import info.xsh.done.core.controller.BaseController;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Created by yann on 2016/12/5.
 */

@RunWith(SpringRunner.class)
@SpringBootTest(classes = DoneApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public abstract class SpringIntegration extends BaseController {
}
