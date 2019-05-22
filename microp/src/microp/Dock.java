package microp;
import java.io.Serializable;
public class Dock implements Serializable{
	
	//boolean[] latch= {true,true,true,true,true,false,false,false};
	boolean[] latch= {true,true,true,true,true,false,false,false};
	int i;
	public String dockId;
	
	public int releaseScooter()/*turn one latch to false if there is a latch has scooter(in order),return the position of latch */
	{
		int position=1;
		
		try {
			while(position<=8)
			{
				
				if(latch[position-1]==true)
				{
					latch[position-1]=false;
					break;
				}
				position++;
				
			}
		}
		
		catch(Exception e)
		{
			System.out.println("error in release");
			return -1;
		}
		return position;
		
	}
	
	public int returnPositionPick()
	{
		int position=1;
		
		try {
			while(position<=8)
			{
				
				if(latch[position-1]==true)
				{
					return position;
				}
				position++;
				
			}
		}
		catch(Exception e)
		{
			System.out.println("error in release");
			return -1;
		
		}
		return position;
	}
		
	
		public int returnPositionReturn()
		{
			int position=1;
			
			try {
				while(position<=8)
				{
					
					if(latch[position-1]==false)
					{
						return position;
					}
					position++;
					
				}
			}
		
		catch(Exception e)
		{
			System.out.println("error in release");
			return -1;
		}
		return position;
	}
		
		
	public int retrieveScooter()/*turn one latch to true if there is a empty latch, return the postion of the latchf*/
	{
		int position=1;
		try {
			while(position<=8)
			{
				if(latch[position-1]==false)
				{
					latch[position-1]=true;
					break;
				}
				position++;
			}
		}
		catch(Exception e)
		{
			System.out.println("error in retrieve");
			return -1;
		}
		
		
		return position;
	}
	
	public void scan()
	{
		
	}
	public boolean isEmpty()/*if empty return false, else return true*/
	{
		int count=0;
		for(i=0;i<8;i++)
		{
			if(latch[i]==false)
			{
				count++;
			}
		}
		if(count==8)
		{
			return true;
		}
		else if(count<8&&count>=0)
		{
			return false;
		}
		return false;
	}
	
	public boolean isFull()/*if full, return true, else return false*/
	{
		int count=0;
		for(i=0;i<8;i++)
		{
			if(latch[i]==true)
			{
				count++;
			}
		}
		if(count==8)
		{
			return true;
		}
		else if(count<8)
		{
			return false;
		}
		return false;
	}
	public int numOflatch()
	{
		return latch.length;
	}
	public int numOfSco()
	{
		int count=0;
		for(int i=0;i<8;i++)
		{
			if(latch[i]==true)
			{
				count++;
			}
		}
		return count;
	}
	
	public static void main(String[] args)
	{
		Dock a = new Dock();
		System.out.println(a.isEmpty()+""+a.isFull()+""+a.releaseScooter());
	}
}



