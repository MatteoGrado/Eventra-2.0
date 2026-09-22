package de.grado.userservice.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
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

    @OneToOne
    @JoinColumn(name = "user_id", nullable = false, unique = true)
    private User user;

    protected OrganizerProfile()
    {
    }

    public OrganizerProfile(String organizationName)
    {
        this.organizationName = organizationName;
    }

    public void setUser(User user)
    {
        this.user = user;
        if (user != null && user.getOrganizerProfile() != this) {
            user.setOrganizerProfile(this);
        }
    }
}
