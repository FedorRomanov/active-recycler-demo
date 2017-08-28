package active.recycler.demo;

import com.tomtom.cloud.recycling.ActiveVMRecycler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ActiveRecyclerDemoService {

    private final ActiveVMRecycler recycler;
    private final String instanceId;

    private HealthState healthState = HealthState.Healthy;

    @Autowired
    public ActiveRecyclerDemoService(ActiveVMRecycler recycler) {
        this.recycler = recycler;
        instanceId = recycler.instanceId();
    }

    public String veryImpotantService(boolean makeUnstable){
        //some actual business logic here
        if(makeUnstable) {
            healthState = HealthState.Unstable;
            if(recycler.recycleMe("something when wrong.\n")) {
                return instanceId +" becomes unstable. Triggered active recycling.\n";
            } else {
                return instanceId +" is already unstable, recycling is in progress.\n";
            }
        } else {
            healthState = HealthState.Healthy;
            return instanceId +" is healthy.\n";
        }
    }

    public HealthState healthState() {
        return healthState;
    }
}
