package by.minsk.kes.exmo.observer.repository;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.jmx.export.annotation.ManagedAttribute;
import org.springframework.jmx.export.annotation.ManagedResource;
import org.springframework.stereotype.Repository;

@Repository
@ManagedResource(description = "Manages Exmo Api resources")
public class ResourceRepository {

    @Value("${exmo.pair}")
    private String pairs;

    @ManagedAttribute
    public String getPairs() {
        return pairs;
    }

    @ManagedAttribute
    public void setPairs(String pairs) {
        this.pairs = pairs;
    }
}
