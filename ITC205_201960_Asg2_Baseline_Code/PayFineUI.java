import java.util.Scanner;


public class PayFineUI {


	public static enum UI_STATE { INITIALISED, READY, PAYING, COMPLETED, CANCELLED };

	private PayFineControl coNtRoL;//Changed CoNtRoL to coNtRoL -Dilkushi at 21.19pm
	private Scanner input;
	private UI_STATE stAtE;//Changed StAtE to stAtE -Dilkushi at 21.19pm

	
	public payFineUI(PayFineControl control) {//Changed PayFineUI to payFineUI -Dilkushi at 21.19pm
		this.coNtRoL = control;//Changed CoNtRoL to coNtRoL -Dilkushi at 21.19pm
		input = new Scanner(System.in);
		stAtE = UI_STATE.INITIALISED;//Changed StAtE to stAtE -Dilkushi at 21.19pm
		control.Set_UI(this);
	}
	
	
	public void set_State(UI_STATE state) {//Changed Set_State to set_State -Dilkushi at 21.19pm
		this.stAtE = state;//Changed StAtE to stAtE -Dilkushi at 21.19pm
	}


	public void RuN() {//Changed RuN to ruN -Dilkushi at 21.19pm
		output("Pay Fine Use Case UI\n");
		
		while (true) {
			
			switch (stAtE) {//Changed StAtE to stAtE -Dilkushi at 21.19pm
			
			case READY:
				String Mem_Str = input("Swipe member card (press <enter> to cancel): ");
				if (Mem_Str.length() == 0) {
					coNtRoL.CaNcEl();//Changed CoNtRoL to coNtRoL -Dilkushi at 21.19pm
					break;
				}
				try {
					int Member_ID = Integer.valueOf(Mem_Str).intValue();
					coNtRoL.Card_Swiped(Member_ID);//Changed CoNtRoL to coNtRoL -Dilkushi at 21.19pm
				}
				catch (NumberFormatException e) {
					output("Invalid memberId");
				}
				break;
				
			case PAYING:
				double amouNT = 0;//Changed AmouNT to amouNT -Dilkushi at 21.19pm
				String amt_Str = input("Enter amount (<Enter> cancels) : ");//Changed amt_Str to Amt_Str -Dilkushi at 21.19pm
				if (Amt_Str.length() == 0) {
					coNtRoL.CaNcEl();//Changed CoNtRoL to coNtRoL -Dilkushi at 21.19pm
					break;
				}
				try {
					amouNT = Double.valueOf(Amt_Str).doubleValue();//Changed AmouNT to amouNT -Dilkushi at 21.19pm
				}
				catch (NumberFormatException e) {}
				if (amouNT <= 0) {//Changed AmouNT to amouNT -Dilkushi at 21.19pm
					output("Amount must be positive");
					break;
				}
				coNtRoL.PaY_FiNe(AmouNT);//Changed CoNtRoL to coNtRoL -Dilkushi at 21.19pm
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
			

	public void DiSplAY(Object object) {//Changed DiSplAY to diSplAY -Dilkushi at 21.19pm
		output(object);
	}


}
