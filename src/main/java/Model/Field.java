package Model;

import java.util.Arrays;
import java.util.Objects;

public class Field {
    private int[][] field;
    private int player;
    static final int PLAYER_BLACK = -1;
    static final int PLAYER_WHITE = 1;

    public Field() {
        int[][] matrix = new int[8][8];
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix.length; j++) {
                matrix[i][j] = 0;
            }
        }

        matrix[3][4] = -1;
        matrix[4][3] = -1;
        matrix[4][4] = 1;
        matrix[3][3] = 1;

        this.field = matrix;
        this.player = PLAYER_BLACK;
    }

    public int get(int i, int j) {
        return field[i][j];
    }

    public void setCellValue(int i, int j, int value) {
        this.field[i][j] = value;
    }

    public int getPlayerScore(int player) {
        int current = 0;
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (field[i][j] == player) current++;
            }
        }
        return current;
    }

    public void nextTurn(int i, int j) {
        if (isPat()) this.player *= -1;
        this.field = canDoTurn(i, j);
    }

    public boolean updateField(final int i0, final int j0) {
        boolean flagUpdateField = false;
        boolean flagLineOtherChip;
        boolean flagDiagonalOtherChip;
        boolean flagDiagonal;
        boolean flagLine;
        int i;
        int j;

        if (field[i0][j0] != 0) return false;

        {
            flagLineOtherChip = false;
            flagDiagonalOtherChip = false;
            flagDiagonal = true;
            flagLine = true;
            i = i0;
            j = j0;
            while (i < 7) {
                i++;
                if (field[i][j0] == 0) flagLine = false;
                else if (field[i][j0] == player * -1) flagLineOtherChip = true;
                else if (field[i][j0] == player && !flagLineOtherChip) flagLine = false;
                else if (field[i][j0] == player && flagLine && flagLineOtherChip) {
                    this.cellPainting(i0, j0, i, j0);
                    flagUpdateField = true;
                    flagLine = false;
                }
                if (j0 + i - i0 < 8) {
                    if (field[i][j0 + i - i0] == 0) flagDiagonal = false;
                    else if (field[i][j0 + i - i0] == player * -1) flagDiagonalOtherChip = true;
                    else if (field[i][j0 + i - i0] == player && !flagDiagonalOtherChip) flagDiagonal = false;
                    else if (field[i][j0 + i - i0] == player && flagDiagonal && flagDiagonalOtherChip) {
                        this.cellPainting(i0, j0, i, j0 + i - i0);
                        flagUpdateField = true;
                        flagDiagonal = false;
                    }
                    if (!flagDiagonal && !flagLine) break;
                }
            }
        }
        {
            flagLineOtherChip = false;
            flagDiagonalOtherChip = false;
            flagDiagonal = true;
            flagLine = true;
            i = i0;
            while (i > 0) {
                i--;
                if (field[i][j0] == 0) flagLine = false;
                else if (field[i][j0] == player * -1) flagLineOtherChip = true;
                else if (field[i][j0] == player && !flagLineOtherChip) flagLine = false;
                else if (field[i][j0] == player && flagLine && flagLineOtherChip) {
                    this.cellPainting(i, j0, i0, j0);
                    flagUpdateField = true;
                    flagLine = false;
                }
                if (j0 + i - i0 > -1) {
                    if (field[i][j0 + i - i0] == 0) flagDiagonal = false;
                    else if (field[i][j0 + i - i0] == player * -1) flagDiagonalOtherChip = true;
                    else if (field[i][j0 + i - i0] == player && !flagDiagonalOtherChip) flagDiagonal = false;
                    else if (field[i][j0 + i - i0] == player && flagDiagonal && flagDiagonalOtherChip) {
                        this.cellPainting(i, j0 + i - i0, i0, j0);
                        flagUpdateField = true;
                        flagDiagonal = false;
                    }
                    if (!flagDiagonal && !flagLine) break;
                }
            }
        }
        {
            flagLineOtherChip = false;
            flagDiagonalOtherChip = false;
            flagDiagonal = true;
            flagLine = true;
            while (j < 7) {
                j++;
                if (field[i0][j] == 0) flagLine = false;
                else if (field[i0][j] == player * -1) flagLineOtherChip = true;
                else if (field[i0][j] == player && !flagLineOtherChip) flagLine = false;
                else if (field[i0][j] == player && flagLine && flagLineOtherChip) {
                    this.cellPainting(i0, j0, i0, j);
                    flagUpdateField = true;
                    flagLine = false;
                }
                if (i0 + j0 - j > -1) {
                    if (field[i0 + j0 - j][j] == 0) flagDiagonal = false;
                    else if (field[i0 + j0 - j][j] == player * -1) flagDiagonalOtherChip = true;
                    else if (field[i0 + j0 - j][j] == player && !flagDiagonalOtherChip) flagDiagonal = false;
                    else if (field[i0 + j0 - j][j] == player && flagDiagonal && flagDiagonalOtherChip) {
                        this.cellPainting(i0, j0, i0 + j0 - j, j);
                        flagUpdateField = true;
                        flagDiagonal = false;
                    }
                    if (!flagDiagonal && !flagLine) break;
                }
            }
        }
        {
            flagLineOtherChip = false;
            flagDiagonalOtherChip = false;
            flagDiagonal = true;
            flagLine = true;
            j = j0;
            while (j > 0) {
                j--;
                if (field[i0][j] == 0) flagLine = false;
                else if (field[i0][j] == player * -1) flagLineOtherChip = true;
                else if (field[i0][j] == player && !flagLineOtherChip) flagLine = false;
                else if (field[i0][j] == player && flagLine && flagLineOtherChip) {
                    this.cellPainting(i0, j, i0, j0);
                    flagUpdateField = true;
                    flagLine = false;
                }
                if (i0 + j0 - j < 8) {
                    if (field[i0 + j0 - j][j] == 0) flagDiagonal = false;
                    else if (field[i0 + j0 - j][j] == player * -1) flagDiagonalOtherChip = true;
                    else if (field[i0 + j0 - j][j] == player && !flagDiagonalOtherChip) flagDiagonal = false;
                    else if (field[i0 + j0 - j][j] == player && flagDiagonal && flagDiagonalOtherChip) {
                        this.cellPainting(i0 + j0 - j, j, i0, j0);
                        flagUpdateField = true;
                        flagDiagonal = false;
                    }
                    if (!flagDiagonal && !flagLine) break;
                }
            }
        }
        return flagUpdateField;
    }

    public void cellPainting(int i0, int j0, int i, int j) {
        if (i0 + j0 == i + j) {
            if (i0 < i) {
                for (int index = i0 + 1; index < i; index++) {
                    field[index][j + i - index] = player;
                }
            } else {
                for (int index = j0 + 1; index < j; index++) {
                    field[j + i - index][index] = player;
                }
            }
        } else if (i0 - j0 == i - j) {
            if (i0 < i) {
                for (int index = i0 + 1; index < i; index++) {
                    field[index][j0 + index - i0] = player;
                }
            } else {
                for (int index = j0 + 1; index < j; index++) {
                    field[index][index] = player;
                }
            }
        } else if (j0 == j) {
            for (int index = i0 + 1; index < i; index++) {
                field[index][j] = player;
            }
        } else if (i0 == i) {
            for (int index = j0 + 1; index < j; index++) {
                field[i0][index] = player;
            }
        }
    }

    public boolean isFull() {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (field[i][j] == 0) return false;
            }
        }
        return true;
    }

    public int[][] canDoTurn(int i, int j) {
        int[][] oldField = copy(this.field);
        if (updateField(i, j)) {
            setCellValue(i, j, this.player);
            this.player *= -1;
            return this.field;
        } else {
            return oldField;
        }
    }

    private int[][] copy(int[][] field) {
        int[][] newField = new int[8][8];
        for (int i = 0; i < 8; i++) {
            newField[i] = Arrays.copyOf(field[i], 8);
        }
        return newField;
    }

    public boolean isPat() {
        int[][] oldField = copy(this.field);
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (oldField[i][j] == 0) {
                    int[][] matrix = canDoTurn(i, j);
                    for (int index = 0; index < 8; index++) {
                        if (!Arrays.equals(matrix[index], oldField[index])) {
                            this.field = oldField;
                            this.player *= -1;
                            return false;
                        }
                    }
                }
            }
        }
        return true;
    }

    public boolean endGame() {
        boolean flagWhite = false;
        boolean flagBlack = false;
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (this.get(i, j) == 1) flagWhite = true;
                else if (this.get(i, j) == -1) flagBlack = true;
            }
        }
        return isFull() || (flagBlack ^ flagWhite);
    }

    public String identifyWinner() {
        int sum = 0;
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                sum += field[i][j];
            }
        }
        if (sum < 0) return "Победили черные!";
        else if (sum != 0) return "Победили белые!";
        else return "Победила ничья!";
    }

    public int[][] getField() {
        return field;
    }

    public int getPlayer() {
        return player;
    }

    public void setPlayer(int player) {
        this.player = player;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null || obj.getClass() != this.getClass())
            return false;
        else if (this == obj)
            return true;
        else {
            final Field other = (Field) obj;
            if (player != other.player) return false;
            for (int i = 0; i < 8; i++) {
                if (!Arrays.equals(field[i], other.field[i])) return false;
            }
            return true;
        }
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(player);
        result = 31 * result + Arrays.deepHashCode(field);
        return result;
    }
}
