package eu.vrtime.bootwicketappthree.model;

public class BankAccount {

	private Long accountNumber;
	private String owner;
	private String branch;
	private double balance;
	private double interestRate;

	private BankAccount() {

	}

	public Long getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(Long accountNumber) {
		this.accountNumber = accountNumber;
	}

	public String getOwner() {
		return owner;
	}

	public void setOwner(String owner) {
		this.owner = owner;
	}

	public String getBranch() {
		return branch;
	}

	public void setBranch(String branch) {
		this.branch = branch;
	}

	public double getBalance() {
		return balance;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}

	public double getInterestRate() {
		return interestRate;
	}

	public void setInterestRate(double interestRate) {
		this.interestRate = interestRate;
	}

	public static class Builder {

		private Long accountNumber;
		private String owner;
		private String branch;
		private double balance;
		private double interestRate;

		public Builder(Long accountNumber) {
			this.accountNumber = accountNumber;
		}

		public Builder withOwner(String owner) {
			this.owner = owner;
			return this;
		}

		public Builder atBranch(String branch) {
			this.branch = branch;
			return this;
		}

		public Builder openingBalance(double balance) {
			this.balance = balance;
			return this;
		}

		public Builder atRate(double interestRate) {
			this.interestRate = interestRate;
			return this;
		}

		public BankAccount build() {
			BankAccount account = new BankAccount();
			account.owner = this.owner;
			account.branch = this.branch;
			account.balance = this.balance;
			account.interestRate = this.interestRate;
			return account;
		}
	}

}
