import com.revature.project1.models.AppUser;
import com.revature.project1.services.SaranServices;
import com.revature.project1.services.Service;
import com.revature.project1.util.ConnectionFactory;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class ServiceTest {
    private Service sut;

    private SaranServices mockSaranServices;
    private Connection mockConnection;
    private ConnectionFactory mockConnectionFactory;
    private AppUser testUser;

    @Before
    public void setUp() {
        mockSaranServices = mock(SaranServices.class);
        mockConnection = mock(Connection.class);
        sut = new Service(mockSaranServices);
        testUser = new AppUser("un", "password123", "email@mail.com", "first", "last", 18, true);
    }

    @After
    public void tearDown() {
        sut = null;
        mockSaranServices = null;
        testUser = null;
    }

    @Test
    public void test_isUserValidWithValidUser() {
        // Arrange
        // Act
        // Assert
        assertTrue(sut.isUserValid(testUser));
    }

    @Test
    public void test_isUserValidWithInvalidUser() {
        // Arrange
        testUser.setUsername("");
        // Act
        // Assert
        assertFalse(sut.isUserValid(testUser));
    }

    @Test
    public void test_isUserValidWithInvalidNullUser() {
        // Arrange
        testUser.setUsername(null);
        // Act
        // Assert
        assertFalse(sut.isUserValid(testUser));
    }

    @Test
    public void test_isPasswordSecureWithValidPassword() {
        // Arrange
        // Act
        // Assert
        assertTrue(sut.isPasswordSecure(testUser));
    }

    @Test
    public void test_isPasswordSecureWithInvalidPassword() {
        // Arrange
        testUser.setPassword("password");
        // Act
        // Assert
        assertFalse(sut.isPasswordSecure(testUser));
    }

    @Test(expected = NullPointerException.class)
    public void test_isPasswordSecureWithInvalidNullPassword() {
        // Arrange
        testUser.setPassword(null);
        // Act
        // Assert
        assertFalse(sut.isPasswordSecure(testUser));
    }

    @Test
    public void test_registerWithValidCredentials() throws SQLException {
        // Arrange
        when(sut.isUsernameAvailable(testUser)).thenAnswer(new Answer() {
            @Override
            public Object answer(InvocationOnMock invocation) throws Throwable {
                return new ArrayList<AppUser>();
            }
        });
        when(sut.isEmailAvailable(testUser)).thenAnswer(new Answer() {
            @Override
            public Object answer(InvocationOnMock invocation) throws Throwable {
                return new ArrayList<AppUser>();
            }
        });

        // Act
        sut.register(testUser);

        // Assert
        assertTrue(sut.isUserValid(testUser));
        assertTrue(sut.isPasswordSecure(testUser));

//        verify(mockSaranServices, times(2)).selectObject(any(),any());
        verify(mockSaranServices, times(1)).insertObject(any());

//        assertTrue(sut.isUsernameAvailable(thisUser));
//        assertTrue(sut.isEmailAvailable(thisUser));
    }

//    @Test
//    public void test_registerWithValidUserAndTakenUsername() {
//        // Arrange
//        when(sut.isUsernameAvailable(testUser));
//
//        // Act
//        sut.register(testUser);
//
//        // Assert
//        assertTrue(sut.isUserValid(testUser));
//        assertTrue(sut.isPasswordSecure(testUser));
//        assertFalse(sut.isUsernameAvailable(testUser));
//        verify(mockSaranServices, times(2)).selectObject(any(),any());
//        verify(mockSaranServices, times(0)).insertObject(any());
//    }
//
//
//    @Test
//    public void test_registerWithValidUserAndTakenEmail() {
//        // Arrange
//
//        // Act
//        sut.register(testUser);
//
//        // Assert
//        assertTrue(sut.isUserValid(testUser));
//        assertTrue(sut.isPasswordSecure(testUser));
////        assertFalse(sut.isEmailAvailable(testUser));
//        verify(mockSaranServices, times(2)).selectObject(any(),any());
//        verify(mockSaranServices, times(0)).insertObject(any());
//    }

    @Test
    public void test_authenticateWithValidUser() {
        // Arrange
        testUser.setUsername("unamdse");
        testUser.setPassword("myPasswordIsV3rYS3cur3");
        sut.authenticate(testUser.getUsername(), testUser.getPassword());
        // Act
        verify(mockSaranServices, times(1)).selectObject(any(), anyString());
    }
}
