import java.util.ArrayList;
import java.util.List;

public class BorrowBookControl {

	private BorrowBookUI ui; //Renamed UI to ui  -Suchan, 08/13/19-8:00pm
	private Library library; //Renamed LIBRARY to library -Suchan, 08/13/19-8:00pm
	private Member member; // Renamed M to member -Suchan, 08/13/19-8:00pm

	private enum ControlState { // Renamed CONTROL_STATE to ControlState -Suchan, 08/13/19-8:00pm
		INITIALISED, READY, RESTRICTED, SCANNING, IDENTIFIED, FINALISING, COMPLETED, CANCELLED
	}

	private ControlState state; // Renamed State to state -Suchan, 08/13/19-8:00pm

	private List<Book> pending; // Renamed PENDING to pending -Suchan, 08/13/19-8:00pm
	private List<Loan> completed; // Renamed COMPLETED to completed -Suchan, 08/13/19-8:00pm
	private Book book; // Renamed BOOK to book and Class name book to Book -Suchan, 08/13/19-8:00pm

	public BorrowBookControl() {
		this.library = library.instance(); // INSTANCE to instance -Suchan, 08/13/19-8:00pm
		this.state = ControlState.INITIALISED;
	}

	public void setUI(BorrowBookUI ui) { // Change variable name according to declaration -Suchan, 08/13/19-8:00pm
		if (!this.state.equals(ControlState.INITIALISED))
			throw new RuntimeException("BorrowBookControl: cannot call setUI except in INITIALISED state");

		this.ui = ui;
		this.ui.setState(BorrowBookUI.UI_STATE.READY);
		this.state = ControlState.READY;
	}

	public void swiped(int memberId) { // Changed Swiped to swiped -Suchan, 08/13/19-8:00pm
		if (!this.state.equals(ControlState.READY))
			throw new RuntimeException("BorrowBookControl: cannot call cardSwiped except in READY state");

		this.member = library.getMember(memberId); // M to this.m , MEMBER to getMember -Suchan, 08/13/19-8:00pm
		if (this.m == null) {
			this.ui.display("Invalid memberId");
			return;
		}
		if (library.memberCanBorrow(this.member)) { // Changed MEMBER_CAN_BORROW to memberCanBorrow -Suchan, 08/13/19-8:00pm
			this.pending = new ArrayList<>();
			this.ui.setState(BorrowBookUI.UI_STATE.SCANNING); // Changed Set_State to setState -Suchan, 08/13/19-8:00pm
			this.state = ControlState.SCANNING;
		} else {
			this.ui.display("Member cannot borrow at this time");
			this.ui.setState(BorrowBookUI.UI_STATE.RESTRICTED);
		}
	}

	public void scanned(int bookId) {  // Changed Scanned to scan -Suchan, 08/13/19-8:00pm
		this.book = null; // Changed BOOK to this.book  -Suchan, 08/20/19-7:00pm
		if (!this.state.equals(ControlState.SCANNING)) { // Changed StaTe to this.state  -Suchan, 08/20/19-7:00pm
			throw new RuntimeException("BorrowBookControl: cannot call bookScanned except in SCANNING state");
		}
		this.book = library.Book(bookId);
		if (this.book == null) {
			this.ui.display("Invalid bookId");
			return;
		}
		if (!this.book.AVAILABLE()) {
			this.ui.display("Book cannot be borrowed");
			return;
		}
		this.pending.add(this.book); // Changed PENDING to this.pending  -Suchan, 08/20/19-8:00pm
		for (Book b : this.pending) { // Changed book class name to Book  -Suchan, 08/20/19-8:00pm
			this.ui.display(b.toString());
		}
		if (library.loansRemainingForMember(this.member) - this.pending.size() == 0) { // changed Loans_Remaining_For_Member to loansRemainingForMember -Suchan, 08/13/19-8:00pm
			this.ui.display("Loan limit reached");
			complete();
		}
	}

	public void complete() { // Changed from Complete to complete
		if (this.pending.isEmpty()) { // replaced == 0 with isEmpty() method
			cancel();
		} else {
			this.ui.Display("\nFinal Borrowing List");
			for (Book b : this.pending) {
				this.ui.display(b.toString());
			}
			this.completed = new ArrayList<Loan>();
			this.ui.setState(BorrowBookUI.UI_STATE.FINALISING);
			this.state = ControlState.FINALISING;
		}
	}

	public void commitLoans() { // Changed from CommitLoans to commitLoans
		if (!this.state.equals(ControlState.FINALISING)) {
			throw new RuntimeException("BorrowBookControl: cannot call commitLoans except in FINALISING state");
		}
		for (Book b : this.pending) {
			Loan loan = library.issueLoan(b, this.member); // Changed ISSUE_LOAN TO issueLoan
			this.completed.add(loan);
		}
		this.ui.display("Completed Loan Slip");
		for (Loan loan : this.completed) {
			this.ui.display(loan.toString());
		}
		this.ui.setState(BorrowBookUI.UI_STATE.COMPLETED);
		this.state = ControlState.COMPLETED;
	}

	public void cancel() {
		this.ui.setState(BorrowBookUI.UI_STATE.CANCELLED);
		this.state = ControlState.CANCELLED;
	}

}
