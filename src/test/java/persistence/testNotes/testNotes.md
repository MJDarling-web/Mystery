Test pattern to write in:
Arrange (arrang our objects and test fixtures)
Act - act on an object to perform a test
Assert - assert the result of the action is as expected 

Example:
public class InformationDatabaseTest {

    private InformationDatabase db;

    private InformationDatabase initializeDatabase(){
        InformationDatabase db = new InformationDatabase();
        db.connect();
        return db;
    }

    @Test
    public void insertDataUnitTest(){ 
        // arrange our objects and test fixtures
        db = initializeDatabase(); // initialize db object to test
        Road road = new Road("Germany"); // create a valid Road object to insert into db
        // act on an object to perform a test
        Boolean result = db.insertRoadData(road);

        // assert the result of the action is as expected
        Assert.assertTrue(result);
    }
}