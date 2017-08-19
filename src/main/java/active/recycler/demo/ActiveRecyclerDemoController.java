package active.recycler.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
public class ActiveRecyclerDemoController {

    @Autowired
    ActiveRecyclerDemoService activeRecyclerDemoService;
    
    @RequestMapping("/may-cause-unrecoverable-error")
    public String mayCauseUnrecoverableError() {
        activeRecyclerDemoService.mayCauseUnrecoverableError();
        if(activeRecyclerDemoService.isHealthy()) {
            return "I am healthy";
        } else {
            return "I am sick";
        }
    }

}
