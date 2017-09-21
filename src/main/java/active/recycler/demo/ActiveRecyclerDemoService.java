package active.recycler.demo;

import com.tomtom.cloud.recycling.ActiveVMRecycler;
import org.springframework.stereotype.Component;

@Component
public class ActiveRecyclerDemoService {

    private final ActiveVMRecycler recycler;
    private final String instanceId;

    private HealthState healthState = HealthState.Healthy;

    public ActiveRecyclerDemoService(ActiveVMRecycler recycler) {
        this.recycler = recycler;

        //just helper method exposing cloud-specific instance-id for demonstration
        instanceId = recycler.instanceId();
    }

    public String veryImportantService(boolean makeUnstable){

        //some actual business logic here

        if(makeUnstable) {  //set externally in demo, but could be result of some event in your business-logic
            healthState = HealthState.Unstable;

            if(recycler.scaleOutAndRecycle("something when wrong.\n")) {
                return instanceId +" becomes unstable. Triggered scaling out and recycling.\n";
            } else {
                return "Failed to scale out and recycle " + instanceId +" . May be recycling is already in progress.\n";
            }

        } else {
            //not very likely to happen, once triggered recycling can't be aborted
            healthState = HealthState.Healthy;
            return instanceId +" is healthy.\n";
        }
    }

    HealthState healthState() {
        return healthState;
    }
}
