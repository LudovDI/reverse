package Model;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

class FieldTest {

    private Field field = new Field();

    @Test
    void getPlayerScore() {
        field = new Field();

        field.setCellValue(0, 0, 1);

        assertEquals(3, field.getPlayerScore(1));
        assertEquals(2, field.getPlayerScore(-1));
    }

    @Test
    void nextTurn() {
        field = new Field();
        Field expectedField = new Field();

        expectedField.setCellValue(2, 3, -1);
        expectedField.setCellValue(3, 3, -1);

        field.nextTurn(2, 3);

        assertArrayEquals(expectedField.getField(), field.getField());

        expectedField.setCellValue(2, 2, 1);
        expectedField.setCellValue(3, 3, 1);

        field.nextTurn(2, 2);

        assertArrayEquals(expectedField.getField(), field.getField());

        expectedField.setCellValue(2, 1, -1);
        expectedField.setCellValue(2, 2, -1);

        field.nextTurn(2, 1);

        assertArrayEquals(expectedField.getField(), field.getField());
    }

    @Test
    void setCallValue() {
        field = new Field();
        field.setCellValue(0, 0, 1);

        assertEquals(1, field.get(0, 0));
    }

    @Test
    void isFull() {
        field = new Field();
        assertFalse(field.isFull());
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                field.setCellValue(i, j, -1);
            }
        }
        assertTrue(field.isFull());
    }

    @Test
    void updateField() {
        field = new Field();
        assertTrue(field.updateField(2, 3));
    }

    @Test
    void cellPainting() {
        field = new Field();

        field.cellPainting(2, 3, 4, 3);

        assertEquals(-1, field.get(3, 3));
    }

    @Test
    void canDoTurn() {
        field = new Field();
        Field expectedField = new Field();

        assertArrayEquals(expectedField.getField(), field.canDoTurn(0, 0));

        expectedField.setCellValue(2, 3, -1);
        expectedField.setCellValue(3, 3, -1);

        assertArrayEquals(expectedField.getField(), field.canDoTurn(2, 3));

        expectedField.setCellValue(2, 2, 1);
        expectedField.setCellValue(3, 3, 1);

        assertArrayEquals(expectedField.getField(), field.canDoTurn(2, 2));
    }

    @Test
    void isPat() {
        field = new Field();

        assertFalse(field.isPat());

        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (i < 4 && !(i == 3 && j == 4)) field.setCellValue(i, j, 1);
            }
        }

        field.setCellValue(3, 4, -1);

        field.setCellValue(4, 4, 0);

        assertTrue(field.isPat());

        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                field.setCellValue(i, j, 1);
            }
        }

        assertTrue(field.isPat());

        field.setCellValue(3, 4, 0);

        assertTrue(field.isPat());
    }

    @Test
    void identifyWinner() {

        field = new Field();

        assertEquals(field.identifyWinner(), "Победила ничья!");

        field.setCellValue(0, 0, -1);

        assertEquals(field.identifyWinner(), "Победили черные!");

        field.setCellValue(0,1, 1);
        field.setCellValue(0, 2, 1);

        assertEquals(field.identifyWinner(), "Победили белые!");
    }

    @Test
    void endGame() {
        field = new Field();

        field.setCellValue(3, 4, 1);
        field.setCellValue(4, 4, 1);
        field.setCellValue(3, 3, 1);
        field.setCellValue(4, 3, 1);

        assertTrue(field.endGame());

        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                field.setCellValue(i, j, 1);
            }
        }

        assertTrue(field.endGame());
    }

    @Test
    void equals() {
        field = new Field();

        Field emptyField = new Field();

        emptyField.setCellValue(3, 4, 0);
        emptyField.setCellValue(4, 4, 0);
        emptyField.setCellValue(3, 3, 0);
        emptyField.setCellValue(4, 3, 0);

        assertEquals(field, field);
        assertNotEquals(emptyField, field);

        Field secondField = new Field();

        assertEquals(secondField, field);

        secondField.setCellValue(0, 0, -1);

        assertNotEquals(secondField, field);
    }

    @Test
    void testHashCode() {
        Field field1 = new Field();
        Field field2 = new Field();
        Field field3 = new Field();
        Field field4 = new Field();

        field3.setCellValue(2,3, -1);
        field3.setCellValue(3, 3, -1);
        field3.setPlayer(1);

        field4.setCellValue(5,4, -1);
        field4.setCellValue(4, 4, -1);
        field4.setPlayer(1);

        assertEquals(field1.hashCode(), field1.hashCode());
        assertEquals(field1.hashCode(), field2.hashCode());
        assertNotEquals(field1.hashCode(), field3.hashCode());
        assertNotEquals(field2.hashCode(), field3.hashCode());
        assertNotEquals(field1.hashCode(), field4.hashCode());
        assertNotEquals(field2.hashCode(), field4.hashCode());
        assertNotEquals(field3.hashCode(), field4.hashCode());
    }
}