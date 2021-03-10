/**
 * An expert implementation for a partial context that rejects every question and
 * provides a default counterexample.
 * @author Baris Sertkaya
 */
package de.tudresden.inf.tcs.fcalib.test;

import java.util.Iterator;

import java.util.logging.Logger;

import de.tudresden.inf.tcs.fcaapi.Expert;
import de.tudresden.inf.tcs.fcaapi.action.ExpertAction;
import de.tudresden.inf.tcs.fcaapi.FCAImplication;
import de.tudresden.inf.tcs.fcaapi.ObjectDescription;
import de.tudresden.inf.tcs.fcalib.AbstractExpert;
import de.tudresden.inf.tcs.fcalib.PartialObject;
import de.tudresden.inf.tcs.fcalib.PartialContext;
import de.tudresden.inf.tcs.fcalib.action.CounterExampleProvidedAction;
import de.tudresden.inf.tcs.fcalib.action.QuestionRejectedAction;


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

public class NoExpertPartial<A> extends AbstractExpert<A,String,PartialObject<A,String>> {
	
	/**
	 * To be used as name for default counterexamples.
	 */
	private int name = 0;
	
	/**
	 * The logger.
	 */
	private static final Logger logger = Logger.getLogger(NoExpertPartial.class.getName());
	
	private PartialContext<A,String,PartialObject<A,String>> theContext;
	/**
	 * Creates an instance of NoExpertPartial.
	 *
	 */
	public NoExpertPartial(PartialContext<A,String,PartialObject<A,String>> context) {
		super();
		theContext = context;
	}
	
	/**
	 * Prepares a default counterexample to a given question, and fires an ExpertAction of type
	 * {@link de.tudresden.inf.tcs.fcaapi.Expert#PROVIDED_COUNTEREXAMPLE} together with the 
	 * counterexample.
	 * @param question the question to which a counterexample is going to be
	 */
	@Override
	public synchronized void requestCounterExample(FCAImplication<A> question) {
		PartialObject<A,String> counterExample = new PartialObject<A,String>(name + "",question.getPremise());
		++name;
		Iterator<A> it = question.getConclusion().iterator();

		// add the first attribute in the conclusion of the question as a negated attribute to the
		// description of counterExample
		counterExample.getDescription().addNegatedAttribute(it.next());
		ExpertAction action = 
			// new ExpertAction<A,PartialObject<A>>(this,Expert.PROVIDED_COUNTEREXAMPLE, question,
			// 		counterExample);
			new CounterExampleProvidedAction<A,String,PartialObject<A,String>>(theContext,question,counterExample);
		fireExpertAction(action);
	}
	
	/**
	 * Rejects every given question, i.e., fires action 
	 * {@link de.tudresden.inf.tcs.fcaapi.Expert#REJECTED_QUESTION}
	 * @param question the given question to be rejected
	 */
	@Override
	public synchronized void askQuestion(FCAImplication<A> question) {
		 QuestionRejectedAction<A,String,PartialObject<A,String>> action = 
			// new ExpertAction<A,PartialObject<A>>(this,Expert.REJECTED_QUESTION, question);
			 new QuestionRejectedAction<A,String,PartialObject<A,String>>();
		 action.setContext(theContext);
		 action.setQuestion(question);
		 fireExpertAction(action);
	}

	// @Override
	// public void askQuestion(FCAImplication<A> question) {
	// 	System.out.println(question);
	// 	theContext.questionRejected(question);
	// }
	
	// @Override
	// public void requestCounterExample(FCAImplication<A> question) {
	// 	PartialObject<A> counterExample = new PartialObject<A>(name + "",question.getPremise());
	// 	++name;
	// 	Iterator<A> it = question.getConclusion().iterator();
	// 	// add the first attribute in the conclusion of the question as a negated attribute to the
	// 	// description of counterExample
	// 	counterExample.getDescription().addNegatedAttribute(it.next());
	// 	theContext.counterExampleProvided(counterExample, question);
	// }
	
	@Override
	public void counterExampleInvalid(PartialObject<A,String> counterExample, int reason) {
		switch (reason) {
		case Expert.COUNTEREXAMPLE_EXISTS:
			// System.err.print("An object with the same name already exists\n");
			logger.severe("An object with name " + counterExample.getName() + " already exists");
			break;
		case Expert.COUNTEREXAMPLE_INVALID:
			// System.err.print("The object is not a valid counter example\n");
			logger.severe("The object " + counterExample.getName() + " is not a valid counter example");
			break;
		}
	}
	
	/**
	 * @deprecated
	 */
	// @Override
	public <D extends ObjectDescription<A>> D getCounterExampleDescription() {
		return null;
	}
	
	public void explorationFinished() {
		logger.info("=== End of exploration ===");
	}
	
	/**
	 * Not necessary for partial contexts.
	 * @deprecated
	 */
	public void forceToCounterExample(FCAImplication<A> implication) {
		
	}
	
	/**
	 * @deprecated
	 */
	public void implicationFollowsFromBackgroundKnowledge(FCAImplication<A> imp) {
		
	}
}
