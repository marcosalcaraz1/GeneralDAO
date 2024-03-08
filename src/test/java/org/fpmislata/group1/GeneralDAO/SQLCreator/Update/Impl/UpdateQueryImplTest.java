package org.fpmislata.group1.GeneralDAO.SQLCreator.Update.Impl;

import org.fpmislata.group1.GeneralDAO.SQLCreator.Update.UpdateCreator;
import org.fpmislata.group1.GeneralDAO.SQLCreator.Update.UpdateQuery;
import org.fpmislata.group1.GeneralDAO.SQLCreator.common.Query;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UpdateQueryImplTest {

    @Nested
    @DisplayName("Constructor Tests")
    class ConstructorTests{

        @Test
        @DisplayName("Can create with empty constructor")
        void canCreateWithEmptyConstructor(){
            new UpdateQueryImpl();
        }

        @Test
        @DisplayName("If empty constructor then Query is 'UPDATE' and void list")
        void ifEmptyConstructorThenQueryIsNull(){
            Query query = new UpdateQueryImpl().getQuery();
            assertEquals("UPDATE",query.getSql());
            assertTrue(query.getValues().isEmpty());
        }
    }


    @Nested
    @DisplayName("Table Tests")
    class TableTests{

        @Test
        @DisplayName("if table is null then signals NullPointer")
        void ifTableIsNullThenSignalsNullPointer(){
            assertThrows(
                    NullPointerException.class,
                    ()->new UpdateQueryImpl().table(null)
            );
        }

        @Test
        @DisplayName("After from table sql contains 'table' and value list is null")
        void afterFromTableSqlContainsFROMTable(){
            UpdateQuery updateQuery = new UpdateQueryImpl();
            updateQuery.table("table");
            Query query = updateQuery.getQuery();
            assertTrue(query.getSql().contains("table"));
            assertTrue(query.getValues().isEmpty());
        }

        @Test
        @DisplayName("returns always itself")
        void returnsAlwaysItself(){
            UpdateQuery updateQuery = new UpdateQueryImpl();
            UpdateCreator secondUpdateQuery = updateQuery.table("table");
            assertSame(updateQuery,secondUpdateQuery);
        }

    }


    @Nested
    @DisplayName("WhereIsEquals Tests")
    class WhereIsEquals{

        @Test
        @DisplayName("returns always itself")
        void returnsAlwaysItself(){
            UpdateQuery updateQuery = new UpdateQueryImpl();
            UpdateCreator secondUpdateQuery = updateQuery.whereIsEquals("field1",1);
            assertSame(updateQuery,secondUpdateQuery);
        }

        @Test
        @DisplayName("if field is null then signals NullPointer")
        void ifFiledIsNullThenSignalsNullPointer(){
            UpdateQuery updateQuery = new UpdateQueryImpl();
            assertThrows(NullPointerException.class,
                    ()-> updateQuery.whereIsEquals(null,1));
        }

        @Test
        @DisplayName("if value is null then signals NullPointer")
        void ifValueIsNullThenSignalsNullPointer(){
            UpdateQuery updateQuery = new UpdateQueryImpl();
            assertThrows(NullPointerException.class,
                    ()-> updateQuery.whereIsEquals("field",null));
        }

        @Test
        @DisplayName("After init and whereIsEquals SQL is 'UPDATE WHERE field=?' and value list is value ")
        void afterInitAndWhereIsEqualsDeleteExpected(){
            UpdateQuery updateQuery = new UpdateQueryImpl();
            updateQuery.whereIsEquals("field",1);
            Query query = updateQuery.getQuery();
            assertEquals("UPDATE WHERE field=?",query.getSql());
            assertArrayEquals(new Object[]{1},query.getValues().toArray());
        }
    }


    @Nested
    @DisplayName("Field Tests")
    class FieldTests{

        @Test
        @DisplayName("if field is null then signals NullPointer")
        void ifFiledIsNullThenSignalsNullPointer(){
            assertThrows(NullPointerException.class,
                    ()->new UpdateQueryImpl().setField(null,1));
        }

        @Test
        @DisplayName("returns always itself")
        void returnsAlwaysItself(){
            UpdateQuery updateQuery = new UpdateQueryImpl();
            UpdateCreator secondUpdateQuery = updateQuery.setField("field1",1);
            assertSame(updateQuery,secondUpdateQuery);
        }

        @Test
        @DisplayName("After init and setField, sql is 'UPDATE SET field=?' and value list contains value")
        void afterInitAndSetFieldSqlIsUpdateSetFieldAndValueListIsValue(){
            UpdateQuery updateQuery = new UpdateQueryImpl();
            updateQuery.setField("field",true);
            Query query = updateQuery.getQuery();
            assertEquals("UPDATE SET field=?",query.getSql());
            assertArrayEquals(new Object[]{true},query.getValues().toArray());
        }

        @Test
        @DisplayName("After init and setField 2 times, sql is 'UPDATE SET field=?, field2=?' and value list contains values")
        void afterInitAndSetField2TimesSqlIsUpdateSetFieldsAndValueListIsValues(){
            UpdateQuery updateQuery = new UpdateQueryImpl();
            updateQuery.setField("field",true).setField("field2",1);
            Query query = updateQuery.getQuery();
            assertEquals("UPDATE SET field=?, field2=?",query.getSql());
            assertArrayEquals(new Object[]{true,1},query.getValues().toArray());
        }


    }


    @Test
    @DisplayName("Can create complex updates")
    void canCreateComplexUpdates(){
        final String sqlExpected = "UPDATE table SET field1=?, field2=? WHERE id=?";
        final Object[] paramsExpected = new Object[]{true,"Hola",1};

        UpdateQuery updateQuery = new UpdateQueryImpl();
        updateQuery.table("table")
                .setField("field1",true)
                .setField("field2","Hola")
                .whereIsEquals("id",1);
        Query query = updateQuery.getQuery();
        assertEquals(sqlExpected,query.getSql());
        assertArrayEquals(paramsExpected,query.getValues().toArray());
    }

}