package Model;

public class Field {
    private final int[][] field;
    private int player = -1;

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
    }

    public int get(int i, int j) {
        return field[i][j];
    }

    public int getPlayerScore(int player) {
        int black = 0;
        int white = 0;
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (field[i][j] == 1) {
                    white++;
                } else {
                    if (field[i][j] == -1) {
                        black++;
                    }
                }
            }
        }
        if (player == -1) return black;
        else return white;
    }

    public void nextTurn(int i, int j) {
        if (isPat()) this.player *= -1;
        if (canDoTurn(i, j)) {
            this.setCallValue(i, j, this.player);
            this.updateField(i, j);
            this.player *= -1;
        }
    }

    public void setCallValue(int i, int j, int value) {
        this.field[i][j] = value;
    }

    private void updateField(int i, int j) {
        final int i0 = i;
        final int j0 = j;

        boolean flagDiagonal = true;
        boolean flagLine = true;

        while (i < 7) {
            i++;
            if (field[i][j0] == 0) flagLine = false;
            else if ((field[i][j0] == player) && flagLine) {
                this.cellPainting(i0, j0, i, j0);
                flagLine = false;
            }
            if (j0 + i - i0 < 8) {
                if (field[i][j0 + i - i0] == 0) flagDiagonal = false;
                else if ((field[i][j0 + i - i0] == player) && flagDiagonal) {
                    this.cellPainting(i0, j0, i, j0 + i - i0);
                    flagDiagonal = false;
                }
                if (!flagDiagonal && !flagLine) break;
            }
        }
        flagDiagonal = true;
        flagLine = true;
        i = i0;
        while (i > 0) {
            i--;
            if (field[i][j0] == 0) flagLine = false;
            else if ((field[i][j0] == player) && flagLine) {
                this.cellPainting(i, j0, i0, j0);
                flagLine = false;
            }
            if (j0 + i - i0 > -1) {
                if (field[i][j0 + i - i0] == 0) flagDiagonal = false;
                else if ((field[i][j0 + i - i0] == player) && flagDiagonal) {
                    this.cellPainting(i, j0 + i - i0, i0, j0);
                    flagDiagonal = false;
                }
                if (!flagDiagonal && !flagLine) break;
            }
        }
        flagDiagonal = true;
        flagLine = true;
        while (j < 7) {
            j++;
            if (field[i0][j] == 0) flagLine = false;
            else if ((field[i0][j] == player) && flagLine) {
                this.cellPainting(i0, j0, i0, j);
                flagLine = false;
            }
            if (i0 + j0 - j > -1) {
                if (field[i0 + j0 - j][j] == 0) flagDiagonal = false;
                else if ((field[i0 + j0 - j][j] == player) && flagDiagonal) {
                    this.cellPainting(i0, j0, i0 + j0 - j, j);
                    flagDiagonal = false;
                }
                if (!flagDiagonal && !flagLine) break;
            }
        }
        flagDiagonal = true;
        flagLine = true;
        j = j0;
        while (j > 0) {
            j--;
            if (field[i0][j] == 0) flagLine = false;
            else if ((field[i0][j] == player) && flagLine) {
                this.cellPainting(i0, j, i0, j0);
                flagLine = false;
            }
            if (i0 + j0 - j < 8) {
                if (field[i0 + j0 - j][j] == 0) flagDiagonal = false;
                else if ((field[i0 + j0 - j][j] == player) && flagDiagonal) {
                    this.cellPainting(i0 + j0 - j, j, i0, j0);
                    flagDiagonal = false;
                }
                if (!flagDiagonal && !flagLine) break;
            }
        }
    }

    private void cellPainting(int i0, int j0, int i, int j) {
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

    public boolean canDoTurn(int i, int j) {
        final int i0 = i;
        final int j0 = j;

        boolean flagLineOtherChip = false;
        boolean flagDiagonalOtherChip = false;
        boolean flagDiagonal = true;
        boolean flagLine = true;

        if (field[i][j] != 0) return false;

        while (i < 7) {
            i++;
            if (field[i][j0] == 0) flagLine = false;
            else if (field[i][j0] == player * -1) flagLineOtherChip = true;
            else if (field[i][j0] == player && !flagLineOtherChip) flagLine = false;
            else if (field[i][j0] == player && flagLine && flagLineOtherChip) return true;
            if (j0 + i - i0 < 8) {
                if (field[i][j0 + i - i0] == 0) flagDiagonal = false;
                else if (field[i][j0 + i - i0] == player * -1) flagDiagonalOtherChip = true;
                else if (field[i][j0 + i - i0] == player && !flagDiagonalOtherChip) flagDiagonal = false;
                else if (field[i][j0 + i - i0] == player && flagDiagonal && flagDiagonalOtherChip) return true;
                if (!flagDiagonal && !flagLine) break;
            }
        }
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
            else if (field[i][j0] == player && flagLine && flagLineOtherChip) return true;
            if (j0 + i - i0 > -1) {
                if (field[i][j0 + i - i0] == 0) flagDiagonal = false;
                else if (field[i][j0 + i - i0] == player * -1) flagDiagonalOtherChip = true;
                else if (field[i][j0 + i - i0] == player && !flagDiagonalOtherChip) flagDiagonal = false;
                else if (field[i][j0 + i - i0] == player && flagDiagonal && flagDiagonalOtherChip) return true;
                if (!flagDiagonal && !flagLine) break;
            }
        }
        flagLineOtherChip = false;
        flagDiagonalOtherChip = false;
        flagDiagonal = true;
        flagLine = true;
        while (j < 7) {
            j++;
            if (field[i0][j] == 0) flagLine = false;
            else if (field[i0][j] == player * -1) flagLineOtherChip = true;
            else if (field[i0][j] == player && !flagLineOtherChip) flagLine = false;
            else if (field[i0][j] == player && flagLine && flagLineOtherChip) return true;
            if (i0 + j0 - j > -1) {
                if (field[i0 + j0 - j][j] == 0) flagDiagonal = false;
                else if (field[i0 + j0 - j][j] == player * -1) flagDiagonalOtherChip = true;
                else if (field[i0 + j0 - j][j] == player && !flagDiagonalOtherChip) flagDiagonal = false;
                else if (field[i0 + j0 - j][j] == player && flagDiagonal && flagDiagonalOtherChip) return true;
                if (!flagDiagonal && !flagLine) break;
            }
        }
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
            else if (field[i0][j] == player && flagLine && flagLineOtherChip) return true;
            if (i0 + j0 - j < 8) {
                if (field[i0 + j0 - j][j] == 0) flagDiagonal = false;
                else if (field[i0 + j0 - j][j] == player * -1) flagDiagonalOtherChip = true;
                else if (field[i0 + j0 - j][j] == player && !flagDiagonalOtherChip) flagDiagonal = false;
                else if (field[i0 + j0 - j][j] == player && flagDiagonal && flagDiagonalOtherChip) return true;
                if (!flagDiagonal && !flagLine) break;
            }
        }
        return false;
    }

    public boolean isPat() {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (field[i][j] == 0) {
                    if (this.canDoTurn(i, j)) return false;
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
                if (field[i][j] != 0) {
                    if (field[i][j] == 1) flagWhite = true;
                    else flagBlack = true;
                }
            }
        }
        return isFull() || (flagBlack ^ flagWhite);
    }

    public String identifyWinner() {
        int black = 0;
        int white = 0;
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (field[i][j] == 1) {
                    white++;
                } else {
                    if (field[i][j] == -1) {
                        black++;
                    }
                }
            }
        }
        if (black > white) return "Победили черные!";
        else if (black != white) return "Победили белые!";
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
            for (int i = 0; i < 8; i++) {
                for (int j = 0; j < 8; j++) {
                    if (this.getField()[i][j] != other.getField()[i][j]) return false;
                }
            }
            return true;
        }
    }
}
