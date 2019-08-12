public class FixBookControl {
	
	private FixBookUI ui; // Changed ui to ui
	private enum ControlState { INITIALISED, READY, FIXING } // ControlState to ControlState
	private ControlState state; // Changed state to state
	
	private Library library; // Class name to Library and LIB to library
	private Book curBook; // curBook to curBook


	public FixBookControl() {
		this.library = library.instance();
		state = ControlState.INITIALISED;
	}
	
	
	public void setUI(FixBookUI ui) { // Set_Ui to setUI
		if (!state.equals(ControlState.INITIALISED)) {
			throw new RuntimeException("FixBookControl: cannot call setUI except in INITIALISED state");
		}	
		this.ui = ui;
		ui.setState(FixBookUI.UiState.READY);
		state = ControlState.READY;		
	}


	public void bookScanned(int bookId) { // Book_scanned to bookScanned
		if (!state.equals(ControlState.READY)) {
			throw new RuntimeException("FixBookControl: cannot call bookScanned except in READY state");
		}	
		curBook = library.Book(bookId);
		
		if (curBook == null) {
			ui.display("Invalid bookId");
			return;
		}
		if (!curBook.IS_Damaged()) {
			ui.display("Book has not been damaged");
			return;
		}
		ui.display(curBook.toString());
		ui.setState(FixBookUI.UiState.FIXING);
		state = ControlState.FIXING;		
	}


	public void fixBook(boolean mustFix) { // FIX_Book to fixBook, MUST_fix to mustFix
		if (!state.equals(ControlState.FIXING)) {
			throw new RuntimeException("FixBookControl: cannot call fixBook except in FIXING state");
		}	
		if (mustFix) {
			library.Repair_BOOK(curBook);
		}
		curBook = null;
		ui.setState(FixBookUI.UiState.READY); // UI_STATE to UiState
		state = ControlState.READY;		
	}

	
	public void scanningComplete() { // SCannING_COMplete to scanningComplete
		if (!state.equals(ControlState.READY)) {
			throw new RuntimeException("FixBookControl: cannot call scanningComplete except in READY state");
		}	
		ui.setState(FixBookUI.UiState.COMPLETED);		
	}






}
