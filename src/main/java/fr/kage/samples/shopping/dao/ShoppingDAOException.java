package fr.kage.samples.shopping.dao;

public class ShoppingDAOException extends RuntimeException {

	public ShoppingDAOException(String message, Throwable cause) {
		super(message, cause);
	}

	public ShoppingDAOException(String message) {
		super(message);
	}

}
