import java.util.ArrayList;

public class Checker {

	public static ArrayList<Marker> checkWin(Marker[][] markers){
		ArrayList<Marker> match;
		
		for (int i = 0; i < main.size; i++) {
			int x = i % main.rows;
			int y = i / main.rows;
			
			// diagonal bottom left, top right
			match = checkMatch(x, y, 1, -1, i, markers);
			
			// diagonal top left, bottom right
			if(match == null) {
				match = checkMatch(x, y, 1, 1, i, markers);
			}

			// horizontal
			if(match == null) {
				match = checkMatch(x, y, 1, 0, i, markers);
			}

			// vertical
			if(match == null) {
				match = checkMatch(x, y, 0, 1, i, markers);
			}
			
			if(match != null) {
				return match;
			}
		}
		
		return null;
	}
	
	private static ArrayList<Marker> checkMatch(int x, int y, int dX, int dY, int index, Marker[][] markers){
		ArrayList<Marker> match = new ArrayList<Marker>(main.match);
		int type = -1;
		int checkCount = 0;
		
		while(checkCount < main.rows && index < main.size && x >= 0 && x <= main.rows - 1 && 
				y >= 0 && y <= main.rows - 1) {
			boolean found = false;
			Marker marker = markers[x][y];
			if(marker != null) {
				if(type == -1) {
					type = marker.getType();
				}
				
				if(marker.getType() == type) {
					match.add(marker);
					found = true;
				}
			}
			
			if(!found && match.size() < main.match) {
				match.clear();
				type = -1;
			}
			
			
			x += dX;
			y += dY;
			index ++;
			checkCount ++;
		}
		return match.size() >= main.match ? match : null;
	}

	public static int getWinType(Marker[][] markers) {
		// TODO Auto-generated method stub
		
		ArrayList<Marker> match = checkWin(markers);
		
		return match == null ? -1 : match.get(0).getType();
			
		
		//return 0;
	}
	
}