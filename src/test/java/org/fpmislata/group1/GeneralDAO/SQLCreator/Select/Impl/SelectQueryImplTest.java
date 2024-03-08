package org.fpmislata.group1.GeneralDAO.SQLCreator.Select.Impl;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.fpmislata.group1.GeneralDAO.SQLCreator.common.Query;
import org.fpmislata.group1.GeneralDAO.SQLCreator.Select.SelectCreator;
import org.fpmislata.group1.GeneralDAO.SQLCreator.Select.SelectQuery;

import static org.junit.jupiter.api.Assertions.*;

class SelectQueryImplTest {
    @Nested
    @DisplayName("Constructor Tests")
    class ConstructorTests{

        @Test
        @DisplayName("Can create with empty constructor")
        void canCreateWithEmptyConstructor(){
            new SelectQueryImpl();
        }

        @Test
        @DisplayName("If empty constructor then Query is 'SELECT' and void list")
        void ifEmptyConstructorThenQueryIsNull(){
            Query query = new SelectQueryImpl().getQuery();
            assertEquals("SELECT",query.getSql());
            assertTrue(query.getValues().isEmpty());
        }
    }

    @Nested
    @DisplayName("Fields Tests")
    class FieldsTests{
        @Test
        @DisplayName("if some field is null then signals NullPointerException")
        void ifSomeFieldIsNullThenSignalsNullPointer(){
            SelectQuery selectQuery= new SelectQueryImpl();
            assertThrows(NullPointerException.class,
                    ()->selectQuery.fields("",null));
        }

        @Test
        @DisplayName("if fields is null then signals NullPointerException")
        void ifFieldsIsNullThenSignalsNullPointer(){
            SelectQuery selectQuery= new SelectQueryImpl();
            String[] fields = null;
            assertThrows(NullPointerException.class,
                    ()->selectQuery.fields(fields));
        }

        @Test
        @DisplayName("returns always the same object")
        void returnsAlwaysTheSameObject(){
            SelectQuery selectQuery = new SelectQueryImpl();
            SelectCreator secondSelectQuery = selectQuery.fields("Hello");
            assertSame(secondSelectQuery,selectQuery);
        }

        @Test
        @DisplayName("After init and fields, query is 'SELECT field...' and values is empty")
        void afterInitAndFieldsQueryIsSelectFiledList(){
            SelectQuery selectQuery = new SelectQueryImpl();
            selectQuery.fields("hello","bye");
            Query query = selectQuery.getQuery();
            assertEquals("SELECT hello,bye",query.getSql());
            assertTrue(query.getValues().isEmpty());
        }
    }

    @Nested
    @DisplayName("From Tests")
    class FromTests{

        @Test
        @DisplayName("if table is null then signals NullPointer")
        void ifTableIsNullThenSignalsNullPointer(){
            assertThrows(
                    NullPointerException.class,
                    ()->new SelectQueryImpl().from(null)
            );
        }

        @Test
        @DisplayName("After from table sql contains 'FROM table' and value list is null")
        void afterFromTableSqlContainsFROMTable(){
            SelectQuery selectQuery = new SelectQueryImpl();
            selectQuery.from("table");
            Query query = selectQuery.getQuery();
            assertTrue(query.getSql().contains("FROM table"));
            assertTrue(query.getValues().isEmpty());
        }

        @Test
        @DisplayName("returns always itself")
        void returnsAlwaysItself(){
            SelectQuery selectQuery = new SelectQueryImpl();
            SelectCreator secondSelectQuery = selectQuery.from("table");
            assertSame(selectQuery,secondSelectQuery);
        }

    }

    @Test
    @DisplayName("After fields field1,field2 and table table SQL is 'SELECT field1,field2 FROM table'")
    void afterFieldsField1Field2AndTableTableSQLIsSelectFieldsFromTable(){
        SelectQuery selectQuery = new SelectQueryImpl();
        selectQuery.fields("field1","field2").from("table");
        Query query = selectQuery.getQuery();
        assertEquals("SELECT field1,field2 FROM table",query.getSql());
        assertTrue(query.getValues().isEmpty());
    }

}