package RentalMovie;

import java.io.PrintWriter;
import java.util.Scanner;

public class NewReleaseMovie extends RentalMovie  {
	private String reserveID;
	
	public NewReleaseMovie(Scanner fileScanner)
	{
		super(fileScanner);
		this.reserveID = fileScanner.nextLine();
	}
	
	
	

	@Override
	public void writeDetails(PrintWriter pw) {
		
		super.writeDetails(pw);
		pw.println(reserveID);
	}




	// constructor for Newrelease movie with fixe rental price
	public NewReleaseMovie(String movieID, String title) {
		super(movieID, title,6);
		this.reserveID = "not on reserve";
	}
	
	// accessor
	public String getreserveID()
	{
		return reserveID;
	}
	
	//reserve movie if it is not available
	public boolean reserveMovie(String memberId)
	{
		if(getAvailable() == true ||(!reserveID.equals("not on reserve")))
		{
//			if(getAvailable() == true)
//				System.out.println("Movie is available can't reserve ");
//			else if(!reserveID.equals("not on reserve"))
//				System.out.println("Sorry! Movie is already reserved ");
			return false;
		}
		else
		{
			this.reserveID = memberId;
			//System.out.println("Movie Reserved Successfully ");
			return true;
		}
		
		
	}

	@Override
	// over riding method to handle new movie 
	// checking if borrower is same as reserver
	public void borrowMovie(String memberID) throws MovieException
	{
		if(!(reserveID.equals("not on reserve")))
		{
			if(reserveID.equals(memberID))
			{
				super.borrowMovie(memberID);
				reserveID = "not on reserve";
				
				
			}
			else
			{
				throw new MovieException("Your Reserve id doesn't Match ");
				
			}
		}
		else
		{
			super.borrowMovie(memberID);
			
		}
		
	}
	
	

	@Override
	// overriding return movie where late payment fee is $10
	public double returnMovie(int daysBorrowed) {
		 if (getAvailable() == true)
	      {
	         return Double.NaN;
	      }
	      else
	      {
	         // movie has been borrowed so reset loan status and borrower ID
	      	setAvailability(true);
	         setBorrow();
	         
	         // determine whether the movie was returned late
	         int daysLate = daysBorrowed - 7;
	         
	         if (daysLate > 0)
	         {
	            // movie was returned late, so return fine
	            return daysLate * 10.0;
	         }
	         else
	         {
	            // movie was returned on time - no fine applies
	            return 0.0;
	         }
	      }
	}

	@Override
	public void printDetails() {
		
		super.printDetails();
		System.out.printf("%15s%s\n", "Reserve Id:", reserveID);
	}
	
	

}
