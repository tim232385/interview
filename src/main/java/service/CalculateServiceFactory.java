package service;

import enums.LocationType;
import lombok.extern.slf4j.Slf4j;
import org.reflections.Reflections;
import service.CalculateService;

import java.util.HashMap;
import java.util.Set;

@Slf4j
public class CalculateServiceFactory extends HashMap<LocationType, CalculateService> {

    public CalculateServiceFactory() {
        Reflections reflections = new Reflections("service");
        Set<Class<? extends CalculateService>> subTypes = reflections.getSubTypesOf(CalculateService.class);
        subTypes.stream()
                .map(c -> {
                    try {
                        log.info("init calculateService:[{}]",  c.getSimpleName());
                        return (CalculateService) c.newInstance();
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                }).forEach(calculateService -> this.put(calculateService.getLocationType(), calculateService));
    }
}
