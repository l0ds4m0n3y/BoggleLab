public class LetterCell {
    private int row, col;
    private LetterCell parent = null;
    private String character;

    public LetterCell(int row, int col, String character, LetterCell parent) {
        this.row = row;
        this.col = col;
        this.character = character;
        this.parent = parent;
    }

    public LetterCell(int row, int col, String character) {
        this.row = row;
        this.col = col;
        this.character = character;
    }

    public int getCol() {
        return col;
    }

    public int getRow() {
        return row;
    }

    public String getCharacter() {
        return character;
    }
}
