package GKA.Controler.MouseControler;

public class MouseSearchHandler {

	private boolean isFinished = false;
	protected String source;

	public MouseSearchHandler() {
		super();
	}

	public void cancelOperation() {
		setFinished(true);
	}

	public boolean isFinished() {
		return isFinished;
	}

	protected void setFinished(boolean isFinished) {
		this.isFinished = isFinished;
	}

}