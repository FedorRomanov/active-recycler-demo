package active.recycler.demo;

import com.tomtom.cloud.recycling.ActiveVMRecycler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ActiveRecyclerDemoService {

    private final ActiveVMRecycler recycler;

    private HealthState healthState = HealthState.Healthy;

    @Autowired
    public ActiveRecyclerDemoService(ActiveVMRecycler recycler) {
        this.recycler = recycler;
    }

    public HealthState healthStatus() {
        return healthState;
    }

    public String veryImpotantService(boolean makeUnstable){
        //some actual business logic here
        if(makeUnstable) {
            healthState = HealthState.Unstable;
            if(recycler.recycleMe("something when wrong.\n")) {
                return recycler.instanceId() +" becomes unstable. Triggered active recycling.\n";
            } else {
                return recycler.instanceId() +" is already unstable, recycling is in progress.\n";
            }
        } else {
            healthState = HealthState.Healthy;
            return recycler.instanceId() +" is healthy.\n";
        }
    }

    public HealthState getHealthState() {
        return healthState;
    }
}
