package EloRating;
public class EloRating {
	
	public static int newRating(int ratingPlayer, int ratingPlayerTwo, int scorePlayer) {
		double diffRating = ratingPlayerTwo - ratingPlayer;
		double expectedScorePlayer = 1.0/(1.0 + Math.pow(10, ((double)(diffRating))/400.0));
		return (int)( ratingPlayer + calculateKfactor(ratingPlayer)*(scorePlayer - expectedScorePlayer));
	}
	
	private static int calculateKfactor(int ratingPlayer) {
		if(ratingPlayer<2100) {
			return 32;
		} else if(2100<=ratingPlayer && ratingPlayer<=2400) {
			return 24;
		} else if(ratingPlayer>2400) {
			return 16;
		} else {
			return 0;
		}
	}
	
	public static void main(String[] args) {
		System.out.println(EloRating.newRating(400, 400, 1));
	}

}
