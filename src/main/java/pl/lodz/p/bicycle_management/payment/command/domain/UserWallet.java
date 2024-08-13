package pl.lodz.p.bicycle_management.payment.command.domain;

import jakarta.persistence.*;
import lombok.*;
import lombok.extern.java.Log;

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
@Log
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

    @Transient
    RentPaymentPolicy rentPaymentPolicy;

    // TODO: Later on add policies based on user bought packets/subscriptions/memberships

    public UserWallet(UserId userId, Money money) {
        this.money = money;
        this.userId = userId;
    }

    public void pay(Money amount) {
        money = money.subtract(amount);
        log.info(prefix() + "Paying: " + amount.asString());
    }

    public Money payForRent(Integer timeInMinutes) {
        if (rentPaymentPolicy == null) {
            throw new IllegalStateException("Rent payment policy not set");
        }
        Money rentPrice = rentPaymentPolicy.calculatePriceForRent(timeInMinutes);
        log.info(prefix() + "Rent price: " + rentPrice.asString());
        this.pay(rentPrice);
        return rentPrice;
    }

    public void deposit(Money amount) {
        money = money.add(amount);
        log.info(prefix() + " Depositing: " + amount.asString());
    }

    public boolean hasMoney(Money amount) {
        return money.compareTo(amount) > 0;
    }

    private String prefix() {
        return "[UserWallet with userId " + userId.value() + "] ";
    }
}
