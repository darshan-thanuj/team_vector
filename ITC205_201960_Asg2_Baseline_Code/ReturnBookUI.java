import java.util.Scanner;


public class ReturnBookUI {

	public static enum UI_STATE { INITIALISED, READY, INSPECTING, COMPLETED };

	private ReturnBookControl coNtRoL;//changed CoNtRoL to coNtRoL- Dilkushi 19.24pm

	private Scanner input;
	private UI_STATE stATe;//changed StATe to stATe- Dilkushi 19.24pm

	
	public ReturnBookUI(ReturnBookControl control) {
		this.coNtRoL = control;//changed CoNtRoL to coNtRoL- Dilkushi 19.24pm
		input = new Scanner(System.in);
		stATe = UI_STATE.INITIALISED;//changed StATe to stATe- Dilkushi 19.24pm
		control.Set_UI(this);
	}


	public void RuN() {		
		output("Return Book Use Case UI\n");
		
		while (true) {
			
			switch (stATe) {//changed StATe to stATe- Dilkushi 19.24pm
			
			case INITIALISED:
				break;
				
			case READY:
				String book_STR = input("Scan Book (<enter> completes): ");//changed Book_STR to book_STR- Dilkushi 19.24pm
				if (Book_STR.length() == 0) {
					coNtRoL.Scanning_Complete();//changed CoNtRoL to coNtRoL- Dilkushi 19.24pm
				}
				else {
					try {
						int Book_Id = Integer.valueOf(book_STR).intValue();//changed Book_STR to book_STR- Dilkushi 19.24pm
						CoNtRoL.Book_scanned(Book_Id);
					}
					catch (NumberFormatException e) {
						output("Invalid bookId");
					}					
				}
				break;				
				
			case INSPECTING:
				String ans = input("Is book damaged? (Y/N): ");
				boolean Is_Damaged = false;
				if (ans.toUpperCase().equals("Y")) {					
					Is_Damaged = true;
				}
				CoNtRoL.Discharge_loan(Is_Damaged);
			
			case COMPLETED:
				output("Return processing complete");
				return;
			
			default:
				output("Unhandled state");
				throw new RuntimeException("ReturnBookUI : unhandled state :" + StATe);			
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
	
	public void Set_State(UI_STATE state) {
		this.StATe = state;
	}

	
}
