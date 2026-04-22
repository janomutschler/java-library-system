package service;

import model.Book;

import java.util.ArrayDeque;
import java.util.Deque;

public class HistoryManager {

	public enum ActionType {
		ADD,
		REMOVE,
		BORROW,
		RETURN
	}

	public static class Action {
		private final ActionType type;
		private final Book bookSnapshot;

		public Action(ActionType type, Book bookSnapshot) {
			this.type = type;
			this.bookSnapshot = bookSnapshot;
		}

		public ActionType getType() {
			return type;
		}

		public Book getBookSnapshot() {
			return bookSnapshot;
		}
	}

	private final Deque<Action> history;

	public HistoryManager() {
		this.history = new ArrayDeque<>();
	}

	public void record(ActionType type, Book book) {
		history.push(new Action(type, new Book(book)));
	}

	public Action undoLastAction() {
		if (history.isEmpty()) {
			return null;
		}

		return history.pop();
	}

	public boolean hasActions() {
		return !history.isEmpty();
	}
}
