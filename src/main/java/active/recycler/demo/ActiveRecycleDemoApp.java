package active.recycler.demo;

import java.util.Arrays;

import com.tomtom.cloud.recycling.aws.config.AwsRecyclingAutoConfig;
import com.tomtom.cloud.recycling.config.RecyclingAutoConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.ImportAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
@ImportAutoConfiguration({RecyclingAutoConfig.class, AwsRecyclingAutoConfig.class})
public class ActiveRecycleDemoApp {
    
    public static void main(String[] args) {
        ApplicationContext ctx = SpringApplication.run(ActiveRecycleDemoApp.class, args);
        
        System.out.println("Let's inspect the beans provided by Spring Boot:");
        
        String[] beanNames = ctx.getBeanDefinitionNames();
        Arrays.sort(beanNames);
        for (String beanName : beanNames) {
            System.out.println(beanName);
        }
    }

}
