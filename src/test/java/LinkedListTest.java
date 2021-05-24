import com.revature.project0.util.structures.LinkedList;
import org.junit.Test;
import org.junit.Before;
import org.junit.After;
import org.junit.Assert;

public class LinkedListTest {
    //create out (s)ystem (u)nder (t)est
    private LinkedList<String> sut;

    //runs before each test
    @Before
    public void setUpTest(){
        sut = new LinkedList<>();
    }
    //runs after each test
    @After
    public void tearDownTest(){
        sut = null;
    }

    //testing adding fuctionality
    @Test
    public void testAddNonNullVal(){
        //Arrange test
        int targetSize = 1;
        //Act-does the test
        sut.add("data");
        int currentSize = sut.size();
        //Assert-compare results
        Assert.assertEquals(targetSize,currentSize);
    }
    //looks for specific exception class to be called
    @Test(expected = Exception.class)
    public void testAddNullVal(){
        //Arrange--nothing to arrange
        //Act
        sut.add(null);
        //Assert--blank due to expected exception
    }
    @Test
    public void testPollWithEmptyLL(){
        String aResults = sut.poll();
        //Assert
        Assert.assertNull(aResults);
    }
    @Test
    public void testPollWithNonEmptyLL(){
        //arrange
        sut.add("data1");
        sut.add("data2");
        String eResult = "data1";
        int eSize = 1;
        //act
        String aResult = sut.poll();
        //assert
        int aSize = sut.size();
        Assert.assertEquals(eResult,aResult);
        Assert.assertEquals(eSize,aSize);

    }
    @Test
    public void testWithNonNullList(){
        sut.add("data1");
        Boolean eResult = true;
        Boolean aResult = sut.contains("data1");
        Assert.assertEquals(eResult,aResult);
    }
    @Test(expected = Exception.class)
    public void testPeekWithEmptyList(){
        //arrange-blank
        String aResult = sut.peek();
        //Assert
        Assert.assertNull(aResult);
    }
    @Test
    public void testPeekWithNonEmptyList(){
        //arrange
        sut.add("data");
        //arrange-blank
        String eResult = "data";
        String aResult = sut.peek();
        //Assert
        Assert.assertEquals(eResult,aResult);
    }
    @Test
    public void testIsEmpty(){
        sut.add("data1");
        boolean eResults = false;
        boolean aResult = sut.isEmpty();
        Assert.assertEquals(eResults,aResult);
    }
    @Test
    public void testContainsWithEmptyList(){
        //arrange-blank
        Boolean eResult = false;
        Boolean aResult = sut.contains("data");
        //Assert
        Assert.assertEquals(eResult,aResult);
    }
//    @Test
//    public void testPopWithNull(){
//        //arrange-blank
//        //act
//        String aResult = sut.pop();
//        //Assert
//        Assert.assertNull(aResult);
//    }


}
