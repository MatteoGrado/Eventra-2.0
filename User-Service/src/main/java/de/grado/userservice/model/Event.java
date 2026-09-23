package de.grado.userservice.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Column;
import jakarta.persistence.Table;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Table(name = "user_service_event")
@Data
public class Event
{
    @Id
    private Long id;
    private String eventName;
    private String eventDescription;
    private LocalDateTime eventDate;
    private String location;
    private String eventOrganizer;
}
