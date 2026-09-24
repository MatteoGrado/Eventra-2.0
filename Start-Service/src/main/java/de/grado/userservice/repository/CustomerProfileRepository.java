package de.grado.userservice.repository;

import de.grado.userservice.model.CustomerProfile;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerProfileRepository extends JpaRepository<CustomerProfile, Long>
{
}
