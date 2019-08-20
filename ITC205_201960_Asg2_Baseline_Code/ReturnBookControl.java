public class ReturnBookControl {

	private ReturnBookUI ui;//Changed Ui to ui Dilkushi 10.52pm
	private enum CONTROL_STATE { INITIALISED, READY, INSPECTING };
	private CONTROL_STATE sTaTe;
	
	private library lIbRaRy;
	private loan currENT_loan;//Changed CurrENT_loan to currENT_loan Dilkushi 10.52pm
	

	public ReturnBookControl() {
		this.lIbRaRy = lIbRaRy.INSTANCE();
		sTaTe = CONTROL_STATE.INITIALISED;
	}
	
	
	public void set_UI(ReturnBookUI ui) {//Changed Set_UI to set_UI Dilkushi 10.52pm
		if (!sTaTe.equals(CONTROL_STATE.INITIALISED)) {
			throw new RuntimeException("ReturnBookControl: cannot call setUI except in INITIALISED state");
		}	
		this.ui = ui;//Changed Ui to ui Dilkushi 10.52pm
		ui.set_State(ReturnBookUI.UI_STATE.READY);//Changed Set_UI to set_UI Dilkushi 10.52pm
		sTaTe = CONTROL_STATE.READY;		
	}


	public void book_scanned(int Book_ID) {//Changed Book_scanned to book_scanned Dilkushi 10.52pm
		if (!sTaTe.equals(CONTROL_STATE.READY)) {
			throw new RuntimeException("ReturnBookControl: cannot call bookScanned except in READY state");
		}	
		book cUR_book = lIbRaRy.Book(Book_ID);//Changed cUR_book to CUR_book Dilkushi 10.52pm
		
		if (cUR_book == null) {//Changed cUR_book to CUR_book Dilkushi 10.52pm
			Ui.display("Invalid Book Id");
			return;
		}
		if (!cUR_book.On_loan()) {//Changed cUR_book to CUR_book Dilkushi 10.52pm
			Ui.display("Book has not been borrowed");
			return;
		}		
		currENT_loan = lIbRaRy.LOAN_BY_BOOK_ID(Book_ID);//Changed CurrENT_loan to currENT_loan Dilkushi 10.52pm	
		double over_Due_Fine = 0.0;//Changed Over_Due_Fine to over_Due_Fine Dilkushi 10.52pm	
		if (currENT_loan.OVer_Due()) {//Changed CurrENT_loan to currENT_loan Dilkushi 10.52pm
			Over_Due_Fine = lIbRaRy.CalculateOverDueFine(CurrENT_loan);
		}
		ui.display("Inspecting");//Changed Ui to ui Dilkushi 10.52pm
		ui.display(CUR_book.toString());//Changed Ui to ui Dilkushi 10.52pm
		ui.display(CurrENT_loan.toString());//Changed Ui to ui Dilkushi 10.52pm
		
		if (CurrENT_loan.OVer_Due()) {
			Ui.display(String.format("\nOverdue fine : $%.2f", Over_Due_Fine));
		}
		Ui.Set_State(ReturnBookUI.UI_STATE.INSPECTING);
		sTaTe = CONTROL_STATE.INSPECTING;		
	}


	public void Scanning_Complete() {
		if (!sTaTe.equals(CONTROL_STATE.READY)) {
			throw new RuntimeException("ReturnBookControl: cannot call scanningComplete except in READY state");
		}	
		Ui.Set_State(ReturnBookUI.UI_STATE.COMPLETED);		
	}


	public void Discharge_loan(boolean isDamaged) {
		if (!sTaTe.equals(CONTROL_STATE.INSPECTING)) {
			throw new RuntimeException("ReturnBookControl: cannot call dischargeLoan except in INSPECTING state");
		}	
		lIbRaRy.Discharge_loan(CurrENT_loan, isDamaged);
		CurrENT_loan = null;
		Ui.Set_State(ReturnBookUI.UI_STATE.READY);
		sTaTe = CONTROL_STATE.READY;				
	}


}
