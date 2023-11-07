import java.util.ArrayList;

public class Minimax {
	
		private int bestMove = 0;
		private int counter = 0;
		
		public int getBestMove(Marker[][] markers, int requester) {
			bestMove = 0;
			counter  = 0;
			minimax(markers, requester, true, 0, Integer.MIN_VALUE,
					Integer.MAX_VALUE);
			
			System.out.println("Minimax took " + counter + "tries");
			return bestMove;
		}
		
		private int minimax(Marker[][] markers, int requester, boolean requesterMove,
				int depth, int alpha, int beta) {
			
			counter++;
			int winner = Checker.getWinType(markers);
			if(winner >= 0 || getMarkersPlacedSize(markers) == main.size) {
				return getFieldScore(markers, requester, depth);
			}
			
			ArrayList<Integer> scores = new ArrayList<Integer>();
			int [] openMoves = getOpenSpotsIndexes(markers);
			int score = 0;
			
				for(int i = 0; i < openMoves.length; i++) {
					int x = openMoves[i] % main.rows;
					int y = openMoves[i] / main.rows;
					
					if(!requesterMove) {
						depth++;
					}
					
					int marker = requesterMove ? requester : requester + 1;
					markers[x][y] = new Marker(marker);
					score = minimax(markers, requester, !requesterMove, depth,
							alpha, beta);
					scores.add(score);
					markers[x][y] = null;
					
					if(requesterMove) {
						int maxValue = Math.max(Integer.MIN_VALUE, score);
						alpha = Math.max(alpha,  maxValue);
						
						if(alpha >beta) {
							return maxValue;
						}
					}
					else {
						int minValue = Math.min(Integer.MAX_VALUE, score);
						beta = Math.min(beta, minValue);
						if(beta < alpha) {
							return minValue;
						}
					}
				}
			
				int scoreIndex = 0;
				if(requesterMove) {
					scoreIndex = getMax(scores);
					
				}
				else {
					scoreIndex = getMin(scores);
					//betsMove = openMoves[scoreIndex];
				}
				bestMove = openMoves[scoreIndex];
			return scores.get(scoreIndex);
		}
		
		private int getMin(ArrayList<Integer> scores) {
			// TODO Auto-generated method stub
			int index = 0;
			int min = 0;
			for(int i = 0; i < scores.size(); i++) {
				if(scores.get(i) <= min) {
					index = i;
					min = scores.get(i);
				}
			}
			
			
			return index;
			
			//return 0;
		}

		private int getMax(ArrayList<Integer> scores) {
			// TODO Auto-generated method stub
			int index = 0;
			int max = 0;
			for(int i = 0; i < scores.size(); i++) {
				if(scores.get(i) >= max) {
					index = i;
					max = scores.get(i);
				}
			}
			
			
			return index;
		}

		private int getFieldScore(Marker[][] markers, int requester, int depth) {
			// TODO Auto-generated method stub
			ArrayList<Marker> match = Checker.checkWin(markers);
			if(match == null) {
				return 0;
			}
			if(match.get(0).getType() == requester) {
				return main.size - depth;
			}
			return (main.size * -1 ) + depth;
			//return 0;
		}

		private int [] getOpenSpotsIndexes(Marker[][] markers) {
			int [] openSpots = new int [main.size - getMarkersPlacedSize(markers)];
			int openSpotIndex = 0;
			
				for(int x = 0; x < markers.length; x++) {
					for(int y = 0; y < markers[x].length; y++) {
						if(markers[x][y] == null) {
							openSpots[openSpotIndex] = ( y * main.rows) + x;
							openSpotIndex++;
						}
					}
				}
				return openSpots;
		}
		
		private int getMarkersPlacedSize(Marker[][] markers) {
			// TODO Auto-generated method stub
			int result = 0;
			
			for(int x = 0; x < markers.length; x++) {
				for(int y = 0; y < markers[x].length; y++) {
					if(markers[x][y] != null) {
						result ++;
					}
				}
			}
			
			return result;
			//return 0;
		}
	
}
