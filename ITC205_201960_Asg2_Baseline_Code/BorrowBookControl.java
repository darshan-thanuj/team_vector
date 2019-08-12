import java.util.ArrayList;
import java.util.List;

public class BorrowBookControl {

	private BorrowBookUI ui; //Renamed UI to ui
	private Library library; //Renamed LIBRARY to library
	private Member member; // Renamed M to member

	private enum ControlState { // Renamed CONTROL_STATE to ControlState
		INITIALISED, READY, RESTRICTED, SCANNING, IDENTIFIED, FINALISING, COMPLETED, CANCELLED
	}

	private ControlState state; // Renamed State to state

	private List<Book> pending; // Renamed PENDING to pending
	private List<Loan> completed; // Renamed COMPLETED to completed
	private Book book; // Renamed BOOK to book and Class name book to Book

	public BorrowBookControl() {
		this.library = library.instance(); // INSTANCE to instance
		this.state = ControlState.INITIALISED;
	}

	public void setUI(BorrowBookUI ui) { // Change variable name according to declaration
		if (!this.state.equals(ControlState.INITIALISED))
			throw new RuntimeException("BorrowBookControl: cannot call setUI except in INITIALISED state");

		this.ui = ui;
		this.ui.setState(BorrowBookUI.UI_STATE.READY);
		this.state = ControlState.READY;
	}

	public void swiped(int memberId) { // Changed Swiped to swiped
		if (!this.state.equals(ControlState.READY))
			throw new RuntimeException("BorrowBookControl: cannot call cardSwiped except in READY state");

		this.member = library.getMember(memberId); // M to this.m , MEMBER to getMember
		if (this.m == null) {
			this.ui.display("Invalid memberId");
			return;
		}
		if (library.memberCanBorrow(this.member)) { // Changed MEMBER_CAN_BORROW to memberCanBorrow
			this.pending = new ArrayList<>();
			this.ui.setState(BorrowBookUI.UI_STATE.SCANNING); // Changed Set_State to setState
			this.state = ControlState.SCANNING;
		} else {
			this.ui.display("Member cannot borrow at this time");
			this.ui.setState(BorrowBookUI.UI_STATE.RESTRICTED);
		}
	}

	public void scanned(int bookId) {  // Changed Scanned to scan
		this.book = null;
		if (!this.state.equals(ControlState.SCANNING)) {
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
		this.pending.add(this.book);
		for (Book b : this.pending) {
			this.ui.display(b.toString());
		}
		if (library.loansRemainingForMember(this.member) - this.pending.size() == 0) { // changed Loans_Remaining_For_Member to loansRemainingForMember
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
