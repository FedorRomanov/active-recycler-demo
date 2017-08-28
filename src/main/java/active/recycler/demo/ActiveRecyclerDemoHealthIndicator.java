package active.recycler.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.stereotype.Component;

@Component
public class ActiveRecyclerDemoHealthIndicator implements HealthIndicator {

    private final ActiveRecyclerDemoService activeRecyclerDemoService;

    @Autowired
    public ActiveRecyclerDemoHealthIndicator(ActiveRecyclerDemoService activeRecyclerDemoService) {
        this.activeRecyclerDemoService = activeRecyclerDemoService;
    }

    @Override
    public Health health() {
        switch (activeRecyclerDemoService.getHealthState()) {
            case Healthy:
            case Unstable:
                return Health.up().build();
            case Sick:
                return Health.down().build();
            default:
                throw new IllegalStateException("Unknown health state");
        }
    }

}
