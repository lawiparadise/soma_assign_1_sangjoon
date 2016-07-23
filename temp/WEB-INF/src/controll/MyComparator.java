package controll;

import java.util.Comparator;

public class MyComparator implements Comparator<Double>{

	@Override
	public int compare(Double o1, Double o2) {
		if(o1 > o2){
			return -1;
		} else if(o1 == o2){
			return 0;
		} else {
			return 1;
		}
	}
	

}
