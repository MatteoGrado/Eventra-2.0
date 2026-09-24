package de.grado.userservice.repository;

import de.grado.userservice.model.Organizer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrganizerRepository extends JpaRepository<Organizer, Long>
{
}
