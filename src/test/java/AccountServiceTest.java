import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

import dao.*;
import dto.AccountDto;
import entity.*;
import exception.DaoException;
import exception.MapperException;
import exception.ServiceException;
import mapper.AccountMapper;
import mapper.Mapper;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
class AccountServiceTest {

    @Mock
    AccountDao accountDao;

    List<Account> accountsTest;

    int accountsCount = 20;

    public void initEntities(int size) {
        accountsTest = new ArrayList<>();
        for (int i = 1; i <= size; i++) {
            Account testAccount = new Account();
            testAccount.setId(i);
            testAccount.setName("Account-" + i);
            testAccount.setBookAmountMax(i*10);
            testAccount.setBookAmountCurrent(i*5);
            testAccount.setLogin("Login-" + i);
            Role role = new Role("Role-" + i);
            testAccount.setRole(role);
            accountsTest.add(testAccount);
        }
    }

    @Before
    public void initialize() throws DaoException {
        initEntities(accountsCount);
        accountDao = (AccountDao) Dao.of(Account.class);
        when(accountDao.retrieveAll()).thenReturn(accountsTest);
    }


    @Test
    public void getAllMapperTest() throws DaoException, MapperException {
        List<Account> entityAccounts = accountDao.retrieveAll();
        List<AccountDto> dtoAccounts = new ArrayList<>();
        AccountMapper accountMapper = (AccountMapper) Mapper.of(Account.class);
        for (Account e : entityAccounts) {
            dtoAccounts.add(accountMapper.toDto(e));
        }

        assertNotNull(dtoAccounts);
        assertEquals(dtoAccounts.size(), entityAccounts.size());

        for (int i = 0; i < entityAccounts.size(); i++) {
            Account entityAccount = entityAccounts.get(i);
            AccountDto dtoAccount = dtoAccounts.get(i);
            assertEquals(entityAccount.getId(), dtoAccount.getId());
            assertEquals(entityAccount.getName(), dtoAccount.getName());
            assertEquals(entityAccount.getBookAmountCurrent(), dtoAccount.getAmountCurrent());
            assertEquals(entityAccount.getBookAmountMax(), dtoAccount.getAmountMax());
            assertEquals(entityAccount.getLogin(), dtoAccount.getLogin());
            assertEquals(entityAccount.getRole().getName(), dtoAccount.getRole());
        }
    }
}
