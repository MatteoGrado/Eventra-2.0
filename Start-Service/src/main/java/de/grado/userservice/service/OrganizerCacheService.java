package de.grado.userservice.service;

import de.grado.userservice.model.Organizer;
import de.grado.userservice.repository.OrganizerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrganizerCacheService
{
    private static final String CACHE_KEY = "organizers:v4";
    private final RedisTemplate<String, List<Organizer>> organizerRedisTemplate;
    private final OrganizerRepository organizerRepository;

    public List<Organizer> getOrganizers()
    {
        List<Organizer> cachedOrganizers = organizerRedisTemplate.opsForValue().get(CACHE_KEY);

        if (cachedOrganizers != null) {
            return cachedOrganizers;
        }

        return refreshCache();
    }

    public List<Organizer> refreshCache()
    {
        List<Organizer> organizers = organizerRepository.findAll();

        organizerRedisTemplate.opsForValue().set(
                CACHE_KEY,
                organizers,
                Duration.ofMinutes(10)
        );

        return organizers;
    }
}
