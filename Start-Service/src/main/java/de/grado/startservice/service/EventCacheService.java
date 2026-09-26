package de.grado.startservice.service;

import de.grado.startservice.model.Event;
import de.grado.startservice.repository.EventRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.List;

@Service
@RequiredArgsConstructor
public class EventCacheService
{
    private static final String CACHE_KEY = "events";
    private final RedisTemplate<String, List<Event>> redisTemplate;
    private final EventRepository eventRepository;

    public List<Event> getEvents()
    {

        List<Event> events = redisTemplate.opsForValue().get(CACHE_KEY);

        if (events != null) {
            return events;
        }

        return refreshCache();
    }

    public List<Event> refreshCache()
    {

        List<Event> events = eventRepository.findAll();

        redisTemplate.opsForValue().set(
                CACHE_KEY,
                events,
                Duration.ofMinutes(10)
        );

        return events;
    }
}
