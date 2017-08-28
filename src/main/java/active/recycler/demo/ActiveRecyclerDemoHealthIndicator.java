package active.recycler.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.stereotype.Component;

import static active.recycler.demo.HealthState.Healthy;
import static active.recycler.demo.HealthState.Sick;
import static active.recycler.demo.HealthState.Unstable;

@Component
public class ActiveRecyclerDemoHealthIndicator implements HealthIndicator {

    private final ActiveRecyclerDemoService activeRecyclerDemoService;

    @Autowired
    public ActiveRecyclerDemoHealthIndicator(ActiveRecyclerDemoService activeRecyclerDemoService) {
        this.activeRecyclerDemoService = activeRecyclerDemoService;
    }

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
