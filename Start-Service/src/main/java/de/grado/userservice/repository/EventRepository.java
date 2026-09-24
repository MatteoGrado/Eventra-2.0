package de.grado.userservice.repository;

import de.grado.userservice.model.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface EventRepository extends JpaRepository<Event, Long>
{
    @Query(value = """
        SELECT *
        FROM user_service_event e
        WHERE
            similarity(e.event_name, :query) > 0.2
            OR similarity(e.location, :query) > 0.2
            OR similarity(e.event_description, :query) > 0.2
            OR similarity(e.event_organizer, :query) > 0.2
        ORDER BY
            GREATEST(
                similarity(e.event_name, :query),
                similarity(e.location, :query),
                similarity(e.event_description, :query),
                similarity(e.event_organizer, :query)
            ) DESC
        LIMIT 50
        """,
            nativeQuery = true)
    List<Event> fuzzySearch(@Param("query") String query);
}
