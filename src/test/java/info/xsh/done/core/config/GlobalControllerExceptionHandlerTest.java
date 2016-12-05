package info.xsh.done.core.config;

import info.xsh.done.core.SpringIntegration;
import info.xsh.done.core.exception.ErrorInfo;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.web.client.TestRestTemplate;

/**
 * Created by yann on 2016/12/5.
 */

public class GlobalControllerExceptionHandlerTest extends SpringIntegration {

    @Autowired
    private TestRestTemplate testRestTemplate;

    @Test
    public void test_global_exception_handler() {
        ErrorInfo errorInfo = this.testRestTemplate.getForObject("/api/v1.0/users/1", ErrorInfo.class);

        Assert.assertNotNull(errorInfo);
    }
}