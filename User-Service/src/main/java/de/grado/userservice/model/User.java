package de.grado.userservice.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "users")
@Data
public class User
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String email;

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private OrganizerProfile organizerProfile;

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private CustomerProfile customerProfile;

    protected User()
    {
    }

    public User(String email)
    {
        this.email = email;
    }

    public void setOrganizerProfile(OrganizerProfile organizerProfile)
    {
        this.organizerProfile = organizerProfile;
        if (organizerProfile != null && organizerProfile.getUser() != this)
        {
            organizerProfile.setUser(this);
        }
    }

    public void setCustomerProfile(CustomerProfile customerProfile)
    {
        this.customerProfile = customerProfile;
        if (customerProfile != null && customerProfile.getUser() != this)
        {
            customerProfile.setUser(this);
        }
    }
}
