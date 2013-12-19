/**
 * Defines expert action event.
 * @author Baris Sertkaya
 */
package de.tudresden.inf.tcs.fcaapi.obsolete;

import java.util.EventObject;

import de.tudresden.inf.tcs.fcaapi.FCAImplication;

public class ExpertAction<T,O extends Object> extends EventObject {

	private static final long serialVersionUID = 1L;
	
	/**
	 * The question that resulted in this expert action.
	 */
	private FCAImplication<T> question;

	/**
	 * The counterexample provided in response to a question.
	 */
	private O counterExample;
	
	/**
	 * The type of this action.
	 */
	private int type;
	
	/**
	 * Creates a new expert action event. 
	 * @param source source of this event
	 * @param t type of this event
	 */
	public ExpertAction(Object source, int t) {
		super(source);
		type = t;
	}
	
	/**
	 * Creates a new expert action event. 
	 * @param source source of this event
	 * @param t type of this event
	 * @param q the question that resulted in this event
	 */
	public ExpertAction(Object source, int t, FCAImplication<T> q) {
		super(source);
		type = t;
		question = q;
	}
	
	/**
	 * Creates a new expert action event.
	 * @param source source of this event
	 * @param t type of this event
	 * @param q the question that resulted in this event
	 * @param o the counterexample provided with this event
	 */
	public ExpertAction(Object source, int t, FCAImplication<T> q, O o) {
		super(source);
		type = t;
		question = q;
		counterExample = o;
	}
	
	/**
	 * Returns the type of this event
	 * @return type of this event
	 */
	public int getType() {
		return type;
	}
	/**
	 * Returns the question that resulted in this event.
	 * @return the question that resulted in this event
	 */
	public FCAImplication<T> getQuestion() {
		return question;
	}
	
	/**
	 *  Returns the counterexample that is provided with this event.
	 * @return the counterexample provided by the expert with this event
	 */
	 public O getCounterExample() {
		return counterExample;
	}
	 
}
