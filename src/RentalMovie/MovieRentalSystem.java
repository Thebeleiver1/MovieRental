package RentalMovie;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.PrintWriter;

/*
 * class MovieRentalSystem
 * 
 * Implements the data storage and functional requirements
 * for a menu-driven program that manages property sales.
 * 
 * This is the start-up code for Assignment 3 and you should
 * work off this program - the features described in the
 * specification for Stages 2 and 4 should be implemented in 
 * the corresponding helper methods included at the bottom of 
 * this class.
 * 
 */

import java.util.*;


public class MovieRentalSystem
{

   /* 
    * You can refer to the array and Scanner object here anywhere
    * within this class, even within helper methods that you are
    * required to implement the code for each of the features within
    */
   
   private static final RentalMovie [] movies = new RentalMovie [100];
   private static int movieCount = 0;
   
   private static final Scanner sc = new Scanner(System.in);
   
   public static void main(String[] args) 
   {
      String selection;
            
      // creating and store the specified set of FerryBooking objects 
      // in the array here (Stage 2 - requirement (i))
      // ...
      
      // loading the save data form file to array and displaying it.
      loadData();
      // implementation of the program menu
      
      do
      {

         // print menu to screen
         System.out.println("*** Movie Rental System Menu ***");
         System.out.println();
         
         System.out.printf("%-25s%s\n", "Add Rental Movie", "A");
         System.out.printf("%-25s%s\n", "Display Movie List", "B");
         System.out.printf("%-25s%s\n", "Borrow Movie", "C");
         System.out.printf("%-25s%s\n", "Return Movie", "D");
         System.out.printf("%-25s%s\n", "Add New Release Movie", "E");
         System.out.printf("%-25s%s\n", "Reserve Movie", "F");
         System.out.printf("%-25s%s\n", "Exit Program", "X");
         System.out.println();
         
         // prompt user to enter selection
         System.out.print("Enter selection: ");
         selection = sc.nextLine();
         
         
         System.out.println();
         
         // validate selection input length
         if (selection.length() != 1)
         {
            System.out.println("Error - invalid selection!");
         }
         else
         {
           
            
            // process user's selection
            switch (selection.toUpperCase())
            {
               case "A":
               	addRentalMovie();
                  break;
                  
               case "B":
                  displayMovieList();
                  break;
                  
               case "C":
               	try{
                  borrowMovie();
               	}
               	catch(MovieException e){
               		System.out.println(e.getMessage());
               	}
                  break;
                  
               case "D":
                  returnMovie();
                  break;
                  
               case "E":
                  addNewReleaseMovie();
                  break;
                  
               case "F":
                  reserveMovie();
                  break;
                  
               case "X":
               	saveData();
                  System.out.println("Exiting the program...");
                  break;
                  
               default:
                  System.out.println("Error - invalid selection!");
            }
         }
         System.out.println();
         
      } while (!selection.equals("X"));

   }

   // helper method which implements the functionality for
   // adding a new RentalMovie
   //int count = 0;
   
   private static void addRentalMovie()
   {
      /* temp code so that you can see which options are being
       * selected in the menu in its start up form - you can
       * remove it when you are ready to implement the actual code
       * for this feature (and for the features that follow)
       */
   	
      System.out.println("Add RentalMovie option selected");
      System.out.println();
      
      // implement your code for Stage 2 Requirement A) here
      // ...
      System.out.println("Enter The movie ID: ");
      String movieId = sc.nextLine();
      System.out.println("Enter The Movie Name: ");
      String movieName = sc.nextLine();
      System.out.println("Enter The movie Rental Fee: ");
      int rentalFee = sc.nextInt();
      sc.nextLine();
      
      movies[movieCount++] = new RentalMovie(movieId, movieName, rentalFee);
      
     
   }

// dispaly all the movie from array
   private static void displayMovieList()
   {
      System.out.println("Display Movie List option selected");
      System.out.println();

      // implement your code for Stage 2 Requirement B) here
      // ...
      if(movieCount == 0)
   		System.out.println("There are no any movie in our store to Display");
      else
      {
	      for(int i = 0; i < movieCount;i++ )
			{
	      	movies[i].printDetails();
				System.out.println();
			}
      }
      
   }
   
   // throw error if movie is already on loan
   private static void borrowMovie() throws MovieException
   {  
   	
      System.out.println("Borrow Movie option selected");
      System.out.println();
      
      // implement your code for Stage 2 Requirement C) here
      // ...
      System.out.println("Enter the Movie Id You want to borrow :");
   	String movieId = sc.nextLine();
   	
   	int count = 0;
   	// looping to check if entered id is there
   	for(int i = 0;i < movieCount;i++ )
   	{
   		
			if(movies[i].getMovieID().equalsIgnoreCase(movieId))
			{
				System.out.println("Enter your member id :");
				String memberId = sc.nextLine();
				movies[i].borrowMovie(memberId);
				count = 1;	
			}
   		
   	}
   	// if selected id is not available
   	if(count == 0)
   	{
   		System.out.println("The id you enterd is not available");
   	}
   		
   }
   
