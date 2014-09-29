package GKA.Graph;

import org.jgrapht.graph.DefaultEdge;

public class GKAEdge extends DefaultEdge{

	/**
	 * 
	 */
	private static final long serialVersionUID = -6523434696944744833L;
	
	private String edgeName = null;
	private Double weight = null;
	
	public Object getSource(){
		return super.getSource();
	}
	
	public Object getTarget(){
		return super.getTarget();
	}
	
	public Double getWeight() {
		return weight;
	}
	
	public void setWeight(Double weight) {
		this.weight = weight;
	}
	
	public void setName(String name){
		this.edgeName = name;
	}
	
    public String toString()
    {
    	String retVal;
    	if (edgeName == null){
    		retVal = "(" + getSource() + " : " + getTarget() + ")";
    	}
    	else{
    		retVal =  edgeName;
    	}
    	if (getWeight() != null){
    		retVal += " : " + getWeight();
    	}
    	return retVal;
    }

}
