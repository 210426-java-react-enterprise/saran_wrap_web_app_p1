import com.revature.project1.daos.UserDAO;
import com.revature.project1.exception.*;
import com.revature.project1.services.UserService;
import com.revature.project1.models.AppUser;
import com.revature.project1.util.ConnectionFactory;
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
        mockConnection = mock(Connection.class);
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
        when(mockUserDao.isUsernameAvailable(mockConnection,anyString())).thenReturn(true);
        when(mockUserDao.isEmailAvailable(mockConnection,anyString())).thenReturn(true);
        when(mockUserDao.isPasswordSecure(anyString())).thenReturn(true);
        //Act
        sut.register(new AppUser("un", "password123", "email", "useremail", "ln", 18,"active"));
        //Assert
//        verify(mockConnectionFactory, times(1)).getConnection();
        verify(mockUserDao, times(1)).isUsernameAvailable(mockConnection,anyString());
        verify(mockUserDao, times(1)).isEmailAvailable(mockConnection,anyString());
        verify(mockUserDao, times(1)).isPasswordSecure(anyString());
        verify(mockUserDao, times(1)).save(mockConnection,any());
//        verify(mockConnection, times(1)).commit();
    }
    @Test
    public void test_registerWithValidUserAndTakenUsername() {
        // Arrange
        when(mockUserDao.isUsernameAvailable(mockConnection,anyString())).thenReturn(false);

        // Act
        try {
            sut.register(new AppUser("uname", "pword", "email", "fn", "ln", 18,"active"));
        } catch (Exception e) {
            assertTrue(e instanceof ResourcePersistenceException);
        } finally {
            //verify(mockConnectionFactory, times(1)).getConnection();
            verify(mockUserDao, times(0)).isEmailAvailable(mockConnection,anyString());
            verify(mockUserDao, times(0)).save(mockConnection,any());
        }


    }


    @Test
    public void test_registerWithValidUserAndTakenEmail() {
        // Arrange
        when(mockUserDao.isUsernameAvailable(mockConnection,anyString())).thenReturn(true);
        when(mockUserDao.isEmailAvailable(mockConnection,anyString())).thenReturn(false);

        // Act
        try {
            sut.register(new AppUser("un", "pw", "email", "fn", "ln", 18,"active"));
        } catch (Exception e) {
            assertTrue(e instanceof ResourcePersistenceException);
        } finally {
            //verify(mockConnectionFactory, times(1)).getConnection();
            verify(mockUserDao, times(1)).isUsernameAvailable(mockConnection,anyString());
            verify(mockUserDao, times(1)).isEmailAvailable(mockConnection,anyString());
            verify(mockUserDao, times(0)).save(mockConnection,any());
        }


    }
    @Test
    public void test_registerWithValidUserAndEmailAndPassword() {
        // Arrange
        when(mockUserDao.isUsernameAvailable(mockConnection,anyString())).thenReturn(true);
        when(mockUserDao.isEmailAvailable(mockConnection,anyString())).thenReturn(true);
        when(mockUserDao.isPasswordSecure(anyString())).thenReturn(true);
        // Act
        try {
            sut.register(new AppUser("un", "password1", "differentEmail", "fn", "ln", 18,"active"));
        } catch (Exception e) {
            assertTrue(e instanceof ResourcePersistenceException);
        } finally {
            //verify(mockConnectionFactory, times(1)).getConnection();
            verify(mockUserDao, times(1)).isUsernameAvailable(mockConnection,anyString());
            verify(mockUserDao, times(1)).isEmailAvailable(mockConnection,anyString());
            verify(mockUserDao, times(1)).isPasswordSecure(anyString());
            verify(mockUserDao, times(1)).save(mockConnection,any());
        }


    }
    @Test
    public void test_registerWithValidUserAndEmailNotPassword() {
        // Arrange
        when(mockUserDao.isUsernameAvailable(mockConnection,anyString())).thenReturn(true);
        when(mockUserDao.isEmailAvailable(mockConnection,anyString())).thenReturn(true);
        when(mockUserDao.isPasswordSecure(anyString())).thenReturn(false);
        // Act
        try {
            sut.register(new AppUser("un", "pasrd", "differentEmail", "fn", "ln", 18,"active"));
        } catch (Exception e) {
            assertTrue(e instanceof ResourcePersistenceException);
        } finally {
            //verify(mockConnectionFactory, times(1)).getConnection();
            verify(mockUserDao, times(1)).isUsernameAvailable(mockConnection,anyString());
            verify(mockUserDao, times(1)).isEmailAvailable(mockConnection,anyString());
            verify(mockUserDao, times(1)).isPasswordSecure(anyString());
            verify(mockUserDao, times(0)).save(mockConnection,any());
        }


    }
    @Test
    public void test_registerWithValidUserNotEmailOrPassword() {
        // Arrange
        when(mockUserDao.isUsernameAvailable(mockConnection,anyString())).thenReturn(true);
        when(mockUserDao.isEmailAvailable(mockConnection,anyString())).thenReturn(true);
        when(mockUserDao.isPasswordSecure(anyString())).thenReturn(false);
        // Act
        try {
            sut.register(new AppUser("un", "pasrd", "email", "fn", "ln", 18,"active"));
        } catch (Exception e) {
            assertTrue(e instanceof ResourcePersistenceException);
        } finally {
            //verify(mockConnectionFactory, times(1)).getConnection();
            verify(mockUserDao, times(1)).isUsernameAvailable(mockConnection,anyString());
            verify(mockUserDao, times(1)).isEmailAvailable(mockConnection,anyString());
            verify(mockUserDao, times(1)).isPasswordSecure(anyString());
            verify(mockUserDao, times(0)).save(mockConnection,any());
        }


    }
    @Test
    public void test_registerNonValidUserAndEmailAndPassword() {
        // Arrange
        when(mockUserDao.loginValidation(, anyString(),anyString())).thenReturn(null);
        when(mockUserDao.isEmailAvailable(mockConnection,anyString())).thenReturn(true);
        when(mockUserDao.isPasswordSecure(anyString())).thenReturn(false);
        // Act
        try {
            sut.register(new AppUser("uname", "pasrd", "differentEmail", "fn", "ln", 18,"active"));
        } catch (Exception e) {
            assertTrue(e instanceof ResourcePersistenceException);
        } finally {
            //verify(mockConnectionFactory, times(1)).getConnection();
            verify(mockUserDao, times(1)).isUsernameAvailable(mockConnection,anyString());
            verify(mockUserDao, times(1)).isEmailAvailable(mockConnection,anyString());
            verify(mockUserDao, times(1)).isPasswordSecure(anyString());
            verify(mockUserDao, times(0)).save(mockConnection,any());
        }


    }
    @Test
    public void test_loginValidation(){
        // Arrange
        when(mockUserDao.loginValidation(mockConnection, anyString(),anyString())).thenReturn(null);
        // Act
        mockUserDao.loginValidation(mockConnection, "unamdse", "myPasswordIsV3rYS3cur3");

        //verify(mockConnectionFactory, times(1)).getConnection();
        verify(mockUserDao, times(1)).loginValidation(mockConnection, anyString(),anyString());
    }


    @Test(expected = InvalidRequestException.class)
    public void test_registerWithInvalidUser() {
        // Arrange
        AppUser invalidUser = new AppUser("", "", "", "", "", 30,"");

        // Act
        sut.register(invalidUser);

        // Assert
        verify(mockConnectionFactory, times(0)).getConnection();
        verify(mockUserDao, times(0)).isUsernameAvailable(mockConnection,anyString());
        verify(mockUserDao, times(0)).isEmailAvailable(mockConnection,anyString());
        verify(mockUserDao, times(0)).save(mockConnection,any());


    }

}
