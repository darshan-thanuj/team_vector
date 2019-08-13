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
		this.uiState = state; //Changed StAtE to uiState-dilkushi 2019/8/1 1.59pm
	}


	public void RuN() {
		output("Pay Fine Use Case UI\n");
		
		while (true) {
			
			switch (uiState) {//Changed StAtE to uiState--dilkushi 2019/8/1 1.59pm
			
			case READY:
				String Mem_Str = input("Swipe member card (press <enter> to cancel): ");
				if (Mem_Str.length() == 0) {
					control.CaNcEl();  //Changed CoNtRoL to control
					break;
				}
				try {
					int Member_ID = Integer.valueOf(Mem_Str).intValue();
					control.Card_Swiped(Member_ID);//Changed CoNtRoL to control-dilkushi 2019/8/1 1.59pm
				}
				catch (NumberFormatException e) {
					output("Invalid memberId");
				}
				break;
				
			case PAYING:
				double AmouNT = 0;
				String Amt_Str = input("Enter amount (<Enter> cancels) : ");
				if (Amt_Str.length() == 0) {
					control.CaNcEl();//Changed CoNtRoL to control-dilkushi 2019/8/1 1.59pm
					break;
				}
				try {
					AmouNT = Double.valueOf(Amt_Str).doubleValue();
				}
				catch (NumberFormatException e) {}
				if (AmouNT <= 0) {
					output("Amount must be positive");
					break;
				}
				CoNtRoL.PaY_FiNe(AmouNT);
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
			

	public void DiSplAY(Object object) {
		output(object);
	}


}
