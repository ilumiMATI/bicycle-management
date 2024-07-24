package pl.lodz.p.bicycle_management.user.domain;

import jakarta.persistence.*;
import lombok.*;
import pl.lodz.p.bicycle_management.annotations.ddd.AggregateRoot;

// TODO: This is just a basic User class without authentication.
//       It needs to be changed later on.

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(of = "id")
@Table(name = "users")
@Entity
@AggregateRoot
public class User {
    @Id
    @SequenceGenerator(
            name = "user_id_seq",
            sequenceName = "user_id_seq",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "user_id_seq"
    )
    @Column(name = "userId")
    private Integer id;

    @Column(nullable = false)
    private String nickname;

    @Column(nullable = false)
    private String email;
}
