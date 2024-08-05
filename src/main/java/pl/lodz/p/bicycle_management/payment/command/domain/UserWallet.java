package pl.lodz.p.bicycle_management.payment.command.domain;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(
        name = "user_wallets",
        uniqueConstraints = {
                @UniqueConstraint(
                        name = "user_wallets_user_id_unique",
                        columnNames = "userId"
                )
        }
)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class UserWallet {
    @Id
    @SequenceGenerator(
            name = "user_wallet_id_seq",
            sequenceName = "user_wallet_id_seq",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "user_wallet_id_seq"
    )
    Integer id;

    @Column
    @Embedded
    UserId userId;

    @Column(nullable = false)
    @Embedded
    Money money;

    @Version
    Integer version;

    public UserWallet(UserId userId, Money money) {
        this.money = money;
        this.userId = userId;
    }

    public void pay(Money amount) {
        money.subtract(amount);
    }

    public void deposit(Money amount) {
        money.add(amount);
    }
}
