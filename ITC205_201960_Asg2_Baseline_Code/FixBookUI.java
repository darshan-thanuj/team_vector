import java.util.Scanner;


public class FixBookUI {

	public static enum UI_STATE { INITIALISED, READY, FIXING, COMPLETED };

	private FixBookControl coNtRoL; // changed 'CoNtrol' to 'coNtRol' - darshan, 08/13/19-7:23pm
	private Scanner input;
	private UI_STATE stAtE;// changed 'StAtE' to 'stAtE'-darshan,08/15/19-5.03pm

	
	public fixBookUI(FixBookControl control) { // changed 'FixBookUI' to 'fixBookUI'-darshan, 08/15/19-5:27pm
		this.coNtRoL = control;//changed 'CoNtrol' to 'coNtRol' - darshan, 08/15/19-5:14pm
		input = new Scanner(System.in);
		stAtE = UI_STATE.INITIALISED;//changed 'StAtE' to 'stAtE' -darshan, 08/15/19-5:16pm 
		control.Set_Ui(this);
	}


	public void set_State(UI_STATE state) {
		this.stAtE = state;//changed 'StAtE' to 'stAtE' -darshan, 08/15/19-5:20pm 
	}

	
	public void ruN() { // changed 'RuN' to 'ruN' -darshan,08/15/19-5:31pm
		output("Fix Book Use Case UI\n");
		
		while (true) {
			
			switch (stAtE) {// changed 'StAte' to 'stAtE' - darshan, 08/15/19 -5:33 pm
			
				case rEADY://changed 'READY' to 'rEADY' -darshan,08/15/19 -5:34 pm
				String book_STR = input("Scan Book (<enter> completes): ");// changed 'Book_STR' to 'book_STR'-darshan,08/15/19-5:35 pm
				if (book_STR.length() == 0) { // changed 'Book_STR' to 'book_STR' -darshan,08/15/19 -5:36pm
					CoNtRoL.SCannING_COMplete();
				}
				else {
					try {
						int book_ID = Integer.valueOf(book_STR).intValue();// changed 'Book_ID' to 'book_ID' and 'Book_STR' 
						                                                      //to 'book_STR' - darshan -5:40pm
						CoNtRoL.Book_scanned(book_ID); // changed 'Book_ID' to 'book_ID' -darshan,08/15/19-5:43pm 
					}
					catch (NumberFormatException e) {
						output("Invalid bookId");
					}
				}
				break;	
				
				case fIXING: // changed 'FIXING' TO 'fIXING'-darshan,08/15/19-5:45pm
				String anS = input("Fix Book? (Y/N) : ");//changed 'AnS' to 'anS' - darshan,08/15/19-5:47pm
				boolean fiX = false; //changed 'FiX' to 'fiX' -darshan,08/15/19 - 5:48 pm
				if (anS.toUpperCase().equals("Y")) { //changed 'AnS' to 'anS' - darshan,08/15/19-5:48pm
					fiX = true;//changed 'FiX' to 'fiX' -darshan,08/15/19 - 5:49 pm
				}
				CoNtRoL.fIX_Book(FiX);// changed 'FIX_Book' to 'fIX_Book' -darshan,08/15/19-5:54pm
				break;
								
				case cOMPLETED://changed 'COMPLETED' to 'cOMPLETED' -darshan,08/15/19-5:56pm
				output("Fixing process complete");
				return;
			
			default:
				output("Unhandled state");
				throw new RuntimeException("FixBookUI : unhandled state :" + stAtE);//changed 'StAtE' to 'stAtE'- darshan,08/15/19-5:57pm			
			
			}		
		}
		
	}

	
	private String input(String prompt) {
		System.out.print(prompt);
		return input.nextLine();
	}	
		
		
	private void output(Object object) {
		System.out.println(object);
	}
	

	public void display(Object object) {
		output(object);
	}
	
	
}
