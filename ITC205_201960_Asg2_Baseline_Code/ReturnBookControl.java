public class ReturnBookControl {

	private ReturnBookUI Ui;
	private enum CONTROL_STATE { INITIALISED, READY, INSPECTING };
	private CONTROL_STATE state;//changed sTaTe to state-Dilkushi
	
	private library library_;//changed lIbRaRy to library_--Dilkushi
	private loan current_loan;//changed CurrENT_loan to current_loan--Dilkushi
	

	public ReturnBookControl() {
		this.library_ = library_.INSTANCE();//changed lIbRaRy to library_--Dilkushi
		state = CONTROL_STATE.INITIALISED;//changed sTaTe to state--Dilkushi
	}
	
	
	public void Set_UI(ReturnBookUI ui) {
		if (!state.equals(CONTROL_STATE.INITIALISED)) {//changed sTaTe to state--Dilkushi
			throw new RuntimeException("ReturnBookControl: cannot call setUI except in INITIALISED state");
		}	
		this.Ui = ui;
		ui.Set_State(ReturnBookUI.UI_STATE.READY);
		state = CONTROL_STATE.READY;//changed sTaTe to state--Dilkushi		
	}


	public void Book_scanned(int Book_ID) {
		if (!state.equals(CONTROL_STATE.READY)) {//changed sTaTe to state--Dilkushi
			throw new RuntimeException("ReturnBookControl: cannot call bookScanned except in READY state");
		}	
		book CUR_book = library_.Book(Book_ID);//changed lIbRaRy to library_--Dilkushi
		
		if (CUR_book == null) {
			Ui.display("Invalid Book Id");
			return;
		}
		if (!CUR_book.On_loan()) {
			Ui.display("Book has not been borrowed");
			return;
		}		
		current_loan = library_.LOAN_BY_BOOK_ID(Book_ID);//changed CurrENT_loan to current_loan--Dilkushi //changed lIbRaRy to library_--Dilkushi	
		double Over_Due_Fine = 0.0;
		if (current_loan.OVer_Due()) {//changed CurrENT_loan to current_loan--Dilkushi	
			Over_Due_Fine = library_.CalculateOverDueFine(current_loan);//changed CurrENT_loan to current_loan--Dilkushi //changed lIbRaRy to library_--Dilkushi	
		}
		Ui.display("Inspecting");
		Ui.display(CUR_book.toString());
		Ui.display(current_loan.toString());//changed CurrENT_loan to current_loan--Dilkushi	
		
		if (current_loan.OVer_Due()) {//changed CurrENT_loan to current_loan--Dilkushi	
			Ui.display(String.format("\nOverdue fine : $%.2f", Over_Due_Fine));
		}
		Ui.Set_State(ReturnBookUI.UI_STATE.INSPECTING);
		state = CONTROL_STATE.INSPECTING;//changed sTaTe to state--Dilkushi		
	}


	public void Scanning_Complete() {
		if (!state.equals(CONTROL_STATE.READY)) {//changed sTaTe to state--Dilkushi
			throw new RuntimeException("ReturnBookControl: cannot call scanningComplete except in READY state");
		}	
		Ui.Set_State(ReturnBookUI.UI_STATE.COMPLETED);		
	}


	public void Discharge_loan(boolean isDamaged) {
		if (!state.equals(CONTROL_STATE.INSPECTING)) {//changed sTaTe to state--Dilkushi
			throw new RuntimeException("ReturnBookControl: cannot call dischargeLoan except in INSPECTING state");
		}	
		lIbRaRy.Discharge_loan(current_loan, isDamaged);
		current_loan = null;//changed CurrENT_loan to current_loan--Dilkushi	
		Ui.Set_State(ReturnBookUI.UI_STATE.READY);
		state = CONTROL_STATE.READY;	//changed sTaTe to state--Dilkushi			
	}


}
