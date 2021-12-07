package project3;
/**
* COSC 1020 – Fall 2021
* Project 3
* <This class takes in an audio file name as input from the user
*  and edits that audio based a series of commands given from the user> * @author Max Steffen
*/
import java.util.*;
public class AudioProcessor
{
	public static void main(String[] args) 
	{
		
		Scanner scan = new Scanner(System.in);
		Random ran = new Random();
		System.out.print("Enter audio file name: "); String field = scan.next();
		double[] array = StdAudio.read(field);
		double[] arrayOrig = StdAudio.read(field); //used to reset array for testing purposes
		
		String run = "g";

		while(!run.toLowerCase().equals("q"))
		{
			System.out.print("\rSelect command (p, r, s, n, v, o, q): ");
			run = scan.next().toLowerCase();
			if(run.equals("p"))
			{
				System.out.print("Playing sound");
				StdAudio.play(field);
			}
			else if(run.equals("r"))
			{	
				System.out.print("Reversing sound");
				double tempValue;
			 	for(int i = 0; i<array.length/2; i++)
				{
				   tempValue = array[i];
				 array[i] = array[(array.length-1)-i];
				  array[(array.length-1)-i] = tempValue;
				}
			 	StdAudio.save(field, array);
			}
			else if(run.equals("s"))
			{

				System.out.print("By what factor would you like to speed up the sound?  ");
				double scale =  Math.abs(scan.nextDouble());
				if(scale>=1)
				System.out.print("Speeding up sound");
				if(scale<1)
				System.out.print("Slowing down sound");
				double[] array2 = new double[(int) (array.length/scale)];
				for(int i = 0; i<array2.length; i++)
				{
					array2[i] = array[(int)(i*scale)];
				}
			 	StdAudio.save(field, array2);
			}
			else if(run.equals("n"))
			{	
				System.out.print("Add how much noise? ");
				double amount = scan.nextDouble();
				System.out.print("Adding noise to sound");
				double test;

			 	for(int i = 0; i<array.length; i++)
				{
			 		test = ran.nextDouble();
			 		if(test>.5)
			 			test = test*-1*amount;
			 		else
			 			test = test*amount;
			 		
			 		if((array[i] + test)>1)
			 		array[i] = 1;
			 		else if((array[1] + test)<-1)
				 		array[i] = -1;
			 		else
				 		array[i] += test;
				}
			 	StdAudio.save(field, array);

			}
			else if(run.equals("v"))
			{
				System.out.print("Increase volume by what scale? ");
				double amount = scan.nextDouble();
				if(amount>=1)
					System.out.print("Increasing volume of sound");
					if(amount<1)
					System.out.print("Decreasing volume of sound");
			 	for(int i = 0; i<array.length; i++)
				{
			 		if((array[i] * amount)>1)
			 		array[i] = 1;
			 		else if((array[1] * amount)<-1)
				 		array[i] = -1;
			 		else
				 		array[i] = array[i] * amount;
				}
			 	StdAudio.save(field, array);
			}
			else if(run.equals("o"))
			{
				System.out.print("Save to what file name?  ");
				String name = scan.next();
				StdAudio.save(name, array);
				System.out.print("Saving sound under file " + name);
			}
			else if(run.equals("q"))
			{
				System.out.print("Program terminated.");
			//	StdAudio.save(field, arrayOrig); //resets audio to original for testing purposes
				
			}

		}
	}

}
