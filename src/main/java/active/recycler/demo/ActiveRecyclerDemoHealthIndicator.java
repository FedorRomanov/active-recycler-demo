package active.recycler.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.stereotype.Component;

@Component
public class ActiveRecyclerDemoHealthIndicator implements HealthIndicator {

    @Autowired
    ActiveRecyclerDemoService activeRecyclerDemoService;

    @Override
    public Health health() {
        if (!activeRecyclerDemoService.isHealthy()) {
            return Health.down().build();
        }
        return Health.up().build();
    }

}
