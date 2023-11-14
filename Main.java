/*
 * David Luna
 * CS270
 * Lab 8
 * creates a GUI to play Boogle!, as the player guesses
 * the gui displays the word they got correct, moves a progress
 * bar at the bottom to encourge the player to find all the words
 * along with a message to when they score correct words in a row.
 * Once the player gives up, they can press 'solve" where the game 
 * will give them all the words they missed and allow them to play
 * a new randomaized game that uses dice provided.
 */

public class Main {
    public static void main(String[] args) {
        new BoggleGameFrame();
    }
}