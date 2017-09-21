package active.recycler.demo;

import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.stereotype.Component;

@Component
public class ActiveRecyclerDemoHealthIndicator implements HealthIndicator {

    private final ActiveRecyclerDemoService activeRecyclerDemoService;

    public ActiveRecyclerDemoHealthIndicator(ActiveRecyclerDemoService activeRecyclerDemoService) {
        this.activeRecyclerDemoService = activeRecyclerDemoService;
    }

    //used by load-balancer to put node out of rotation
    @Override
    public Health health() {
        switch (activeRecyclerDemoService.healthState()) {
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
