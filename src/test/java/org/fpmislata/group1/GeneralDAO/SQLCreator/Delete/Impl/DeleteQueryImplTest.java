package org.fpmislata.group1.GeneralDAO.SQLCreator.Delete.Impl;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.fpmislata.group1.GeneralDAO.SQLCreator.Delete.DeleteCreator;
import org.fpmislata.group1.GeneralDAO.SQLCreator.Delete.DeleteQuery;
import org.fpmislata.group1.GeneralDAO.SQLCreator.common.Query;

import static org.junit.jupiter.api.Assertions.*;

class DeleteQueryImplTest {

    @Nested
    @DisplayName("Constructor Tests")
    class ConstructorTests{

        @Test
        @DisplayName("Can create with empty constructor")
        void canCreateWithEmptyConstructor(){
            new DeleteQueryImpl();
        }

        @Test
        @DisplayName("If empty constructor then Query is 'DELETE' and void list")
        void ifEmptyConstructorThenQueryIsNull(){
            Query query = new DeleteQueryImpl().getQuery();
            assertEquals("DELETE",query.getSql());
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
                    ()->new DeleteQueryImpl().from(null)
            );
        }

        @Test
        @DisplayName("After from table sql contains 'DELETE FROM table' and value list is null")
        void afterFromTableSqlContainsFROMTable(){
            DeleteQuery insertQuery = new DeleteQueryImpl();
            insertQuery.from("table");
            Query query = insertQuery.getQuery();
            assertTrue(query.getSql().contains("DELETE FROM table"));
            assertTrue(query.getValues().isEmpty());
        }

        @Test
        @DisplayName("returns always itself")
        void returnsAlwaysItself(){
            DeleteQuery insertQuery = new DeleteQueryImpl();
            DeleteCreator secondDeleteQuery = insertQuery.from("table");
            assertSame(insertQuery,secondDeleteQuery);
        }

    }

    @Nested
    @DisplayName("WhereIsEquals Tests")
    class WhereIsEquals{

        @Test
        @DisplayName("returns always itself")
        void returnsAlwaysItself(){
            DeleteQuery insertQuery = new DeleteQueryImpl();
            DeleteCreator secondDeleteQuery = insertQuery.whereIsEquals("field1",1);
            assertSame(insertQuery,secondDeleteQuery);
        }

        @Test
        @DisplayName("if field is null then signals NullPointer")
        void ifFiledIsNullThenSignalsNullPointer(){
            DeleteQuery insertQuery = new DeleteQueryImpl();
            assertThrows(NullPointerException.class,
                    ()-> insertQuery.whereIsEquals(null,1));
        }

        @Test
        @DisplayName("if value is null then signals NullPointer")
        void ifValueIsNullThenSignalsNullPointer(){
            DeleteQuery deleteQuery = new DeleteQueryImpl();
            assertThrows(NullPointerException.class,
                    ()-> deleteQuery.whereIsEquals("field",null));
        }

        @Test
        @DisplayName("After init and whereIsEquals SQL is 'DELETE WHERE field=?' and value list is value ")
        void afterInitAndWhereIsEqualsDeleteExpected(){
            DeleteQuery deleteQuery = new DeleteQueryImpl();
            deleteQuery.whereIsEquals("field",1);
            Query query = deleteQuery.getQuery();
            assertEquals("DELETE WHERE field=?",query.getSql());
            assertArrayEquals(new Object[]{1},query.getValues().toArray());
        }
    }

    @Test
    @DisplayName("If construct is complex query is expected")
    void ifConstructIsComplexQueryIsExpected(){
        final String sqlExpected = "DELETE FROM table WHERE id=?";
        final Object[] values = new Object[]{14};
        DeleteQuery deleteQuery = new DeleteQueryImpl();
        deleteQuery.from("table").whereIsEquals("id",14);
        Query query = deleteQuery.getQuery();
        assertEquals(sqlExpected,query.getSql());
        assertArrayEquals(values,query.getValues().toArray());
    }
}