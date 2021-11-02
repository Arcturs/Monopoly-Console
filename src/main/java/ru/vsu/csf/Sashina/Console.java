package ru.vsu.csf.Sashina;

import ru.vsu.csf.Sashina.game.GameLogic;
import ru.vsu.csf.Sashina.player.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Console extends GameLogic {

    Scanner scan = new Scanner(System.in);

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
        setPlayers(players);
        playGame();
    }

    @Override
    protected void gameMenu(Player player, boolean[] actions) {
        super.gameMenu(player, actions);

        int action = scan.nextInt();
        while (action < 1 || action > 7 || !actions[action - 1]) {
            System.out.println("Please write your actions correctly: ");
            action = scan.nextInt();
        }

        actions(action, player, actions);
    }

    @Override
    protected void getStreetOnBail(Player player) {
        super.getStreetOnBail(player);
        String colour = scan.next();
        super.setStreetOnBail(colour, player);
    }

    @Override
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

    @Override
    protected void sendMessage(String message) {
        System.out.println(message);
    }
}
