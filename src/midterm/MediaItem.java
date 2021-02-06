package midterm;//same package as the Library class

//this begins our class, MediaItem
public class MediaItem {
	private String title;// title of the media item
	private String format;// format of the media item can be BlueRay, DVD, or Windows, Mac, Xbox,
	// Playstation, etc
	private boolean onLoan;// is the media item loaned out or not? This data field tells us so
	private String loanedTo;// this data field tells us who the media item is loaned to
	private String dateLoaned;// this tells us the date the item was loaned.

	//first constructor
	//set the variables to null, except for onLoan, which is set to false by default
	MediaItem() {
		this.title = null;
		this.format = null;
		this.onLoan = false;
		this.loanedTo = null;
		this.dateLoaned = null;
	}

	//another constructor with two parameters, title and format
	MediaItem(String title, String format) {
		this.title = title;
		this.format = format;
		this.onLoan = false;
	}

	//getter and setter methods below
	//we want to get the title of the media item
	public String getTitle() {
		return this.title;
	}

	//set the title of the media item
	public void setTitle(String title) {
		this.title = title;
	}

	//get the format of the media item. Is it a CD or a DVD, for example.
	public String getFormat() {
		return this.format;
	}

	//set the format
	public void setFormat(String format) {
		this.format = format;
	}

	//is it loaned out or not?
	public boolean isOnLoan() {
		return this.onLoan;
	}

	//set whether it has been loaned out or not.
	public void setIsOnLoan(boolean onLoan) {
		this.onLoan = onLoan;
	}

	//get who the media item was loaned to
	public String getLoanedTo() {
		return this.loanedTo;
	}

	//set who the media item was loaned to
	public void setLoanedTo(String loanedTo) {
		this.loanedTo = loanedTo;
	}

	//get the date that this media item was loaned on
	public String getDateLoaned() {
		return this.dateLoaned;
	}

	//set the date that the media item was loaned on
	public void setDateLoaned(String dateLoaned) {
		this.dateLoaned = dateLoaned;
	}

	public void markOnLoan(String name, String date) {
		if (this.onLoan) {
			System.out.println("Error, this item is already loaned out");
		} else {
			setIsOnLoan(true);
			setLoanedTo(name);
			setDateLoaned(date);
			// this.onLoan = true;
			// this.loanedTo = name;
			// this.dateLoaned = date;
		}
	}

	public void markReturned() {
		if (this.onLoan) {
			setIsOnLoan(false);
			setLoanedTo(null);
			setDateLoaned(null);
			// this.onLoan = false;
			// this.loanedTo = null;
			// this.dateLoaned = null;
		} else {
			System.out.println("Error: this item is not currently loaned out");
		}
	}

}
