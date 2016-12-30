/**
 * An abstract expert implementation.
 */
package de.tudresden.inf.tcs.fcalib;

import java.util.List;
import java.util.ArrayList;

import de.tudresden.inf.tcs.fcaapi.FCAObject;
import de.tudresden.inf.tcs.fcaapi.Expert;
import de.tudresden.inf.tcs.fcaapi.action.ExpertAction;
import de.tudresden.inf.tcs.fcaapi.action.ExpertActionListener;
import de.tudresden.inf.tcs.fcaapi.FCAImplication;


/*
 * FCAlib: An open-source extensible library for Formal Concept Analysis 
 *         tool developers
 * Copyright (C) 2009  Baris Sertkaya
 *
 * This file is part of FCAlib.
 * FCAlib is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * FCAlib is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with FCAlib.  If not, see <http://www.gnu.org/licenses/>.
 */

public abstract class AbstractExpert<A,I,O extends FCAObject<A,I>> implements Expert<A,I,O> {
	
	/**
	 * The list of listeners
	 */
	private List<ExpertActionListener<A,I>> listeners; 
	
	/**
	 * Creates an AbstractExpert with empty set of listeners.
	 *
	 */
	public AbstractExpert() {
		listeners = new ArrayList<>();
	}

	/**
	 * Gets a counterexample, fires an expert action of type #PROVIDED_COUNTEREXAMPLE
	 * @param question the given implication question
	 */
	public abstract void requestCounterExample(FCAImplication<A> question);
	
	/**
	 * Checks whether a given implication question holds. If yes, fires an expert action of
	 * type #CONFIRMED_QUESTION, if no an expert action of type
	 * #REJECTED_QUESTION and notifies listeners.
	 * @param question the given implication question
	 */
	public abstract void askQuestion(FCAImplication<A> question);

	/**
	 * Called to notify the expert that the specified counterexample is invalid due to the given
	 * reason. The reason is one of #COUNTEREXAMPLE_EXISTS or #COUNTEREXAMPLE_INVALID.
	 * An implementation of this method should then perform the necessary actions. For instance, if
	 * it is a human expert, it should display an error message with the reason. 
	 * @param counterExample the counterexample given by the expert
	 * @param reason the reason why the counterexample is not valid
	 */
	public abstract void counterExampleInvalid(O counterExample, int reason); 
	
	/**
	 * Adds a given ExpertActionListener to the listener list of this Expert
	 * @param listener the listener to be added
	 */
	public synchronized void addExpertActionListener(ExpertActionListener<A,I> listener) {
		listeners.add(listener);
	}
	
	// public synchronized void removeExpertActionListener(ExpertActionListener<A,O> listener) {
	public synchronized void removeExpertActionListeners() {
		// listeners.remove(listener);
		listeners.clear();
	}
	
	/**
	 * Fires a given ExpertAction event. 
	 * @param action the ExpertAction event to be fired
	 */
	public synchronized void fireExpertAction(ExpertAction action) {
		
		for (ExpertActionListener<A,I> listener : listeners) {
			listener.expertPerformedAction(action);
		}
	}
	
	/**
	 * Requests a counterexample from the expert. Called in the case where accepting an implication
	 * would cause problems. In this case we do not ask the expert whether the
	 * implication holds, but tell him that accepting this implication will cause problems
	 * and request a counterexample directly using this method. Note that this can
	 * for instance occur while exploring DL ontologies due to anonymous ABox individuals. In a usual 
	 * formal/partial context exploration, this case can not occur. 
	 */
	public abstract void forceToCounterExample(FCAImplication<A> implication);
	
}
