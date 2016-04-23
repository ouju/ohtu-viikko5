package ohtu;

public class TennisGame {

    private int player1Score = 0;
    private int player2Score = 0;
    private final String player1Name;
    private final String player2Name;

    public TennisGame(String player1Name, String player2Name) {
        this.player1Name = player1Name;
        this.player2Name = player2Name;
    }

    public void wonPoint(String playerName) {
        if ("player1".equals(playerName)) {
            player1Score++;
        } else if ("player2".equals(playerName)) {
            player2Score++;
        } else {
            throw new IllegalArgumentException("Nimellä ei löydy pelaajaa");
        }
    }

    public String scoreToString(int score) {
        switch (score) {
            case 0:
                return "Love";
            case 1:
                return "Fifteen";
            case 2:
                return "Thirty";
            case 3:
                return "Forty";
        }
        return "";
    }

    public String lessThanFour(int score1, int score2){
        String score = scoreToString(score1) + "-" + scoreToString(score2);
        return score;
    }
    
    public String equal(int score){
        if (score >= 4){
            return "Deuce";
        } else {
            return scoreToString(score) + "-All";
        }
    }
    
    public String atLeastFour(int score1, int score2){
        int minusResult = score1 - score2;
            if (minusResult == 1) {
                return "Advantage player1";
            } else if (minusResult == -1) {
                return "Advantage player2";
            } else if (minusResult >= 2) {
                return "Win for player1";
            } else {
                return "Win for player2";
            }
    }
    
    public String getScore() {
        if (player1Score == player2Score){
            return equal(player1Score);
        } else if (player1Score >= 4 || player2Score >= 4) {
            return atLeastFour(player1Score, player2Score);
        } else {
            return lessThanFour(player1Score, player2Score);
        }
    }
}
