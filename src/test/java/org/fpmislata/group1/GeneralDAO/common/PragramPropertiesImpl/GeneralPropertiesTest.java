package org.fpmislata.group1.GeneralDAO.common.PragramPropertiesImpl;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.fpmislata.group1.GeneralDAO.RawSQL.DBUtilImpl.ConfigConnection;
import org.fpmislata.group1.GeneralDAO.common.ProgramProperties;
import org.fpmislata.group1.GeneralDAO.common.ProgramPropertiesImpl.GeneralProperties;

import static org.junit.jupiter.api.Assertions.*;

class GeneralPropertiesTest {

    @Nested
    @DisplayName("Constructor Tests")
    class ConstructorTests{
        @Test
        @DisplayName("If url is null then signals NullPointer")
        void ifUrlIsNullThenSignalsNullPointer(){
            assertThrows(NullPointerException.class,
                    ()-> new GeneralProperties(null));
        }

        @Test
        @DisplayName("If file not found then signals RuntimeException")
        void ifFileNotFoundThenSignalsRuntimeException(){
            assertThrows(RuntimeException.class,
                    ()-> new GeneralProperties("file.properties"));
        }

        @Test
        @DisplayName("Can init with correct file")
        void canInitWithCorrectFile(){
            new GeneralProperties("src/test/java/org/marcos/common/common/test.properties");
        }
    }


    @Nested
    @DisplayName("GetConfigConnection Tests")
    class GetConfigConnectionTests{

        private static String testFileUrl = "src/test/java/org/marcos/common/common/test.properties";
        private static String voidFileUrl = "src/test/java/org/marcos/common/common/test2.properties";

        @Test
        @DisplayName("if configConnection is present then return object")
        void ifConfigConnectionIsPresentThenReturnObject(){
            ProgramProperties programProperties = new GeneralProperties(testFileUrl);
            ConfigConnection connection = programProperties.getConfigConnection();
            assertEquals("testUrl",connection.getUrl());
            assertEquals("testUser",connection.getUser());
            assertEquals("testPassword",connection.getPassword());
        }

        @Test
        @DisplayName("if configConnection is invalid then return null")
        void ifConfigConnectionIsInvalidThenReturnNull(){
            ProgramProperties programProperties = new GeneralProperties(voidFileUrl);
            assertNull(programProperties.getConfigConnection());
        }

    }
}