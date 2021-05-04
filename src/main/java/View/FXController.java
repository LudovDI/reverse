package View;

import Model.Field;
import javafx.scene.control.Button;
import javafx.scene.control.DialogPane;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Ellipse;

public class FXController {

    public Label identifyWinner;
    public Label currentPlayer;
    public Label whiteScores;
    public Label blackScores;
    public Button startGame;
    public DialogPane infoBoard;
    public TextArea textArea;
    private Field field;
    private boolean flag = false;

    StackPane[][] cheeps = new StackPane[8][8];

    public GridPane table;

    public void start() {
        field = new Field();
        if (!flag) {
            for (int i = 0; i < 8; i++) {
                for (int j = 0; j < 8; j++) {
                    Ellipse whiteCheep = new Ellipse(13, 13);
                    whiteCheep.setVisible(false);
                    whiteCheep.setFill(Color.WHITE);
                    Ellipse blackCheep = new Ellipse(13, 13);
                    blackCheep.setVisible(false);
                    blackCheep.setFill(Color.BLACK);
                    StackPane stackPane = new StackPane(whiteCheep, blackCheep);
                    table.add(stackPane, i, j);
                    cheeps[i][j] = stackPane;
                }
            }
            flag = true;
        } else {
            for (int i = 0; i < 8; i++) {
                for (int j = 0; j < 8; j++) {
                    cheeps[i][j].getChildren().get(0).setVisible(false);
                    cheeps[i][j].getChildren().get(1).setVisible(false);
                }
            }
        }
        cheeps[3][3].getChildren().get(0).setVisible(true);
        cheeps[3][4].getChildren().get(1).setVisible(true);
        cheeps[4][3].getChildren().get(1).setVisible(true);
        cheeps[4][4].getChildren().get(0).setVisible(true);

        currentPlayer.setText("Ход черных");
        startGame.setText("Начать заново");
        whiteScores.setText(String.valueOf(field.getPlayerScore(1)));
        blackScores.setText(String.valueOf(field.getPlayerScore(-1)));
        infoBoard.setVisible(false);
        identifyWinner.setVisible(false);
    }

    public void turn(MouseEvent mouseEvent) {
        final int x = (int) Math.floor(mouseEvent.getX() / 40);
        final int y = (int) Math.floor(mouseEvent.getY() / 40);
        field.nextTurn(x, y);
        if (!field.isPat()) {
            if (field.getPlayer() == -1) currentPlayer.setText("Ход черных");
            else currentPlayer.setText("Ход белых");
        }
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                cheeps[i][j].getChildren().get(0).setVisible(field.get(i, j) == 1);
                cheeps[i][j].getChildren().get(1).setVisible(field.get(i, j) == -1);
            }
        }
        whiteScores.setText(String.valueOf(field.getPlayerScore(1)));
        blackScores.setText(String.valueOf(field.getPlayerScore(-1)));
        if (field.endGame()) identifyWinner.setText(field.identifyWinner());
    }

    public void info() {
        textArea.appendText("В игре используется квадратная доска размером 8 × 8\n" +
                "клеток (все клетки могут быть одного цвета) и 64\n" +
                "специальные фишки, окрашенные с разных сторон в\n" +
                "контрастные цвета, например, в белый и чёрный. Клетки\n" +
                "доски нумеруются от верхнего левого угла: вертикали —\n" +
                "латинскими буквами, горизонтали — цифрами (по сути\n" +
                "дела, можно использовать шахматную доску). Один из\n" +
                "игроков играет белыми, другой — чёрными. Делая ход,\n" +
                "игрок ставит фишку на клетку доски «своим» цветом\n" +
                "вверх.\n" +
                "\n" +
                "В начале игры в центр доски выставляются 4 фишки: чёрные\n" +
                "на d5 и e4, белые на d4 и e5.\n" +
                "\n" +
                "    Первый ход делают чёрные. Далее игроки ходят по\n" +
                "очереди.\n" +
                "    Делая ход, игрок должен поставить свою фишку на\n" +
                "одну из клеток доски таким образом, чтобы между этой\n" +
                "поставленной фишкой и одной из имеющихся уже на доске\n" +
                "фишек его цвета находился непрерывный ряд фишек\n" +
                "соперника, горизонтальный, вертикальный или\n" +
                "диагональный (другими словами, чтобы непрерывный ряд\n" +
                "фишек соперника оказался «закрыт» фишками игрока с\n" +
                "двух сторон). Все фишки соперника, входящие в\n" +
                "«закрытый» на этом ходу ряд, переворачиваются на\n" +
                "другую сторону (меняют цвет) и переходят к\n" +
                "ходившему игроку.\n" +
                "    Если в результате одного хода «закрывается»\n" +
                "одновременно более одного ряда фишек противника, то\n" +
                "переворачиваются все фишки, оказавшиеся на всех\n" +
                "«закрытых» рядах.\n" +
                "    Игрок вправе выбирать любой из возможных для него\n" +
                "ходов. Если игрок имеет возможные ходы, он не может\n" +
                "отказаться от хода. Если игрок не имеет допустимых\n" +
                "ходов, то ход передаётся сопернику.\n" +
                "    Игра прекращается, когда на доску выставлены\n" +
                "все фишки или когда ни один из игроков не может\n" +
                "сделать хода. По окончании игры проводится подсчёт\n" +
                "фишек каждого цвета, и игрок, чьих фишек на доске\n" +
                "выставлено больше, объявляется победителем. В\n" +
                "случае равенства количества фишек засчитывается ничья.");
        infoBoard.setVisible(true);
    }

    public void close() {
        infoBoard.setVisible(false);
    }
}
