package dataStructuresLab04Package;


public class Link implements Comparable<Link>{
	public String ref;
	public int weight;
	
	public Link(String ref) {
		this.ref = ref;
		weight = 1;
	}
	public Link(String ref, int weight) {
		this.ref = ref;
		this.weight = weight;
	}   
	@Override
	public boolean equals (Object obj) {
		Link another = (Link)obj;
		if(obj == null || getClass() != obj.getClass())
		{
			return false;
		}
		if(ref == null && another.ref != null) 
		{
				return false;
		} 
		if(!ref.equals(another.ref))
		{
			return false;
		}
		return true;
	}
	@Override
	public String toString() {
		return ref+"("+weight+")";
	}
	@Override
	public int compareTo(Link another) {
		return this.ref.compareTo(another.ref);
	}
}