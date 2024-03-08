package org.fpmislata.group1.GeneralDAO.SQLCreator.Insert.Impl;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.fpmislata.group1.GeneralDAO.SQLCreator.Insert.InsertCreator;
import org.fpmislata.group1.GeneralDAO.SQLCreator.Insert.InsertQuery;
import org.fpmislata.group1.GeneralDAO.SQLCreator.common.Query;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class InsertQueryImplTest {

    @Nested
    @DisplayName("Constructor Tests")
    class ConstructorTests{

        @Test
        @DisplayName("Can create with empty constructor")
        void canCreateWithEmptyConstructor(){
            new InsertQueryImpl();
        }

        @Test
        @DisplayName("If empty constructor then Query is 'INSERT' and void list")
        void ifEmptyConstructorThenQueryIsNull(){
            Query query = new InsertQueryImpl().getQuery();
            assertEquals("INSERT",query.getSql());
            assertTrue(query.getValues().isEmpty());
        }
    }


    @Nested
    @DisplayName("Into Tests")
    class IntoTests{

        @Test
        @DisplayName("if table is null then signals NullPointer")
        void ifTableIsNullThenSignalsNullPointer(){
            assertThrows(
                    NullPointerException.class,
                    ()->new InsertQueryImpl().into(null)
            );
        }

        @Test
        @DisplayName("After into table sql contains 'INSERT INTO table' and value list is null")
        void afterFromTableSqlContainsFROMTable(){
            InsertQueryImpl insertQuery = new InsertQueryImpl();
            insertQuery.into("table");
            Query query = insertQuery.getQuery();
            assertTrue(query.getSql().contains("INSERT INTO table"));
            assertTrue(query.getValues().isEmpty());
        }

        @Test
        @DisplayName("returns always itself")
        void returnsAlwaysItself(){
            InsertQuery insertQuery = new InsertQueryImpl();
            InsertCreator secondInsertQuery = insertQuery.into("table");
            assertSame(insertQuery,secondInsertQuery);
        }

    }


    @Nested
    @DisplayName("Value Tests")
    class ValueTests{

        @Test
        @DisplayName("if valueList is null then signals NullPointer")
        void ifValueListIsNullThenSignalsNullPointer(){
            assertThrows(NullPointerException.class,
                    ()->new InsertQueryImpl().value(null));
        }

        @Test
        @DisplayName("returns always itself")
        void returnsAlwaysItself(){
            InsertQuery insertQuery = new InsertQueryImpl();
            InsertCreator secondInsertQuery = insertQuery.value(List.of(1,2,3));
            assertSame(insertQuery,secondInsertQuery);
        }


        @Test
        @DisplayName("After init and value 1,2,3 sql is 'INSERT VALUE (?,?,?) and values are 1,2,3'")
        void afterInitAndValue123SqlIsFormatInsert(){
            InsertQuery insertQuery = new InsertQueryImpl();
            List<Object> objectList = List.of(1,2,3);

            insertQuery.value(objectList);
            Query query = insertQuery.getQuery();

            assertEquals("INSERT VALUE (?,?,?)",query.getSql());
            assertArrayEquals(objectList.toArray(),query.getValues().toArray());
        }

    }


    @Nested
    @DisplayName("Fields Tests")
    class FieldsTests{
        @Test
        @DisplayName("if some field is null then signals NullPointerException")
        void ifSomeFieldIsNullThenSignalsNullPointer(){
            InsertQuery insertQuery= new InsertQueryImpl();
            assertThrows(NullPointerException.class,
                    ()->insertQuery.fields("",null));
        }

        @Test
        @DisplayName("if fields is null then signals NullPointerException")
        void ifFieldsIsNullThenSignalsNullPointer(){
            InsertQuery insertQuery= new InsertQueryImpl();
            String[] fields = null;
            assertThrows(NullPointerException.class,
                    ()->insertQuery.fields(fields));
        }

        @Test
        @DisplayName("returns always the same object")
        void returnsAlwaysTheSameObject(){
            InsertQuery insertQuery = new InsertQueryImpl();
            InsertCreator secondSelectQuery = insertQuery.fields("Hello");
            assertSame(secondSelectQuery,insertQuery);
        }

        @Test
        @DisplayName("After init and fields, query is 'INSERT (field...)' and values is empty")
        void afterInitAndFieldsQueryIsSelectFiledList(){
            InsertQuery insertQuery = new InsertQueryImpl();
            insertQuery.fields("hello","bye");
            Query query = insertQuery.getQuery();
            assertEquals("INSERT (hello,bye)",query.getSql());
            assertTrue(query.getValues().isEmpty());
        }
    }


    @Test
    @DisplayName("Can construct a complex insert 'INSERT INTO table (field1,field2) VALUE (?,?)' and valueList (1,2) ")
    void canConstructAComplexInsert(){
        InsertQuery insertQuery = new InsertQueryImpl();
        insertQuery.into("table")
                .fields("field1","field2")
                .value(List.of(1,2));
        Query query = insertQuery.getQuery();
        assertEquals("INSERT INTO table (field1,field2) VALUE (?,?)",query.getSql());
        assertArrayEquals(List.of(1,2).toArray(),query.getValues().toArray());

    }

}