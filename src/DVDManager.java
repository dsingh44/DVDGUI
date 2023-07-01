import java.util.*;

public class DVDManager {
	
	public static void main (String[] args) {
		
		DVDCollection dl = new DVDCollection();
		
		dl.loadData("dvddata.txt");
		
		DvdGui dgui = new DvdGui(dl);
		
		
	}

}
