package ru.vsu.csf.Sashina;

import ru.vsu.csf.Sashina.game.GameBoard;
import ru.vsu.csf.Sashina.game.GameInterface;
import ru.vsu.csf.Sashina.game.GameLogic;
import ru.vsu.csf.Sashina.player.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Console implements GameInterface {

    Scanner scan = new Scanner(System.in);
    GameLogic game = new GameLogic(this);

    public void startGame() {
        System.out.print("How many players: ");
        int numberOfPlayers = checkAnswer(2, 6);
        List<Player> players = new ArrayList<>();
        for (int i = 0; i < numberOfPlayers; i++) {
            String message = java.text.MessageFormat.format("Enter your name, number {0}: ", i + 1);
            System.out.print(message);
            String name = scan.next();
            players.add(new Player(name));
        }
        System.out.println();
        System.out.println("__________________________________________________");
        System.out.println();
        game.setPlayers(players);
        game.playGame();
    }

    public void gameMenu(Player player, boolean[] actions) {
        int action = scan.nextInt();
        while (action < 1 || action > 7 || !actions[action - 1]) {
            System.out.println("Please write your actions correctly: ");
            action = scan.nextInt();
        }

        game.actions(action, player, actions);
    }

    public void getStreetOnBail(Player player) {
        String colour = scan.next();
        game.setStreetOnBail(colour, player);
    }

    public int checkAnswer(int a, int b) {
        try {
            int answer = scan.nextInt();
            while (answer < a || answer > b) {
                System.out.println("Please write your answer correctly: ");
                answer = scan.nextInt();
            }
            return answer;
        } catch (Exception e) {
            System.out.println("Invalid type. Try again");
            return checkAnswer(a, b);
        }
    }

    public void sendMessage(String message) {
        System.out.println(message);
    }
}
