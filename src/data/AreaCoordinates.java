package data;

public class AreaCoordinates {
	
	int[] coo;
	boolean display;
	String direction;
	String spotName;
	
	public AreaCoordinates(int[] coo, String direction, String spotName, boolean display) {
		this.coo = coo;
		this.direction = direction;
		this.spotName = spotName;
		this.display = display;
	}
	
	public int[] getCoo() {
		return coo;
	}
	public String getDirection() {
		return direction;
	}
	public void setCoo(int[] coo) {
		this.coo = coo;
	}
	public void setDirection(String direction) {
		this.direction = direction;
	}

	public String getSpotName() {
		return spotName;
	}

	public void setSpotName(String spotName) {
		this.spotName = spotName;
	}

	public boolean isDisplay() {
		return display;
	}

	public void setDisplay(boolean display) {
		this.display = display;
	}
	
	
}


