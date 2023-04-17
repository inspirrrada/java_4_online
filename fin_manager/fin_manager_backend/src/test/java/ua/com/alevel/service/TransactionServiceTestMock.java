package ua.com.alevel.service;

import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.function.Executable;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import ua.com.alevel.persistence.entity.Account;
import ua.com.alevel.persistence.entity.User;
import ua.com.alevel.service.impl.UserServiceImpl;

import java.math.BigDecimal;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;


@RunWith(SpringRunner.class)
public class TransactionServiceTestMock {

    private static final String FIRST_NAME = "firstName";
    private static final String LAST_NAME = "lastName";
    private static final Integer AGE = 30;
    private static final Integer ACCOUNTS_QTY_1 = 1;
    private static final Integer ACCOUNTS_QTY_2 = 2;
    private static final String ACCOUNT_NUMBER = "UA";
    private static final Boolean ACCOUNT_ACTIVE_STATUS = true;
    private static final BigDecimal ACCOUNT_BALANCE_EMPTY = new BigDecimal("00.00");
    private static final BigDecimal ACCOUNT_BALANCE_NOT_EMPTY = new BigDecimal("100.00");

    private static User user1 = new User();
    private static User user2 = new User();
    private static Account account1 = new Account();
    private static Account account2 = new Account();
    private static Account account3 = new Account();

    @Before
    public static void setUp() {
        user1.setFirstName(FIRST_NAME + 1);
        user1.setLastName(LAST_NAME + 1);
        user1.setAge(AGE);
        user1.setAccountsQty(ACCOUNTS_QTY_1);

        account1.setAccountNumber(ACCOUNT_NUMBER + 1);
        account1.setIsActive(ACCOUNT_ACTIVE_STATUS);
        account1.setBalance(ACCOUNT_BALANCE_NOT_EMPTY);
        account1.setUser(user1);

        user2.setFirstName(FIRST_NAME + 2);
        user2.setLastName(LAST_NAME + 2);
        user2.setAge(AGE);
        user2.setAccountsQty(ACCOUNTS_QTY_2);

        account2.setAccountNumber(ACCOUNT_NUMBER + 2);
        account2.setIsActive(ACCOUNT_ACTIVE_STATUS);
        account2.setBalance(ACCOUNT_BALANCE_NOT_EMPTY);
        account2.setUser(user2);

        account3.setAccountNumber(ACCOUNT_NUMBER + 3);
        account3.setIsActive(ACCOUNT_ACTIVE_STATUS);
        account3.setBalance(ACCOUNT_BALANCE_NOT_EMPTY);
        account3.setUser(user2);
    }

//    @Test
//    public void shouldBeCreateUserWhenEmailIsUnique() {
//        // given
//        when(userRepository.existsByEmail(EMAIL)).thenReturn(false);
//
//        // when
//        userService.create(user);
//
//        // then
//        verify(userRepository, times(1)).save(user);
//    }

}
