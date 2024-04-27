package org.sid.backendproject.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.*;

@Entity
@NoArgsConstructor @AllArgsConstructor @Getter @Setter @ToString @Builder
public class Student {
    @Id
    private String id;
    private String firstName;
    private String lastName;
    @Column(unique = true)// c est pour avoir le code unique
    private String code;
    private String programId;
    private String photo;
}
