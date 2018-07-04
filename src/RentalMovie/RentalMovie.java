package RentalMovie;

import java.io.PrintWriter;
import java.util.Scanner;

/*
 * class Rentalmovie
 * 
 * Encapsulates the attribute detail and functionality required to
 * record and manage "normal" movie's in the movie rental system
 * as described in Stage 1 of the specification.
 * 
 * You should leave this class as-is while implementing the requirements
 * in stages 2-4, but you can/will need to amend this class for stage 5
 * (exception handling) and the bonus marks stage (file handling) to
 * facilitate the addressing of the requirements set out in those stages.
 *  
 */

public class RentalMovie
{
   private String movieID;
   private String title;
   private int rentalFee;
   private boolean available;
   private String borrowerID;
   
   
   // Constructor read value for new account from scanner
   public RentalMovie(Scanner fileScanner)
   {
   	this.movieID = fileScanner.nextLine();
   	this.title = fileScanner.nextLine();
   	this.rentalFee = fileScanner.nextInt();
   	this.available = fileScanner.nextBoolean();
   	fileScanner.nextLine();
   	this.borrowerID = fileScanner.nextLine();
   	
   	
   }
   
   // method to write details out for an Account
   public void writeDetails(PrintWriter pw)
   {
   	//Write the class name to file
   	pw.println(this.getClass().getSimpleName());
   	pw.println(movieID);
   	pw.println(title);
   	pw.println(rentalFee);
   	pw.println(available);
   	pw.println(borrowerID);
   }
   
   public RentalMovie(String movieID, String title, int rentalFee)
   {
      this.movieID = movieID;
      this.title = title;
      this.rentalFee = rentalFee;
      
      // initialise movie availability status to true to reflect the movie being 
      // "available" initially
      this.available = true;
      
      // initialise borrower ID to default value
      this.borrowerID = "not on loan";
   }

   /*
    * Accessors (getters)
    * 
    * Use these as needed in latter stages of the assignment.
    */
   
   public String getMovieID()
   {
      return movieID;
   }
   
   public boolean getAvailable()
   {
   	return available;
   }
   
   public String getTitle()
   {
   	return title;
   }
   
   public String getborrowerID()
   {
   	return borrowerID;
   }


   public int getrentalFee()
   {
   	return rentalFee;
   }

   public boolean isAvailable()
   {
      return available;
   }
   
  
   /*
    * Mutators (setters)
    * 
    * These are all that is required for stages 2-5 of the assignment, 
    * but you may define additional mutators for the bonus marks stage 
    * (file handling) as needed.
    */
   
   public void setBorrowerID(String memberID)
   {
      this.borrowerID = memberID;
   }

   public void setAvailability(boolean isAvailable)
   {
      this.available = isAvailable;
   }
   
  
   public void setBorrow()
   {
   	this.borrowerID = "not on loan";
   }
   
   
   
   /*
    * borrowMovie()
    * 
    * Operation which accepts a the member ID of the borrower as a parameter 
    * and updates the loan status and borrower ID if the movie is available.
    * 
    * Returns false (indicating that borrowing of the movie failed) if the
    * movie is already on loan, otherwise returns true (to indicate that the 
    * movie has been borrowed successfully).
    */
   
   
   public void borrowMovie(String memberID) throws MovieException
   {
   	if (available == false)
       {
          throw new MovieException("Movie is Not Available this time");
       }
       else
       {
          // movie has been borrowed so update loan status and borrower ID
          this.available = false;
          this.borrowerID = memberID;
          
       }
   	
   }
//   public boolean borrowMovie(String memberID)
//   {
//      // reject borrowing of movie if it is not available
//      if (available == false)
//      {
//         return false;
//      }
//      else
//      {
//         // movie has been borrowed so update loan status and borrower ID
//         this.available = false;
//         this.borrowerID = memberID;
//         return true;
//      }
//   }
   
   /*
    * returnMovie()
    * 
    * Operation which accepts the number of days the movie was on loan
    * as a parameter, resets the loan status and borrower ID to their
    * default values if the movie was on loan movie and calculates / returns
    * any fine that may apply for a late returning of the movie.
    * 
    * Returns Double.NaN (indicating that returning of the movie failed) if the
    * movie is not currently on loan, otherwise returns either 0.0 (indicating
    * that the movie was returned on time) or a positive double value which
    * represents the fine that applies for returning the movie late.
    */
   public double returnMovie(int daysBorrowed)
   {
      // reject return of movie if it is not currently on loan
      if (available == true)
      {
         return Double.NaN;
      }
      else
      {
         // movie has been borrowed so reset loan status and borrower ID
         this.available = true;
         this.borrowerID = "not on loan";
         
         // determine whether the movie was returned late
         int daysLate = daysBorrowed - 7;
         
         if (daysLate > 0)
         {
            // movie was returned late, so return fine
            return daysLate * 2.0;
         }
         else
         {
            // movie was returned on time - no fine applies
            return 0.0;
         }
      }
   }
   
   /*
    * printDetails()
    * 
    * Prints the instance variable details for this Sale
    * to the screen, as well as the "outcome" of the sale.
    */
   public void printDetails()
   {
      System.out.printf("%15s%s\n", "Movie ID:", movieID);
      System.out.printf("%15s%s\n", "Title:", title);
      System.out.printf("%15s$%d\n", "Rental Fee:", rentalFee);
      System.out.printf("%15s%b\n", "Available:", available);
      System.out.printf("%15s%s\n", "Borrower ID:", borrowerID);
   }
   
   
   
}