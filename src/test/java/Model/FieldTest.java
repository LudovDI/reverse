package Model;

import static org.junit.jupiter.api.Assertions.*;

class FieldTest {

    private Field field = new Field();

    @org.junit.jupiter.api.Test
    void getPlayerScore() {
        field = new Field();

        field.setCallValue(0, 0, 1);

        assertEquals(3, field.getPlayerScore(1));
        assertEquals(2, field.getPlayerScore(-1));
    }

    @org.junit.jupiter.api.Test
    void nextTurn() {
        field = new Field();
        Field expectedField = new Field();

        expectedField.setCallValue(2, 3, -1);
        expectedField.setCallValue(3, 3, -1);

        field.nextTurn(2, 3);

        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                System.out.print(expectedField.get(i, j));
            }
            System.out.println();
        }
        System.out.println();
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                System.out.print(field.get(i, j));
            }
            System.out.println();
        }

        System.out.println();

        assertEquals(expectedField, field);

        expectedField.setCallValue(2, 2, 1);
        expectedField.setCallValue(3, 3, 1);

        field.nextTurn(2, 2);

        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                System.out.print(expectedField.get(i, j));
            }
            System.out.println();
        }
        System.out.println();
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                System.out.print(field.get(i, j));
            }
            System.out.println();
        }

        System.out.println();

        assertEquals(expectedField, field);

        expectedField.setCallValue(2, 1, -1);
        expectedField.setCallValue(2, 2, -1);

        field.nextTurn(2, 1);

        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                System.out.print(expectedField.get(i, j));
            }
            System.out.println();
        }
        System.out.println();
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                System.out.print(field.get(i, j));
            }
            System.out.println();
        }


        assertEquals(expectedField, field);
    }

    @org.junit.jupiter.api.Test
    void setCallValue() {
        field = new Field();
        field.setCallValue(0, 0, 1);

        assertEquals(1, field.get(0, 0));
    }

    @org.junit.jupiter.api.Test
    void isFull() {
        field = new Field();
        assertFalse(field.isFull());
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                field.setCallValue(i, j, -1);
            }
        }
        assertTrue(field.isFull());
    }

    @org.junit.jupiter.api.Test
    void canDoTurn() {
        field = new Field();

        assertFalse(field.canDoTurn(0, 0));
        assertFalse(field.canDoTurn(3, 4));
        assertFalse(field.canDoTurn(2, 3));
        assertTrue(field.canDoTurn(2, 4));
    }

    @org.junit.jupiter.api.Test
    void isPat() {
        field = new Field();

        assertFalse(field.isPat());

        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (i < 4 && !(i == 3 && j == 4)) field.setCallValue(i, j, 1);
            }
        }
        field.setCallValue(3, 4, -1);
        field.setCallValue(4, 4, 0);

        assertTrue(field.isPat());

        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                field.setCallValue(i, j, 1);
            }
        }

        assertTrue(field.isPat());

        field.setCallValue(3, 4, 0);

        assertTrue(field.isPat());
    }

    @org.junit.jupiter.api.Test
    void identifyWinner() {
        field = new Field();
        assertEquals(field.identifyWinner(), "Победила ничья!");

        field.setCallValue(0, 0, -1);

        assertEquals(field.identifyWinner(), "Победили черные!");

        field.setCallValue(0,1, 1);
        field.setCallValue(0, 2, 1);

        assertEquals(field.identifyWinner(), "Победили белые!");
    }

    @org.junit.jupiter.api.Test
    void endGame() {
        field = new Field();

        field.setCallValue(3, 4, 1);
        field.setCallValue(4, 4, 1);
        field.setCallValue(3, 3, 1);
        field.setCallValue(4, 3, 1);

        assertTrue(field.endGame());
    }

    @org.junit.jupiter.api.Test
    void equals() {
        field = new Field();

        Field emptyField = new Field();

        emptyField.setCallValue(3, 4, 0);
        emptyField.setCallValue(4, 4, 0);
        emptyField.setCallValue(3, 3, 0);
        emptyField.setCallValue(4, 3, 0);

        assertEquals(field, field);
        assertNotEquals(emptyField, field);

        Field secondField = new Field();

        assertEquals(secondField, field);

        secondField.setCallValue(0, 0, -1);

        assertNotEquals(secondField, field);
    }
}