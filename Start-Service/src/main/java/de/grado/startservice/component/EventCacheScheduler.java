package de.grado.startservice.component;

import de.grado.startservice.service.EventCacheService;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EventCacheScheduler
{
    private final EventCacheService eventCacheService;

    @Scheduled(fixedRate = 360000)
    public void refresh()
    {
        eventCacheService.refreshCache();
    }
}
