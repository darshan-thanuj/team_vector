import java.io.Serializable;

@SuppressWarnings("serial")
public class Book implements Serializable {
    private String title; // Changed from TITLE to title  -Suchan, 08/13/19-8:00pm
    private String author; // Changed from AUTHOR to author  -Suchan, 08/13/19-8:00pm
    private String callNo; // Changed from CALL_NO to callNo  -Suchan, 08/13/19-8:00pm
    private int id;

    private enum State { // Changed from StaTe to State  -Suchan, 08/13/19-8:00pm
        AVAILABLE, ON_LOAN, DAMAGED, RESERVED
    };

    private State state;

    // Class arg constructor
    public Book(String author, String title, String callNo, int id, State state) {
        this.author = author;
        this.title = title;
        this.callNo = callNo;
        this.id = id;
        this.state = state;
    }

    // Overide toString method
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Book: ").append(id).append("\n").append("Title:  ").append(title).append("\n").append("Author: ")
                .append(author).append("\n").append("CallNo: ").append(callNo).append("\n").append("State:  ")
                .append(state);

        return sb.toString();
    }

    // Getter and Setter methods
    public Integer getId() {
        return this.id;
    }

    public String getTitle() {
        return this.title;
    }

    public boolean isAvailable() {
        return state == State.AVAILABLE;
    }

    public boolean onloan() {
        return state == State.ON_LOAN;
    }

    public boolean IS_Damaged() {
        return state == State.DAMAGED;
    }

    // Check if the book can be borrowed
    public void Borrow() {
        if (state.equals(State.AVAILABLE)) {
            state = State.ON_LOAN;
        } else {
            throw new RuntimeException(String.format("Book: cannot borrow while book is in state: %s", state));
        }
    }

    // On return change state
    public void Return(boolean DAMAGED) {
        if (state.equals(State.ON_LOAN)) {
            if (DAMAGED) {
                state = State.DAMAGED;
            } else {
                state = State.AVAILABLE;
            }
        } else {
            throw new RuntimeException(String.format("Book: cannot Return while book is in state: %s", state));
        }
    }

    // On repair change state
    public void Repair() {
        if (state.equals(State.DAMAGED)) {
            state = State.AVAILABLE;
        } else {
            throw new RuntimeException(String.format("Book: cannot repair while book is in state: %s", state));
        }
    }

}
