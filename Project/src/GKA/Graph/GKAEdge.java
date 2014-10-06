package GKA.Graph;

import org.jgrapht.graph.DefaultEdge;

public class GKAEdge extends DefaultEdge{

	/**
	 * 
	 */
	private static final long serialVersionUID = -6523434696944744833L;
	
	private String edgeName = null;
	private Double weight = null;
	
	public GKAEdge(){
		super();
	}
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
	
	public String getName(){
		return this.edgeName;
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
    		retVal = "(" + edgeName + ")";
    	}
    	if (getWeight() != null){
    		retVal += " : " + getWeight();
    	}
    	return retVal;
    }
    @Override
    public boolean equals(Object object){
        if(object == null){
            return false;
        }
        else if(object == this){
            return true;
        }
        else if(!(object instanceof GKAEdge)){
            return false;
        }
        else{
            GKAEdge edge = (GKAEdge) object;
            return this.getSource().equals(edge.getSource()) && 
                    this.getTarget().equals(edge.getTarget()) && 
                    this.getName().equals(edge.getName()) && 
                    this.getWeight().equals(edge.getWeight());
        }
    }
    @Override
    public int hashCode(){
        return getSource().hashCode() + getTarget().hashCode() + getName().hashCode() + getWeight().hashCode();
    }

}
