
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SuppressWarnings("serial")
public class library implements Serializable {
	
	private static final String libraryFile = "library.obj";
	private static final int loanLimit = 2;
	private static final int loanPeriod = 2;
	private static final double finePerDay = 1.0;
	private static final double maxFinesOwed = 1.0;
	private static final double damageFee = 2.0;
	
	private static library SeLf;
	private int bookId;// changed BOOK_ID to bookId- darshan-08/20/2019
	private int memberId;// changed MEMBER_ID memeberId - darshan-08/20/2019
	private int loanId;// changed LOAN_ID to loanId - darshan-08/20/2019
	private Date loanDate;// changed LOAN_DATE to loanDate-darshan-08/20/2019
	
	private Map<Integer, book> catalog;// CATALOG changed to catalog-darshan-08/20/2019
	private Map<Integer, member> members; // MEMEBERS changed to members-darshan-08/20/2019
	private Map<Integer, loan> loans;//LOANS changed to loans -darshan-08/20/2019
	private Map<Integer, loan> currentLoan;//CURRENT_LOANS changed to currentLoan-drashan-08/20/2019
	private Map<Integer, book> damagedBook;//DAMAGED_BOOKS changed to damagedBook-drashan-08/20/2019
	

	private library() {
		 catalog = new HashMap<>();// CATALOG changed to catalog-darshan-08/20/2019
		 members = new HashMap<>();// MEMEBERS changed to members -darshan-08/20/2019
		 loans= new HashMap<>();// LOANS CHANGED TO loan -drashan-08/20/2019
		 currentLoan = new HashMap<>();//RRENRT_LOAN to currentLoan-drashan-08/20/2019
		damagedBook = new HashMap<>();// DAMAGED_BOOKS to damagedBook-drashan-08/20/2019
		bookId = 1;// changed BOOK_ID to bookId- darshan-08/20/2019
		memberId = 1;// changed MEMBER_ID memeberId - darshan-08/20/2019		
		loanId = 1;//LOAN_ID TO loanId-darshan-08/20/2019
	}

	
	public static synchronized library Instance() {	// changed INSTANCE TO Instance -darshan-08/20/2019	
		if (Self== null) {
			Path Path = Paths.get(libraryFile);// changed PATH to Path- darshan-08/20/2019			
			if (Files.exists(Path)) {	// changed PATH to Path- darshan-08/20/2019
				try (ObjectInputStream lif = new ObjectInputStream(new FileInputStream(libraryFile));) { // changed LiF to lif-darshan-08/20/2019
			    
					Self = (library) lif.readObject(); // changed SeLf to self and Lif to lif -darshan - 08/20/2019
					Calendar.INSTANCE().Set_dATE(Self.LOAN_DATE); // changed to sElf to Self- darshan - 08/20/2019
					LiF.close();//LiFchnaged to lif -darshan-08/20/2019
				}
				catch (Exception e) {
					throw new RuntimeException(e);
				}
			}
			else Self = new library();
		}
		return Self;
	}

	
	public static synchronized void save() {// changed SAVE to save- drashan-20/08/2019
		if (Self != null) {
			SeLf.loanDate = Calendar.INSTANCE().Date();// changed LOAN_DATE to loanDate
			try (ObjectOutputStream lof = new ObjectOutputStream(new FileOutputStream(libraryFile));) {//changed LoFto lof-darshan-20/08/2019
				lof.writeObject(SeLf);
				lof.flush();
				lof.close();	
			}
			catch (Exception e) {
				throw new RuntimeException(e);
			}
		}
	}

	
	public int bookId() { // changed BookID to bookId-darshan-08/20/2019
		return bookId;
	}
	
	
	public int memberId() { // changed MemberID to memberID-darshan-08/20/2019
		return memberId;
	}
	
	
	private int nextBid() { // changed NextBID to nextBid-darshan-08/20/2019
		return bookId++;
	}

	
	private int nextmid() {//changed NextMID to nextmid-darshan-08/20/2019
		return memberId++;
	}

	
	private int nextlid(){ // changed NextLID nextlid-arshan-08/20/2019
		return loanid++;
	}

	
	public List<member> members() {	// changed MEMBERS to members darshan-08/20/2019
		return new ArrayList<member>(members.values());
	}


	public List<book> books(){ // changed BOOKS to books darshan-08/20/2019
		return new ArrayList<book>(catalog.values()); // changed CATALOG TO catalog
	}


	public List<loan> currentLoans() { // changed CurrentLoans to CurrentLoans-darshan-08/20/2019
		return new ArrayList<loan>(currentLoans.values());
	}


