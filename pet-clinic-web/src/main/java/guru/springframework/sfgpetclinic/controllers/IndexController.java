package guru.springframework.sfgpetclinic.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexController {
    @RequestMapping({"", "/", "index", "index.html"})
    public String index(){
        return "index";
    }

    @RequestMapping({"/owners/find","/owners/find.html","/oups","oups.html" })
    public String  notImplemented(){
        return "notimplemented";
    }
}
