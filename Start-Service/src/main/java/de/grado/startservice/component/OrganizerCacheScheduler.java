package de.grado.startservice.component;

import de.grado.startservice.service.OrganizerCacheService;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrganizerCacheScheduler
{
    private final OrganizerCacheService organizerCacheService;

    @Scheduled(fixedRate = 360000)
    public void refresh()
    {
        organizerCacheService.refreshCache();
    }
}