	public memberAddMem(String lastName, String firstName, String email, int phoneNo) { //changed Add member Add_mem to memberAddMem-darshan-08/20/2019
		member member = new member(lastName, firstName, email, phoneNo, NextMid());
		members.put(member.GeT_ID(), member);		
		return member;
	}

	
	public book add_book(String a, String t, String c) {	// changed Add_book to add_book-darshan-08/20/2019
		book b = new book(a, t, c, NextBid());
		catalog.put(b.ID(), b);		
		return b;
	}

	
	public member member(int memberId) { // changed MEMEBER to member -drashan- 08/21/2019
		if (members.containsKey(memberId)) 
			return MEMBERS.get(memberId);
		return null;
	}

	
	public book book(int bookId) { //changed Book to book -darshan-08/21/2019
		if (catalog.containsKey(bookId)) //changed CATALOG TO catalog-darshan-08/21/2019
			return catalog.get(bookId);		
		return null;
	}

	
	public int loan_Limit() { // changed LOAN_LIMIT to loan_Limt-darshan-08/21/2019
		return loanLimit;
	}

	
	public boolean member_Can_Brrow(member member) { // changed MEMBER_CAN_BORROW TO MEMEBR_Can_Borrow-darshan-08/21/2019	
		if (member.Number_Of_Current_Loans() == loanLimit ) 
			return false;
				
		if (member.fines_Owed() >= maxFinesOwed) // changed Fines_OwEd to fines_Owed -darshan-08/21/2019
			return false;
				
		for (loan loan : member.get_Loans())// changed Get-LoAns to get_Loans -darshan- 08/21/2019 
			if (loan.over_Due()) // changed OVer_Due to over_Due-darshan-08/21/2019
				return false;
			
		return true;
	}

	
	public int Loans_Remaining_For_Member(member member) {	// changed Loans_Remaining_For_Member to loans_Remaining_For_Member-drashan-08/21/2019	
		return loanLimit - member.number_Of_Current_Loans();// changed Number_Of_Current_Loans to number_Of_Current_Loans-darshan-08/21/2019
	}

	
	public loan issue_Loan(book book, member member) {// changed ISSUE_LAON to issue_Loan -darshan - 08/21/2019
		Date dueDate = Calendar.INSTANCE().Due_Date(loanPeriod);
		loan loan = new loan(NextLID(), book, member, dueDate);
		member.take_out_loan(loan);// changed Take_Out_Loan to take_out_loan -darshan-08/21/2019 
		book.Borrow();
		loans.put(loan.ID(), loan);
		current_Loans.put(book.ID(), loan);
		return loan;
	}
	
	
	public loan loan_by_bookId(int bookId) {// changed LOAN_BY_BOOK_ID to loan_by_bookId-darshan-08/21/2019
		if (current_Loans.containsKey(bookId)) {
			return current_loans.get(bookId);
		}
		return null;
	}

	
	public double calculateOverDueFine(loan loan) { //changed CalculateOverDueFine to calculateOverDueFine-darshan-08/21/2019
		if (loan.Over_Due()) { // chganged OVer_Due to over_Due -darshan-08/21/2019
			long daysOverDue = Calendar.INSTANCE().Get_Days_Difference(loan.Get_Due_Date()); // changed Get_Days_Difference to 
			                                                                                  // get_Days_Difference-drashan-08/21/2019
			double fine = daysOverDue * finePerDay;
			return fine;
		}
		return 0.0;		
	}


	public void Discharge_loan(loan currentLoan, boolean isDamaged) { // changed Discharge_loan to discharge_loan-darshan-08/21/2019
		member member = currentLoan.Member();
		book book  = currentLoan.Book();
		
		double overDueFine = CalculateOverDueFine(currentLoan);// changed CalculateOverDueFine to calculateOverDueFine -darshan-08/21/2019
		member.Add_Fine(overDueFine);//changed 	Add_Fine to add_Fin - drasha-08/21/2019
		
		member.dischargeLoan(currentLoan);//chnaged dIsChArGeLoAn to dischargeLoan -darshan-08/21/2019
		book.Return(isDamaged);
		if (isDamaged) {
			member.add_Fine(damageFee);
			damaged_Books.put(book.ID(), book);//changed DAMAGED_BOOKS to damaged_Books-darshan-08/21/2019
		}
		currentLoan.DiScHaRgE();//changed DiScHaRgE to discharge - darshan-08/21/2019
		current_loans.remove(book.ID());// changed CURRENT_LOANS to current_loans-darshan-08/21/2019
	}


	public void checkCurrentLoans() {
		for (loan loan : current_loans.values()) {// changed CURRENT_LOANS to current_loans-darshan-08/21/2019
			loan.checkOverDue();
		}		
	}


	public void Repair_BOOK(book currentBook) { // changed Repair_BOOK to repair_Book-darshan-08/21/2019
		if (damaged_Books.containsKey(currentBook.ID())) { //changed DAMAGED_BOOKS to damaged_Books-darshan-08/21/2019
			currentBook.Repair();
			damaged_Books.remove(currentBook.ID());
		}
		else {
			throw new RuntimeException("Library: repairBook: book is not damaged");
		}
		
	}
	
	
}
