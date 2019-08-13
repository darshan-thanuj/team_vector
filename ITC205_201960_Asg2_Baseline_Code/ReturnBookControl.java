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


	public void Book_Scanned(int Book_ID) {//changed Book_scanned to Book_Scanned--Dilkushi
		if (!state.equals(CONTROL_STATE.READY)) {//changed sTaTe to state--Dilkushi
			throw new RuntimeException("ReturnBookControl: cannot call bookScanned except in READY state");
		}	
		book cur_book = library_.Book(Book_ID);//changed lIbRaRy to library_--Dilkushi//changed CUR_book to cur_book--Dilkushi
		
		if (cur_book == null) {//changed CUR_book to cur_book--Dilkushi
			Ui.display("Invalid Book Id");
			return;
		}
		if (!cur_book.On_loan()) {//changed CUR_book to cur_book--Dilkushi
			Ui.display("Book has not been borrowed");
			return;
		}		
		current_loan = library_.LOAN_BY_BOOK_ID(Book_ID);//changed CurrENT_loan to current_loan--Dilkushi //changed lIbRaRy to library_--Dilkushi	
		double over_due_fine = 0.0;//changed Over_Due_Fine to over_due_fine--Dilkushi 
		if (current_loan.Over_Due()) {//changed CurrENT_loan to current_loan--Dilkushi	//changed OVer_Due to Over_Due--Dilkushi	
			over_due_fine = library_.CalculateOverDueFine(current_loan);//changed CurrENT_loan to current_loan--Dilkushi //changed lIbRaRy to library_--Dilkushi//changed Over_Due_Fine to over_due_fine--Dilkushi 	
		}
		Ui.display("Inspecting");
		Ui.display(cur_book.toString());//changed CUR_book to cur_book--Dilkushi
		Ui.display(current_loan.toString());//changed CurrENT_loan to current_loan--Dilkushi	
		
		if (current_loan.Over_Due()) {//changed CurrENT_loan to current_loan--Dilkushi	//changed OVer_Due to Over_Due--Dilkushi	
			Ui.display(String.format("\nOverdue fine : $%.2f", over_due_fine));//changed Over_Due_Fine to over_due_fine--Dilkushi 	
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


	public void Discharge_Loan(boolean isDamaged) {//changed Discharge_loan to Discharge_Loan--Dilkushi
		if (!state.equals(CONTROL_STATE.INSPECTING)) {//changed sTaTe to state--Dilkushi
			throw new RuntimeException("ReturnBookControl: cannot call dischargeLoan except in INSPECTING state");
		}	
		library_.Discharge_Loan(current_loan, isDamaged);//changed Discharge_loan to Discharge_Loan--Dilkushi //changed lIbRaRy to library_--Dilkushi
		current_loan = null;//changed CurrENT_loan to current_loan--Dilkushi	
		Ui.Set_State(ReturnBookUI.UI_STATE.READY);
		state = CONTROL_STATE.READY;	//changed sTaTe to state--Dilkushi			
	}


}
