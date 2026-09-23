package de.grado.userservice.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
// The organizer data is owned by the organizer service and is stored in the
// plural table.  The singular table is an old, empty table created by the
// previous mapping and must not be used for the listing.
@Table(name = "organizers")
@Data
public class Organizer
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String organizationName;
    private String email;
}
