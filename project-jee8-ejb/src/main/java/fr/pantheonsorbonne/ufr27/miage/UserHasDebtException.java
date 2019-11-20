package fr.pantheonsorbonne.ufr27.miage;

public class UserHasDebtException extends Exception {

	double debt;
	int userId;

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public double getDebt() {
		return debt;
	}

	public void setDebt(double debt) {
		this.debt = debt;
	}

	public UserHasDebtException(double amount,int userId) {
		super("you owe us " + amount);
		this.debt = amount;
		this.userId=userId;
	}

	public int getUserId() {
		return userId;
	}

}
