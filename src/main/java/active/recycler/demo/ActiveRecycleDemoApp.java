package active.recycler.demo;

import com.tomtom.cloud.recycling.aws.config.AwsRecyclingAutoConfig;
import com.tomtom.cloud.recycling.config.RecyclingAutoConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.ImportAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@ImportAutoConfiguration({RecyclingAutoConfig.class, AwsRecyclingAutoConfig.class})
@RestController
public class ActiveRecycleDemoApp {

    @Autowired
    ActiveRecyclerDemoService activeRecyclerDemoService;

    public static void main(String[] args) {
        SpringApplication.run(ActiveRecycleDemoApp.class, args);
    }

    @RequestMapping("/very-important-service")
    public String veryImportantService(@RequestParam(value="makeUnstable", defaultValue="false") boolean makeUnstable) {
        return activeRecyclerDemoService.veryImportantService(makeUnstable);
    }

}
