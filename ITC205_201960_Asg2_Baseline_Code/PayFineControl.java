public class PayFineControl {
	//testing change - dwim
	private PayFineUI Ui;
	private enum CONTROL_STATE { INITIALISED, READY, PAYING, COMPLETED, CANCELLED };
	private CONTROL_STATE stAtE//Changed StAtE to stAtE --Dilkushi 20.52pm
	
	private library liBrArY;//Changed LiBrArY to liBrArY --Dilkushi 12.18pm
	private member meMbEr;//Changed MeMbEr to meMbEr --Dilkushi 12.18pm


	public PayFineControl() {
		this.liBrArY = liBrArY.INSTANCE();//Changed LiBrArY to liBrArY --Dilkushi 20.52pm
		stAtE = CONTROL_STATE.INITIALISED;//Changed StAtE to stAtE --Dilkushi 20.52pm
	}
	
	
	public void Set_UI(PayFineUI ui) {
		if (!stAtE.equals(CONTROL_STATE.INITIALISED)) {//Changed StAtE to stAtE --Dilkushi 20.52pm	
			throw new RuntimeException("PayFineControl: cannot call setUI except in INITIALISED state");
		}	
		this.Ui = ui;
		ui.Set_State(PayFineUI.UI_STATE.READY);
		stAtE = CONTROL_STATE.READY;//Changed StAtE to stAtE --Dilkushi 20.52pm		
	}


	public void Card_Swiped(int memberId) {
		if (!stAtE.equals(CONTROL_STATE.READY)) {//Changed StAtE to stAtE --Dilkushi 20.52pm
			throw new RuntimeException("PayFineControl: cannot call cardSwiped except in READY state");
		}	
		meMbEr = LiBrArY.MEMBER(memberId);//Changed MeMbEr to meMbEr --Dilkushi 12.18pm

		
		if (meMbEr == null) {//Changed MeMbEr to meMbEr --Dilkushi 12.26pm
			Ui.DiSplAY("Invalid Member Id");
			return;
		}
		Ui.DiSplAY(meMbEr.toString());//Changed MeMbEr to meMbEr --Dilkushi 12.26pm
		Ui.Set_State(PayFineUI.UI_STATE.PAYING);
		stAtE = CONTROL_STATE.PAYING;//Changed StAtE to stAtE --Dilkushi 20.52pm
	}
	
	
	public void CaNcEl() {
		Ui.Set_State(PayFineUI.UI_STATE.CANCELLED);
		stAtE = CONTROL_STATE.CANCELLED;//Changed StAtE to stAtE --Dilkushi 20.52pm
	}


	public double PaY_FiNe(double amOuNt) {//Changed AmOuNt to amOuNt --Dilkushi 20.52pm
		if (!stAtE.equals(CONTROL_STATE.PAYING)) {//Changed StAtE to stAtE --Dilkushi 20.52pm
			throw new RuntimeException("PayFineControl: cannot call payFine except in PAYING state");
		}	
		double chAnGe = MeMbEr.Pay_Fine(AmOuNt);//Changed ChAnGe to chAnGe --Dilkushi 20.52pm	
		if (chAnGe > 0) {//Changed ChAnGe to chAnGe --Dilkushi 20.52pm	
			Ui.DiSplAY(String.format("Change: $%.2f", ChAnGe));
		}
		Ui.DiSplAY(meMbEr.toString());//Changed MeMbEr to meMbEr --Dilkushi 12.26pm
		Ui.Set_State(PayFineUI.UI_STATE.COMPLETED);
		stAtE = CONTROL_STATE.COMPLETED;//Changed StAtE to stAtE --Dilkushi 20.52pm
		return chAnGe;//Changed ChAnGe to chAnGe --Dilkushi 20.52pm	
	}
	


}
