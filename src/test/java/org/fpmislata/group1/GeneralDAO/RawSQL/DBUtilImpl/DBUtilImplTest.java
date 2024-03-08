package org.fpmislata.group1.GeneralDAO.RawSQL.DBUtilImpl;

import org.flywaydb.core.Flyway;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.sql.ResultSet;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class DBUtilImplTest {

    private static ConfigConnection validConnection;
    private static final String URL = "jdbc:h2:file:./target/foobar";
    private static final String USER = "test";
    private static final String PASSWORD = null;

    @BeforeAll
    static void setUpFlyWay(){

        // Create the Flyway instance and point it to the database
        Flyway flyway = Flyway.configure()
                .dataSource(URL,USER,PASSWORD).load();

        // Start the migration
        flyway.migrate();
        validConnection = new ConfigConnection(URL,USER,PASSWORD);
    }

    @Nested
    @DisplayName("Singleton Tests")
    class SingletonTests{
        @Test
        @DisplayName("Can Get Instance")
        void canGetInstance(){
            assertNotNull(DBUtilImpl.getInstance());
        }

        @Test
        @DisplayName("Get Instance always returns same object")
        void getInstanceAlwaysReturnsSameObject(){
            assertSame(DBUtilImpl.getInstance(),DBUtilImpl.getInstance());
        }
    }

    @Nested
    @DisplayName("Set Connection Tests")
    class setConnectionTests{
        @Test
        @DisplayName("if ConfigConnection is null then signals NullPointer")
        void ifConfigConnectionIsNullThenSignalsNullPointer(){
            assertThrows(NullPointerException.class,
                    ()->DBUtilImpl.getInstance().setConnection(null)
            );
        }

        @Test
        @DisplayName("If can not connect to config data then signals RuntimeException")
        void ifCanNotConnectToConfigDataThenSignalsRuntimeException(){
            assertThrows(RuntimeException.class,
                    ()->DBUtilImpl.getInstance().setConnection(new ConfigConnection(null,null,null))
            );
        }

        @Test
        @DisplayName("If can connect then returns same object")
        void ifCanConnectThenReturnsSameObject(){
            assertSame(DBUtilImpl.getInstance(),
                    DBUtilImpl.getInstance().setConnection(validConnection)
            );
        }
    }

    @Nested
    @DisplayName("Method Select")
    class SelectTests{

        @Test
        @DisplayName("if sql is null then signals NullPointerException")
        void ifSqlIsNullThenSignalsNullPointerException(){
            DBUtilImpl.getInstance().setConnection(validConnection);

            assertThrows(NullPointerException.class,
                    ()-> DBUtilImpl.getInstance().select(null,null)
            );
        }

        @Test
        @DisplayName("if select get all persons then returns ResultSet with insertions")
        void ifSelectGetAllPersonsThenReturnsResultSetWithInsertions(){
            ResultSet resultSet = DBUtilImpl.getInstance().setConnection(validConnection).select("SELECT * FROM PERSON;",null);

            try{
                resultSet.next();
                assertEquals(1,resultSet.getInt("ID"));
                assertEquals("Axel",resultSet.getString("NAME"));
            }catch (Exception e){
                throw new RuntimeException(e.getMessage());
            }

        }

        @Test
        @DisplayName("If SELECT PERSON WHERE ID IS 2 like ? FROM PERSON then returns id=2 name=Mr. Foo")
        void ifSelectID2WithInterrogationThenReturnThatPerson(){
            ResultSet resultSet = DBUtilImpl
                    .getInstance()
                    .setConnection(validConnection)
                    .select("SELECT * FROM PERSON WHERE ID = ?", List.of(2));

            try{
                resultSet.next();
                assertEquals(2,resultSet.getInt("ID"));
                assertEquals("Mr. Foo",resultSet.getString("NAME"));
            }catch (Exception e){
                throw new RuntimeException(e.getMessage());
            }
        }


        @Test
        @DisplayName("If not select statement then signals RuntimeException")
        void ifNotSelectStatementThenSignalsRuntimeException(){
            assertThrows(RuntimeException.class,
                    ()->DBUtilImpl.getInstance()
                            .setConnection(validConnection)
                            .select("INSERT INTO PERSON VALUES(10,'Mays')", List.of(2))
            );
        }

    }


    @Nested
    @DisplayName("Method Insert")
    class InsertTests{

        @Test
        @DisplayName("if insert valid person then no signals")
        void ifInsertValidPersonThenReturnsTrue(){
            DBUtilImpl.getInstance().setConnection(validConnection)
                    .insert("INSERT INTO PERSON VALUES (20,'MAYA')",null);

            ResultSet resultSet = DBUtilImpl.getInstance().select("SELECT * FROM PERSON WHERE ID = 20",null);
            try{
                resultSet.next();
                assertEquals(20,resultSet.getInt("ID"));
                assertEquals("MAYA",resultSet.getString("NAME"));
            }catch (Exception e){
                throw new RuntimeException(e.getMessage());
            }

            DBUtilImpl.getInstance().rollback();

        }

    }

    @Nested
    @DisplayName("Method Rollback")
    class RollbackTests{

        @Test
        @DisplayName("After Modify SQL and rollback() return to status before")
        void afterModifySQLAndRollbackReturnToStatusBefore(){
            DBUtilImpl.getInstance().setConnection(validConnection,false)
                    .insert("INSERT INTO PERSON VALUES (50,'MAYA')",null);
            DBUtilImpl.getInstance().rollback();
            ResultSet resultSet = DBUtilImpl.getInstance().select("SELECT * FROM PERSON WHERE ID = ?",List.of(50));
            try{
                assertFalse(resultSet.next());
            }catch (Exception e){
                throw new RuntimeException(e.getMessage());
            }

        }

    }


    @Nested
    @DisplayName("Method Commit")
    class CommitTests{

    }
}