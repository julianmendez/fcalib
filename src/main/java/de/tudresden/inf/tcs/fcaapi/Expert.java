/**
 * Interface describing an expert.
 * @author Baris Sertkaya
 */
package de.tudresden.inf.tcs.fcaapi;

import de.tudresden.inf.tcs.fcaapi.action.ExpertActionListener;

/*
 * FCAAPI: An API for Formal Concept Analysis tools
 * Copyright (C) 2009  Baris Sertkaya
 *
 * This file is part of FCAAPI.
 * FCAAPI is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * FCAAPI is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with FCAAPI.  If not, see <http://www.gnu.org/licenses/>.
 */

public interface Expert<A, I, O extends FCAObject<A, I>> {

	// // Action Types
	// /**
	// * Type of the event fired when the expert confirms a question.
	// */
	// int CONFIRMED_QUESTION = 00;
	//
	// /**
	// * Type of the event fired when the expert rejects a question.
	// */
	// int REJECTED_QUESTION = 1;
	//
	// /**
	// * Type of the event fired when the expert provides a counterexample.
	// */
	// int PROVIDED_COUNTEREXAMPLE = 2;
	//
	// /**
	// * Type of the event fired when the expert starts attribute exploration.
	// */
	// int STARTED_EXPLORATION = 3;
	//
	// /**
	// * Type of the event fired when the expert wants to stop attribute
	// exploration.
	// */
	// int STOPPED_EXPLORATION = 4;
	//
	// /**
	// * Type of the event fired when the expert resumes attribute exploration.
	// */
	// int RESUMED_EXPLORATION = 5;
	//
	// /**
	// * Type of the event fired when the expert resets attribute exploration.
	// */
	// int RESET_EXPLORATION = 6;
	//
	// /**
	// * Type of the event fired when the expert wants to undo the last change
	// he did to a counterexample
	// * candidate.
	// */
	// int UNDO_LAST_CECHANGE = 7;

	// /**
	// * Type of the event fired when the expert wants to undo all changes he
	// did to counterexample
	// * candidates.
	// */
	// int UNDO_ALL_CECHANGES = 8;

	// Error Message Codes
	int COUNTEREXAMPLE_EXISTS = 20;

	int COUNTEREXAMPLE_INVALID = 21;

	/**
	 * Checks whether a given implication question holds. If yes, fires an
	 * expert action of type #CONFIRMED_QUESTION, if no an expert action of type
	 * #REJECTED_QUESTION and notifies listeners.
	 *
	 * @param question
	 *            the given implication question
	 */
	void askQuestion(FCAImplication<A> question);

	/**
	 * Gets a counterexample, fires an expert action of type
	 * #PROVIDED_COUNTEREXAMPLE
	 *
	 * @param question
	 *            the given implication question
	 */
	void requestCounterExample(FCAImplication<A> question);

	/**
	 * Adds a given ExperActionListener to the list of action listeners of this
	 * expert.
	 * 
	 * @param listener
	 *            expert action listener
	 */
	void addExpertActionListener(ExpertActionListener<A, I> listener);

	void removeExpertActionListeners();

	/**
	 * Called to notify the expert that the specified counterexample is invalid
	 * due to the given reason. The reason is one of #COUNTEREXAMPLE_EXISTS or
	 * #COUNTEREXAMPLE_INVALID. An implementation of this method should then
	 * perform the necessary actions. For instance, if it is a human expert, it
	 * should display an error message with the reason.
	 *
	 * @param counterExample
	 *            counterexample
	 * @param reasonCode
	 *            reason identifier
	 */
	void counterExampleInvalid(O counterExample, int reasonCode);

	/**
	 * Called to notify the expert that the exploration finished (either the
	 * expert wanted it or the exploration algorithm terminated). An
	 * implementation of this method can for instance inform the expert by
	 * writing a message, popping up a window etc.
	 */
	void explorationFinished();

	/**
	 * Requests a counterexample from the expert. Called in the case where
	 * accepting an implication would cause problems. In this case we do not ask
	 * the expert whether the implication holds, but tell him that accepting
	 * this implication will cause problems and request a counterexample
	 * directly using this method. Note that this can for instance occur while
	 * exploring DL ontologies due to anonymous ABox individuals. In a usual
	 * formal/partial context exploration, this case can not occur.
	 * 
	 * @param implication
	 *            implication
	 */
	void forceToCounterExample(FCAImplication<A> implication);

	/**
	 * Called to notify the expert that the implication follows from the
	 * background knowledge
	 *
	 * @param implication
	 *            the implication that follows from the background knowledge
	 */
	void implicationFollowsFromBackgroundKnowledge(FCAImplication<A> implication);

}
