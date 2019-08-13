import java.util.Scanner;

public class BorrowBookUI {

	public enum UiState { // Removed redundant static qualifier -Suchan, 08/13/19-8:30pm
		INITIALISED, READY, RESTRICTED, SCANNING, IDENTIFIED, FINALISING, COMPLETED, CANCELLED
	}

	private BorrowBookControl control;
	private Scanner input;
	private UiState state; // Renamed StaTe to state -Suchan, 08/13/19-8:30pm

	public BorrowBookUI(BorrowBookControl control) {
		this.control = control;
		this.input = new Scanner(System.in);
		this.state = UI_STATE.INITIALISED;
		this.control.setUI(this);
	}

	private String input(String prompt) {
		System.out.print(prompt);
		return this.input.nextLine();
	}

	private void output(Object object) {
		System.out.println(object);
	}

	public void setState(UI_STATE state) {
		this.state = state;
	}

	public void run() {
		output("Borrow Book Use Case UI\n");

		while (true) {

			switch (state) {

			case CANCELLED:
				output("Borrowing Cancelled");
				return;

			case READY:
				String memberString = input("Swipe member card (press <enter> to cancel): "); // Changed MEM_STR to memberString -Suchan, 08/13/19-8:30pm
				if (memberString.length() == 0) {
					control.cancel();
					break;
				}
				try {
					int memberId = Integer.valueOf(memberString).intValue(); // Changed Member_Id to memberId -Suchan, 08/13/19-8:30pm
					control.swiped(memberId);
				} catch (NumberFormatException e) {
					output("Invalid Member Id");
				}
				break;

			case RESTRICTED:
				input("Press <any key> to cancel");
				control.cancel();
				break;

			case SCANNING:
				String bookString = input("Scan Book (<enter> completes): "); // Changed Book_Str to bookString -Suchan, 08/13/19-8:30pm
				if (bookString.length() == 0) {
					control.complete();
					break;
				}
				try {
					int bookId = Integer.parseInt(bookString); // Changed BiD to bookId,  Used "Integer.parseInt" for this string-to-int conversion -Suchan, 08/13/19-8:30pm
					control.scanned(bookId);

				} catch (NumberFormatException e) {
					output("Invalid Book Id");
				}
				break;

			case FINALISING:
				String answer = input("Commit loans? (Y/N): "); // Changed Ans to answer -Suchan, 08/13/19-8:30pm
				if (answer.equalsIgnoreCase("N")) { // Replace these toUpperCase() and equals() calls with a single equalsIgnoreCase() -Suchan, 08/13/19-8:30pm
					control.cancel();

				} else {
					control.commitLoans(); // Changed Commit_LOans to commitLoans -Suchan, 08/13/19-8:30pm
					input("Press <any key> to complete ");
				}
				break;

			case COMPLETED:
				output("Borrowing Completed");
				return;

			default:
				output("Unhandled state");
				throw new RuntimeException("BorrowBookUI : unhandled state :" + state);
			}
		}
	}

	public void display(Object object) { // Changed Display to display -Suchan, 08/13/19-8:30pm
		output(object);
	}

}
