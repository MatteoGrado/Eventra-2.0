package de.grado.startservice.service;

import de.grado.startservice.model.Event;
import de.grado.startservice.repository.EventRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EventService
{
    private final EventRepository eventRepository;

    @Transactional
    public List<Event> search(String query)
    {
        if (query == null || query.isBlank()) {
            return eventRepository.findAll();
        }

        return eventRepository.fuzzySearch(query);
    }
}
