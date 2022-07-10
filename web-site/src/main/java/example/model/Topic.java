package example.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.Stack;

import static javax.persistence.GenerationType.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Table(name = "Topics")
public class Topic {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;
    private String name;

    @JsonBackReference
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @JsonManagedReference
    @OneToMany(mappedBy = "topic", cascade = CascadeType.ALL)
    @ToString.Exclude
    @LazyCollection(LazyCollectionOption.FALSE)
    private List<Task> tasks = new ArrayList<>();
}
