package info.xsh.done.core.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by yangxueying on 2016/12/8.
 */
@Controller
public class SecurityController {

    @RequestMapping("/login")
    public String login() {
        return "login";
    }

    @RequestMapping("/test")
    public String index() {
        return "test";
    }

    @RequestMapping("/")
    public String suc() {
        return "test";
    }
}
