import com.revature.project0.daos.UserDAO;
import com.revature.project0.exception.*;
import com.revature.project0.services.UserService;
import com.revature.project0.models.AppUser;
import com.revature.project0.util.ConnectionFactory;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.MockedStatic;

import java.sql.Connection;
import java.sql.SQLException;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class UserServiceTest {
    private UserService sut;
    private UserDAO mockUserDao;
    private Connection mockConnection;
    private ConnectionFactory mockConnectionFactory;
    private MockedStatic<ConnectionFactory> staticMockConnectionFactory;


    @Before
    public void setUp() {
        mockUserDao = mock(UserDAO.class);
//        mockConnection = mock(Connection.class);
//        mockConnectionFactory = mock(ConnectionFactory.class);
//        when(ConnectionFactory.getInstance()).thenReturn(mockConnectionFactory);
//        when(mockConnectionFactory.getConnection()).thenReturn(mockConnection);

        sut = new UserService(mockUserDao);
    }

    @After
    public void tearDown() {
        sut = null;
        mockUserDao = null;
//        staticMockConnectionFactory.close();
//        staticMockConnectionFactory = null;
//        mockConnectionFactory = null;
//        mockConnection = null;
    }

    @Test
    public void resgisterWithValidCredentials() throws SQLException {
        //Arrange
        when(mockUserDao.isUsernameAvailible(anyString())).thenReturn(true);
        when(mockUserDao.isEmailAvailible(anyString())).thenReturn(true);
        when(mockUserDao.isPasswordSecure(anyString())).thenReturn(true);
        //Act
        sut.register(new AppUser("un", "password123", "email", "useremail", "ln", 18));
        //Assert
//        verify(mockConnectionFactory, times(1)).getConnection();
        verify(mockUserDao, times(1)).isUsernameAvailible(anyString());
        verify(mockUserDao, times(1)).isEmailAvailible(anyString());
        verify(mockUserDao, times(1)).isPasswordSecure(anyString());
        verify(mockUserDao, times(1)).save(any());
//        verify(mockConnection, times(1)).commit();
    }

    @Test
    public void test_registerWithValidUserAndTakenUsername() {
        // Arrange
        when(mockUserDao.isUsernameAvailible(anyString())).thenReturn(false);

        // Act
        try {
            sut.register(new AppUser("uname", "pword", "email", "fn", "ln", 18));
        } catch (Exception e) {
            assertTrue(e instanceof ResourcePersistenceException);
        } finally {
            //verify(mockConnectionFactory, times(1)).getConnection();
            verify(mockUserDao, times(0)).isEmailAvailible(anyString());
            verify(mockUserDao, times(0)).save(any());
        }


    }


    @Test
    public void test_registerWithValidUserAndTakenEmail() {
        // Arrange
        when(mockUserDao.isUsernameAvailible(anyString())).thenReturn(true);
        when(mockUserDao.isEmailAvailible(anyString())).thenReturn(false);

        // Act
        try {
            sut.register(new AppUser("un", "pw", "email", "fn", "ln", 18));
        } catch (Exception e) {
            assertTrue(e instanceof ResourcePersistenceException);
        } finally {
            //verify(mockConnectionFactory, times(1)).getConnection();
            verify(mockUserDao, times(1)).isUsernameAvailible(anyString());
            verify(mockUserDao, times(1)).isEmailAvailible(anyString());
            verify(mockUserDao, times(0)).save(any());
        }


    }
    @Test
    public void test_registerWithValidUserAndEmailAndPassword() {
        // Arrange
        when(mockUserDao.isUsernameAvailible(anyString())).thenReturn(true);
        when(mockUserDao.isEmailAvailible(anyString())).thenReturn(true);
        when(mockUserDao.isPasswordSecure(anyString())).thenReturn(true);
        // Act
        try {
            sut.register(new AppUser("un", "password1", "differentEmail", "fn", "ln", 18));
        } catch (Exception e) {
            assertTrue(e instanceof ResourcePersistenceException);
        } finally {
            //verify(mockConnectionFactory, times(1)).getConnection();
            verify(mockUserDao, times(1)).isUsernameAvailible(anyString());
            verify(mockUserDao, times(1)).isEmailAvailible(anyString());
            verify(mockUserDao, times(1)).isPasswordSecure(anyString());
            verify(mockUserDao, times(1)).save(any());
        }


    }
    @Test
    public void test_registerWithValidUserAndEmailNotPassword() {
        // Arrange
        when(mockUserDao.isUsernameAvailible(anyString())).thenReturn(true);
        when(mockUserDao.isEmailAvailible(anyString())).thenReturn(true);
        when(mockUserDao.isPasswordSecure(anyString())).thenReturn(false);
        // Act
        try {
            sut.register(new AppUser("un", "pasrd", "differentEmail", "fn", "ln", 18));
        } catch (Exception e) {
            assertTrue(e instanceof ResourcePersistenceException);
        } finally {
            //verify(mockConnectionFactory, times(1)).getConnection();
            verify(mockUserDao, times(1)).isUsernameAvailible(anyString());
            verify(mockUserDao, times(1)).isEmailAvailible(anyString());
            verify(mockUserDao, times(1)).isPasswordSecure(anyString());
            verify(mockUserDao, times(0)).save(any());
        }


    }
    @Test
    public void test_registerWithValidUserNotEmailOrPassword() {
        // Arrange
        when(mockUserDao.isUsernameAvailible(anyString())).thenReturn(true);
        when(mockUserDao.isEmailAvailible(anyString())).thenReturn(true);
        when(mockUserDao.isPasswordSecure(anyString())).thenReturn(false);
        // Act
        try {
            sut.register(new AppUser("un", "pasrd", "email", "fn", "ln", 18));
        } catch (Exception e) {
            assertTrue(e instanceof ResourcePersistenceException);
        } finally {
            //verify(mockConnectionFactory, times(1)).getConnection();
            verify(mockUserDao, times(1)).isUsernameAvailible(anyString());
            verify(mockUserDao, times(1)).isEmailAvailible(anyString());
            verify(mockUserDao, times(1)).isPasswordSecure(anyString());
            verify(mockUserDao, times(0)).save(any());
        }


    }
    @Test
    public void test_registerNonValidUserAndEmailAndPassword() {
        // Arrange
        when(mockUserDao.loginValidation(anyString(),anyString())).thenReturn(null);
        when(mockUserDao.isEmailAvailible(anyString())).thenReturn(true);
        when(mockUserDao.isPasswordSecure(anyString())).thenReturn(false);
        // Act
        try {
            sut.register(new AppUser("uname", "pasrd", "differentEmail", "fn", "ln", 18));
        } catch (Exception e) {
            assertTrue(e instanceof ResourcePersistenceException);
        } finally {
            //verify(mockConnectionFactory, times(1)).getConnection();
            verify(mockUserDao, times(1)).isUsernameAvailible(anyString());
            verify(mockUserDao, times(1)).isEmailAvailible(anyString());
            verify(mockUserDao, times(1)).isPasswordSecure(anyString());
            verify(mockUserDao, times(0)).save(any());
        }


    }
    @Test
    public void test_loginValidation(){
        // Arrange
        when(mockUserDao.loginValidation(anyString(),anyString())).thenReturn(null);
        // Act
        mockUserDao.loginValidation("unamdse", "myPasswordIsV3rYS3cur3");

        //verify(mockConnectionFactory, times(1)).getConnection();
        verify(mockUserDao, times(1)).loginValidation(anyString(),anyString());
    }


    @Test(expected = InvalidRequestException.class)
    public void test_registerWithInvalidUser() {
        // Arrange
        AppUser invalidUser = new AppUser("", "", "", "", "", 30);

        // Act
        sut.register(invalidUser);

        // Assert
        verify(mockConnectionFactory, times(0)).getConnection();
        verify(mockUserDao, times(0)).isUsernameAvailible(anyString());
        verify(mockUserDao, times(0)).isEmailAvailible(anyString());
        verify(mockUserDao, times(0)).save(any());


    }

}
