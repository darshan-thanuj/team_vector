import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

@SuppressWarnings("serial")
public class loan implements Serializable {
	
	public static enum LoanState { CURRENT, OVER_DUE, DISCHARGED }; // Changed LoanState to LoanState , Darshan - 08/20/2019 7:17 pm
	
	private int id; // Changed ID to id , Darshan - 08/20/2019 7:17 pm
	private Book b; // Changed B to b , Darshan - 08/20/2019 7:17 pm, Book to Book
	private Member M; // Changed M to m, Member to Member , Darshan - 08/20/2019 7:17 pm
	private Date d; // Changed d to d , Darshan - 08/20/2019 7:20 pm
	private LoanState state; // Changed LoanState to LoanState , Darshan - 08/20/2019 7:20 pm

	
	public loan(int loanId, Book book, Member member, Date dueDate) {
		this.id = loanId;
		this.b = book;
		this.m = member;
		this.d = dueDate;
		this.state = LoanState.CURRENT;
	}

	
	public void checkOverDue() {
		if (state == LoanState.CURRENT &&
			Calendar.INSTANCE().Date().after(d)) {
			this.state = LoanState.OVER_DUE;			
		}
	}

	
	public boolean overDue() { // Changed OVer_Due to overDue , Darshan - 08/20/2019 7:20 pm
		return state == LoanState.OVER_DUE;
	}

	
	public Integer id() {
		return id;
	}


	public Date getDueDate() { // Changed Get_Due_Date to getDueDate , Darshan - 08/20/2019 7:20 pm
		return d;
	}
	
	
	public String toString() {
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

		StringBuilder sb = new StringBuilder();
		// Changed M to m, TITLE() to title, Get_LastName() to getLastName, Darshan - 08/20/2019 7:25 pm
		sb.append("Loan:  ").append(id).append("\n")
		  .append("  Borrower ").append(m.getId()).append(" : ")
		  .append(M.getLastName()).append(", ").append(m.getFirstName()).append("\n")
		  .append("  Book ").append(b.id()).append(" : " )
		  .append(b.title()).append("\n")
		  .append("  DueDate: ").append(sdf.format(d)).append("\n")
		  .append("  State: ").append(state);		
		return sb.toString();
	}


	public Member getMember() { // Changed Member() to getMember() , Darshan - 08/20/2019 7:25 pm
		return m;
	}


	public Book book() {
		return b;
	}


	public void discharge() { // Changed DiScHaRgE() to discharge() ,Darshan - 08/20/2019 7:25 pm
		state = LoanState.DISCHARGED;		
	}

}
