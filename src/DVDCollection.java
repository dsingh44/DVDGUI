import java.io.*;

public class DVDCollection {
	
	/* The current number of DVDs in the array */
	private int numdvds;
	
	/* The array to contain the DVDs*/
	private DVD[] dvdArray;
	
	/*Boolean flag to indicate whether the  DVD collection was modified since it was saved*/
	private boolean modified;
	
	
	public DVDCollection() {
		numdvds= 0;
		dvdArray= new DVD[7];
	}
	public DVD[] DVDinfo() {
		return dvdArray;
	}
	
	
	public String toString() {
		
		String stringdvds ="";
		stringdvds += "\n" + "numdvds = " + numdvds + '\n' +"dvdArray.length" + " " +dvdArray.length+'\n';
		
		for (int i = 0; i< numdvds;i++) {
			stringdvds += "dvdArray [" + i+ "] "+dvdArray[i].getTitle() +  " " + dvdArray[i].getRating() + " "+ dvdArray[i].getRunningTime()+'\n';
		}
		return stringdvds;
	}
	
	
	public void addOrModifyDVD (String title, String rating, String runningTime) {
		
		if(!(rating.equals ("NC-17")|| rating.equals("PG-13") || rating.equals("PG") || rating.equals("R") || rating.equals("G"))) {
			return;
		}
		int runT = Validruntime(runningTime);
		if (runT <= 0) {
			return;
		}
		modified = true;
		int j= DVDlookup(title);
		
		//dvd already in collection
		if (j > 0)
		{
			dvdArray[j].setTitle(title);
			dvdArray[j].setRating(rating);
			dvdArray[j].setRunningTime(runT);
		}
		else
		{
			if (numdvds < dvdArray.length)
			{
				dvdArray[numdvds] = new DVD (title, rating, runT);
				numdvds++;
			}
			else 
			{
				DVD[] newArray = new DVD [dvdArray.length * 2];
				System.arraycopy(dvdArray, 0, newArray, 0, numdvds);
				dvdArray = newArray;
				System.out.print(toString());
				
			}
		}
		
	}
	
	//additional private helper methods
	private int Validruntime(String runningTime) {
		int runT=0;
		try {
			runT = Integer.parseInt(runningTime);
		}
		catch(NumberFormatException e)
		{
			
		}
		
		return runT;	
	}
	
	
	private int DVDlookup(String title) 
	{
		if (numdvds > 0) 
		{
			for (int index =0; index < numdvds; index++) 
			{
				if (dvdArray[index].getTitle().equals(title)) 
				{
					return index;
				}
			}
		}
		return -1 ;
		
	}
	
	

}
