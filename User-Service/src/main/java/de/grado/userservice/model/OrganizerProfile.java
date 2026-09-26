package de.grado.userservice.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "organizer_profiles")
@Data
public class OrganizerProfile
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String organizationName;
    private String email;
    private String password;
}
