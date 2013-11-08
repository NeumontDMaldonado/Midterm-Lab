/**
 * 
 */
package maldonado.Danny_MidtermLab;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author dmaldonado
 *
 */
public class Startup 
{
	public  class CompareContactName implements Comparator<Contact>
	{
		@Override
		public int compare(Contact c1, Contact c2)
		{
			if(c1 == null || c2 == null || c1.getClass() != c2.getClass())
			{
				return -1;
			}

			return c1.name.compareTo(c2.name);
		}
	}

	public  class CompareContactPhone implements Comparator<Contact>
	{
		@Override
		public int compare(Contact c1, Contact c2)
		{
			if(c1 == null || c2 == null || c1.getClass() != c2.getClass())
			{
				return -1;
			}

			return c1.phoneNr.compareTo(c2.phoneNr);
		}
	}

	public  class CompareContactBirth implements Comparator<Contact>
	{
		@Override
		public int compare(Contact c1, Contact c2)
		{
			if(c1 == null || c2 == null || c1.getClass() != c2.getClass())
			{
				return -1;
			}

			return c1.birthDate.compareTo(c2.birthDate);
		}
	}

	public class Contact implements Comparable<Contact>
	{
		String name;
		String phoneNr;
		String birthDate;

		public Contact(String name, String phoneNr, String birthDate) 
		{
			this.name = name;
			this.phoneNr = phoneNr;
			this.birthDate = birthDate;
		}
		/* (non-Javadoc)
		 * @see java.lang.Object#hashCode()
		 */
		@Override
		public int hashCode() 
		{
			final int prime = 31;
			int result = 1;
			result = prime * result + getOuterType().hashCode();
			result = prime * result
					+ ((birthDate == null) ? 0 : birthDate.hashCode());
			result = prime * result + ((name == null) ? 0 : name.hashCode());
			result = prime * result
					+ ((phoneNr == null) ? 0 : phoneNr.hashCode());
			return result;
		}
		/* (non-Javadoc)
		 * @see java.lang.Object#equals(java.lang.Object)
		 */
		@Override
		public boolean equals(Object obj) 
		{
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			Contact other = (Contact) obj;
			if (!getOuterType().equals(other.getOuterType()))
				return false;
			if (birthDate == null) {
				if (other.birthDate != null)
					return false;
			} else if (!birthDate.equals(other.birthDate))
				return false;
			if (name == null) {
				if (other.name != null)
					return false;
			} else if (!name.equals(other.name))
				return false;
			if (phoneNr == null) {
				if (other.phoneNr != null)
					return false;
			} else if (!phoneNr.equals(other.phoneNr))
				return false;
			return true;
		}

		private Startup getOuterType() 
		{
			return Startup.this;
		}

		@Override
		public int compareTo(Contact o) {
			// TODO Auto-generated method stub
			return 0;
		}
	}
	/**
	 * @param args
	 * @throws Exception 
	 */
	public static void main(String[] args)
	{
		Startup start = new Startup();
	}

	ArrayList<Contact> list;
	public Startup()
	{
		list = new ArrayList<>();
		FileReader file = null;
		try 
		{
			file = new FileReader("Contacts.csv");
		} 
		catch (FileNotFoundException e) 
		{
			e.printStackTrace();
		}
		BufferedReader buff = new BufferedReader(file);

		String line = "";
		try {
			while(buff.ready())
			{
				line = buff.readLine();
				String[] contact = line.split(",");
				Contact c = new Contact(contact[0],contact[1],contact[2]);
				list.add(c);
			}
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		}

		menu();
	}

	public void menu()
	{
		Scanner scan = new Scanner(System.in);
		System.out.println("What do you want to do?\n1)Print persons sorted by Name\n2)Print persons sorted by Phone Number\n3)Print persons sorted by Birth Date\n4)Get a single person by Name\n5)Quit");
		switch(scan.nextInt())
		{
		case 1:
		{
			sortName();
			break;
		}
		case 2:
		{
			sortPhone();
			break;
		}
		case 3:
		{
			sortBirth();
			break;
		}
		case 4:
		{
			singlePerson();
			break;
		}
		case 5:
		{
			scan.close();
			quit();
		}
		}
	}
	public  void quit()
	{
		System.out.println("Goodbye.");
	}

	public  void singlePerson()
	{
		Scanner scan = new Scanner(System.in);
		FileReader input = null;
		try 
		{
			input = new FileReader("Contacts.csv");
		} 
		catch (FileNotFoundException e1) 
		{
			e1.printStackTrace();
		}
		BufferedReader buffer = new BufferedReader(input);
		String line = "";
		System.out.println("Enter the full name of the contact");
		try {
			while(buffer.ready())
			{
				line = buffer.readLine();
				String name = scan.nextLine();
				name.toUpperCase();
				System.out.println(name);
				Pattern pattern = Pattern.compile("(["+name+"]),[0-9]+-[0-9]+-[0-9]+,[0-9]{1,2}[/][0-9]{1,2}[/][0-9]{4}");
				Matcher matcher = pattern.matcher(line);
				if(matcher.find())
				{
					System.out.println(line);
				}
			}
		} 
		catch (IOException e1) 
		{
			e1.printStackTrace();
		}
		try 
		{
			menu();
		} 
		catch (Exception e) 
		{
			System.err.println("Error has occurred");
		}
		finally
		{
			scan.close();
			try 
			{
				buffer.close();
			} 
			catch (IOException e) 
			{
				e.printStackTrace();
			}
		}
	}

	public void sortName()
	{
		Collections.sort(list, new CompareContactName());

		for(Contact c: list)
		{
			System.out.println(c.name);
		}
		menu();
	}

	public  void sortPhone()
	{
		Collections.sort(list, new CompareContactPhone());
		
		for(Contact c: list)
		{
			System.out.println(c.phoneNr);
		}
		menu();
	}

	public  void sortBirth()
	{
		Collections.sort(list, new CompareContactBirth());

		for(Contact c: list)
		{
			System.out.println(c.birthDate);
		}
		menu();
	}
}