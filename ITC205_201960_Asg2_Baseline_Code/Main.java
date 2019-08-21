import java.text.SimpleDateFormat;
import java.util.Scanner;


public class Main {
	
	private static Scanner IN; //changed IN to in -darshan-08-21-2019
	private static library LIB;//changed LIB to lib- darshan -08-21-2019
	private static String MENU;// changed MENU to menu -darshan-08-21-2019
	private static Calendar CAL;//changed CAL to cal -darshan-08-21-2019
	private static SimpleDateFormat SDF; //changed SDF to sdf-darshan-08-21-2019
	
	
	private static String get_menu() { // changed Get_menu to get_menu -darshan-08/21/2019
		stringBuilder sb = new StringBuilder();  // changed StringBuilde to stringBuilder-darshan-08/21/2019
		
		sb.append("\nLibrary Main Menu\n\n")
		  .append("  M  : add member\n")
		  .append("  LM : list members\n")
		  .append("\n")
		  .append("  B  : add book\n")
		  .append("  LB : list books\n")
		  .append("  FB : fix books\n")
		  .append("\n")
		  .append("  L  : take out a loan\n")
		  .append("  R  : return a loan\n")
		  .append("  LL : list loans\n")
		  .append("\n")
		  .append("  P  : pay fine\n")
		  .append("\n")
		  .append("  T  : increment date\n")
		  .append("  Q  : quit\n")
		  .append("\n")
		  .append("Choice : ");
		  
		return sb.toString();
	}


	public static void main(String[] args) {		
		try {			
			in = new Scanner(System.in); //cahanged IN to in -drahan-08/21/019
			lib = library.INSTANCE();//cahanged LIB to lib -drahan-08/21/019
			cal = Calendar.INSTANCE();//cahanged CAL to cal -drahan-08/21/019
			sdf = new SimpleDateFormat("dd/MM/yyyy");//cahanged SDF to sdf -drahan-08/21/019
	
			for (member m : lib.MEMBERS()) { //cahanged LIB to lib -drahan-08/21/019
				output(m);
			}
			output(" ");
			for (book b : lib.BOOKS()) {//cahanged LIB to lib -drahan-08/21/019
				output(b);
			}
						
			menu = Get_menu();//cahanged MENU to menu -drahan-08/21/019
			
			boolean e = false;
			
			while (!e) {
				
				output("\n" + sdf.format(CAL.Date()));//cahanged SDF to sdf -darhan-08/21/2019
				String c = input(MENU);
				
				switch (c.toUpperCase()) {
				
				case "M": 
					add_member();// changed ADD_MEMBER to add_member-drashan-08/21/2019
					break;
					
				case "LM": 
					memebrs();// changed MEMEBERS to members-darshan-08/21/2019
					break;
					
				case "B": 
					add_Book();// changed ADD_book to add_Book-darshan-08/21/2019
					break;
					
				case "LB": 
					books();// changed BOOKS to books-darshan-08/21/2019
					break;
					
				case "FB": 
					FIX_BOOKS();// changed FIX_BOOKS to fix_Books-darshan-08/21/2019
					break;
					
				case "L": 
					BORROW_BOOK();// changed BORROW_BOOK to borrow_Book-darshan-08/21/2019
					break;
					
				case "R": 
					Return_Book();// changed RETURN_BOOK to return_Book-darshan-08/21/2019
					break;
					
				case "LL": 
					current_loan();//// changed CURRENT_LOAN to current_loan-darshan-08/21/2019
					break;
					
				case "P": 
					fines();// changed FINES to fines-darshan-08/21/2019
					break;
					
				case "T": 
					increment_Date();// changed INCREMENT_DATE to increment_Date-darshan-08/21/2019
					break;
					
				case "Q": 
					e = true;
					break;
					
				default: 
					output("\nInvalid option\n");
					break;
				}
				
				library.SAVE();
			}			
		} catch (RuntimeException e) {
			output(e);
		}		
		output("\nEnded\n");
	}	

	
	private static void fines() { //// changed FINES to fines-darshan-08/21/2019
		new PayFineUI(new PayFineControl()).RuN();		
	}


	private static void current_Loans() { // changed CURRENT_LOANS to current_Loans-darshan-08/21/2019
		output("");
		for (loan loan : lib.CurrentLoans()) {
			output(loan + "\n");
		}		
	}



	private static void books() { // changed BOOKS to books-darshan-08/21/2019
		output("");
		for (book book : lib.BOOKS()) {
			output(book + "\n");
		}		
	}



	private static void members() { // changed MEMEBERS to members-darshan-08/21/2019
		output("");
		for (member member : lib.memebrs()) {
			output(member + "\n");
		}		
	}



	private static void borrow_Books() { // changed BORROW_BOOKS to borrow_Books-darshan-08/21/2019
		new BorrowBookUI(new BorrowBookControl()).run();		
	}


	private static void return_Book() { // changed RETURN_BOOK to return_Book-darshan-08/21/2019
		new ReturnBookUI(new ReturnBookControl()).RuN();		
	}


	private static void fix_Books() { // changed FIX_BOOKS to fix_Books-darshan-08/21/2019
		new FixBookUI(new FixBookControl()).RuN();		
	}


	private static void increment_Date() { // changed INCREMENT_DATE to increment_Date-darshan-08/21/2019
		try {
			int days = Integer.valueOf(input("Enter number of days: ")).intValue();
			CAL.incrementDate(days);
			LIB.checkCurrentLoans();
			output(SDF.format(CAL.Date()));
			
		} catch (NumberFormatException e) {
			 output("\nInvalid number of days\n");
		}
	}


	private static void add_Book() { // changed ADD_BOOK to add_Book-darshan-08/21/2019
		
		String a = input("Enter author: ");// changed A to a-darshan-08/21/2019
		String t  = input("Enter title: ");// changed T to t-darshan-08/21/2019
		String c = input("Enter call number: ");// changed C to c-darshan-08/21/2019
		book B = LIB.Add_book(A, T, C); // changed B to b-darshan-08/21/2019
		output("\n" + B + "\n");
		
	}

	
	private static void add_Memebrs() { // // changed ADD_MEMBERS to add_Members-darshan-08/21/2019
		try {
			String ln = input("Enter last name: "); // changed LN to ln-darshan-08/21/2019
			String fn  = input("Enter first name: ");// changed FN to fn-darshan-08/21/2019
			String em = input("Enter email: ");// changed EM to em-darshan-08/21/2019
			int pn = Integer.valueOf(input("Enter phone number: ")).intValue();// changed PN to pn-darshan-08/21/2019
			member m = LIB.Add_mem(ln, fn, em, pn);// changed M to m-darshan-08/21/2019
			output("\n" + M + "\n");
			
		} catch (NumberFormatException e) {
			 output("\nInvalid phone number\n");
		}
		
	}


	private static String input(String prompt) {
		System.out.print(prompt);
		return IN.nextLine();
	}
	
	
	
	private static void output(Object object) {
		System.out.println(object);
	}

	
}
