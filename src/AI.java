import java.awt.Graphics2D;

public class AI implements IGameObject{

	private Minimax minimax;
	private Grid grid;
	
	private int timeInterval = 10;
	private int currentTime;
	private boolean startTimer;
	
	
	public AI (Grid grid) {
		this.grid = grid;
		minimax = new Minimax();
	}
	
	public void makeMove() {
		currentTime = 0;
		startTimer = true;
	
		//grid.placeMarker(minimax.getBestMove(grid.getMarkers(), grid.getTurn()));
		
	}
	
	
	@Override
	public void update(float deltaTime) {
		// TODO Auto-generated method stub
		if(!startTimer) {
			return;
		}
		currentTime += deltaTime;
		if(currentTime >= timeInterval) {
			grid.placeMarker(minimax.getBestMove(grid.getMarkers(), grid.getTurn()));
			startTimer = false;
		}
	}

	@Override
	public void render(Graphics2D graphicsRender) {
		// TODO Auto-generated method stub
		
	}
	
	public boolean isMoving() {
		return startTimer;
	}

}
