package org.sid.tripuserservice.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;

@Entity
@Data @AllArgsConstructor @NoArgsConstructor @ToString
public class AppUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private String username;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;
    private boolean activated;
    private String firstname;
    private String lastname;
    private String email;
    private int phone;
    private String bio;
    private String country;
    private String PhotoProfilSrc;
    private String gender;
    @ManyToMany(fetch = FetchType.EAGER)
    private Collection <AppRole> roles = new ArrayList<>();
}
