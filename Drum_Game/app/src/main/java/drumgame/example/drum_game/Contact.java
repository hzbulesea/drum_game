package drumgame.example.drum_game;

class Contact {

    private int id;
    private String song;
    private int score;

    public Contact(int id, String song, int score) {
        this.id = id;
        this.song = song;
        this.score = score;
    }

    public void setSong(String song) {
        this.song = song;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSong() {
        return song;
    }

    public int getScore() {
        return score;
    }

    public int getId() {
        return id;
    }
}
