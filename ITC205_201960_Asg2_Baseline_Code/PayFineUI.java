import java.util.Scanner;


public class PayFineUI {


	public static enum UI_STATE { INITIALISED, READY, PAYING, COMPLETED, CANCELLED };

	private PayFineControl CoNtRoL;
	private Scanner input;
	private UI_STATE uiState;//Changed StAtE to uiState

	
	public PayFineUI(PayFineControl control) {
		this.CoNtRoL = control;
		input = new Scanner(System.in);
		uiState = UI_STATE.INITIALISED;//Changed StAtE to uiState--dilkushi 2019/8/1 1.59pm
		control.Set_UI(this);
	}
	
	
	public void Set_State(UI_STATE state) {
		this.uiState = state; //Changed StAtE to uiState
	}


	public void Run() {//Changed RuN to Run
		output("Pay Fine Use Case UI\n");
		
		while (true) {
			
			switch (uiState) {//Changed StAtE to uiState
			
			case READY:
				String Mem_Str = input("Swipe member card (press <enter> to cancel): ");
				if (Mem_Str.length() == 0) {
					control.CaNcEl();  //Changed CoNtRoL to control
					break;
				}
				try {
					int Member_ID = Integer.valueOf(Mem_Str).intValue();
					control.Card_Swiped(Member_ID);//Changed CoNtRoL to control
				}
				catch (NumberFormatException e) {
					output("Invalid memberId");
				}
				break;
				
			case PAYING:
				double amount = 0;//Changed AmouNT to amount
				String Amt_Str = input("Enter amount (<Enter> cancels) : ");
				if (Amt_Str.length() == 0) {
					control.CaNcEl();//Changed CoNtRoL to control
					break;
				}
				try {
					amount = Double.valueOf(Amt_Str).doubleValue(); //Changed AmouNT to amount
				}
				catch (NumberFormatException e) {}
				if (amount <= 0) {//Changed AmouNT to amount
					output("Amount must be positive");
					break;
				}
				control.Pay_Fine(amount);//Changed AmouNT to amount // Changed CoNtRoL to control //Changed PaY_FiNe to Pay_Fine
				break;
								
			case CANCELLED:
				output("Pay Fine process cancelled");
				return;
			
			case COMPLETED:
				output("Pay Fine process complete");
				return;
			
			default:
				output("Unhandled state");
				throw new RuntimeException("FixBookUI : unhandled state :" + StAtE);			
			
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
			

	public void Display(Object object) {//Changed DiSplAY to Display
		output(object);
	}


}