   // check if the movie is in loan and let return
   private static void returnMovie()
   {    
      System.out.println("Return Movie option selected");
      System.out.println();

      // implement your code for Stage 2 Requirement D) here
      // ...
      System.out.println("Enter the Movie Id You want to Return :");
   	String movieId = sc.nextLine();
   	  	
   	
   	int count = 0;
   	for(int i = 0;i < movieCount;i++ )
   	{
   		
			if(movies[i].getMovieID().equalsIgnoreCase(movieId))
			{
				System.out.println("Enter Number of Days: ");
				int days = sc.nextInt();
				double check = movies[i].returnMovie(days);
				// checking if movie is in rent
				if(Double.isNaN(check))
					System.out.println("The movie is not in rent At the moment. ");
				// sum the amount need to pay
				else					
					System.out.println("The sum you pay is " + check);
				
				count = 1;	
			}
			
   		
   	}
   	if(count == 0)
   	{
   		System.out.println("The id you enterd is not available");
   	}
      sc.nextLine();
   }
   
   // Adding new release movie to movies array  as new release object
   private static void addNewReleaseMovie()
   {      
      System.out.println("Add NewReleaseMovie option selected");
      System.out.println();

      // implement your code for Stage 4 Requirement A) here
      // ...
      System.out.println("Enter the new release movie Id: ");
      String movieId = sc.nextLine();
      System.out.println("Enter the new release movie title: ");
      String title = sc.nextLine();
      movies[movieCount++] = new NewReleaseMovie(movieId, title);
      
   }
   
   // reserve movie option for new release movie
   private static void reserveMovie()
   {
      System.out.println("Reserve Movie option selected");
      System.out.println();
      
      // implement your code for Stage 4 Requirement B) here
      // ...
      System.out.println("Enter the New release Movie Id you want to Reserve: ");
   	String movieId = sc.nextLine();
   	
   	int count = 0;
   	for(int i = 0;i < movieCount;i++ )
   	{
   		
			if(movies[i].getMovieID().equalsIgnoreCase(movieId))
			{
				//checking if the selected id is new release movie
				if(movies[i] instanceof NewReleaseMovie)
				{
					System.out.println("Enter your member id :");
					String memberId = sc.nextLine();
					((NewReleaseMovie) movies[i]).reserveMovie(memberId);
				}
				// the id belongs to old movie
				else
				{
					System.out.println("Old movies can't be Reserved");
				}
				count = 1;	
			}
   		
   	}
   	if(count == 0)
   	{
   		System.out.println("The id you enterd is not available");
   	}
   }
   
   // add any additional helper methods you may need for searching
   // or file handling here
   // ...
   
   
   // loading data from a file 
   private static void loadData()
   {
      RentalMovie movie;
      String tag;
      
      try
      {
      	Scanner fileScanner = new Scanner(new FileReader("movies.txt"));
      	
      	// read until end of file is reached
      	while (fileScanner.hasNextLine())
      	{
      		// read in next tag
      		tag = fileScanner.nextLine();
      		
      		// reset reference
      		movie = null;
      		// Store to object reading tag name
      		if (tag.equals("RentalMovie"))
      		{
      			movie = new RentalMovie(fileScanner);
      			
      		}
      		else if (tag.equals("NewReleaseMovie"))
      		{
      			movie = new NewReleaseMovie(fileScanner);
      			
      	   }
      		
      		if (movie == null)
      		{
      			System.out.println("Invalid tag - " + tag);
      			
      		}
      		else
      		{
      			//fileScanner.nextLine();
      			// Storing object to an array
      			movies[movieCount] = movie;
      			movieCount++;
      			
      			
      		}
      	}
      	// calling the method to display saved data when loading
      	displayMovieList();

			// closing file
			fileScanner.close();
		}
		catch (FileNotFoundException e)
		{
			System.out
						.println("Error - could not open file \"movies.txt\" for writing!");
		}
      

      
   }

   
   public static void saveData()
   {
      
      try
      {
         // open file for writing

      	PrintWriter pw = new PrintWriter("movies.txt");

         // Write each details form array to file       
         for (int i = 0; i < movieCount; i++)
         {
            movies[i].writeDetails(pw);
         }
         
         // closing file 
         pw.close();
      }
      catch (FileNotFoundException e)
      {
         System.out.println("Error - could not open file \"movies.txt\" for writing!");
      }
   }
      

}