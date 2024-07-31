package pl.lodz.p.bicycle_management.rental.domain;

import org.junit.jupiter.api.Test;
import pl.lodz.p.bicycle_management.BaseIT;

import static org.junit.jupiter.api.Assertions.*;

public class RentNumberTest extends BaseIT {

    @Test
    void generated_rent_number_should_contain_3_letters_followed_by_5_digits() {
        RentNumber rentNumber = new RentNumber();
        String letters = rentNumber.toString().substring(0,3);
        String number = rentNumber.toString().substring(3);

        assertFalse(letters.matches("[0-9]"));
        assertFalse(number.matches("[A-Z,a-z]"));
    }
}
