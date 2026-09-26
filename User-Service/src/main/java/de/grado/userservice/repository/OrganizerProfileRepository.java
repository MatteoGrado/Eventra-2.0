package de.grado.userservice.repository;

import de.grado.userservice.model.OrganizerProfile;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrganizerProfileRepository extends JpaRepository<OrganizerProfile, Long>
{
    OrganizerProfile findByEmail(String email);
    boolean existsByEmail(String email);
}
