package active.recycler.demo;

import com.tomtom.cloud.recycling.ActiveVMRecycler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ActiveRecyclerDemoService {

    @Autowired
    ActiveVMRecycler recycler;

    private boolean isHealthy = true;

    public boolean isHealthy() {
        return isHealthy;
    }

    public void mayCauseUnrecoverableError(){
        if (isHealthy) {
            isHealthy = false;
            recycler.recycleMe("something bad happened!");
        } else {
            isHealthy = true;
        }
    }
}
