package example.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Table(name = "Users")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;
    private String name;
    private String username;
    private String password;
    private String confirmPassword;
    private boolean active;
    private String role;

    @JsonManagedReference
    @OneToMany(mappedBy = "user",cascade = CascadeType.ALL, orphanRemoval = true)
    private java.util.List<List> lists = new ArrayList<>();


    public User(String name, String username, String password, boolean active, String role) {
        this.name = name;
        this.username = username;
        this.password = password;
        this.active = active;
        this.role = role;
    }
}
