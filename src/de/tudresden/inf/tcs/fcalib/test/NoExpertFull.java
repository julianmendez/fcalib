/**
 * An expert implementation for a partial context that rejects every question and
 * provides a default counterexample.
 * @author Baris Sertkaya
 */
package de.tudresden.inf.tcs.fcalib.test;

import org.apache.log4j.Logger;

import de.tudresden.inf.tcs.fcaapi.Expert;
import de.tudresden.inf.tcs.fcaapi.FCAImplication;
import de.tudresden.inf.tcs.fcaapi.ObjectDescription;
import de.tudresden.inf.tcs.fcalib.AbstractExpert;
import de.tudresden.inf.tcs.fcalib.FormalContext;
import de.tudresden.inf.tcs.fcalib.FullObject;
import de.tudresden.inf.tcs.fcaapi.action.ExpertAction;
import de.tudresden.inf.tcs.fcalib.action.QuestionRejectedAction;
import de.tudresden.inf.tcs.fcalib.action.CounterExampleProvidedAction;


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

public class NoExpertFull<A> extends AbstractExpert<A,FullObject<A>> {
	
	/**
	 * To be used as name for default counterexamples.
	 */
	private int name = 0;
	
	/**
	 * The logger.
	 */
	private static final Logger logger = Logger.getLogger(NoExpertFull.class);
	
	/**
	 * Creates an instance of NoExpertFull.
	 *
	 */
	
	FormalContext<A> theContext;
	
	public NoExpertFull(FormalContext<A> context) {
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
		FullObject<A> counterExample = new FullObject<A>(name + "",question.getPremise());
		++name;

		ExpertAction<A,FullObject<A>> action = 
			new CounterExampleProvidedAction<A,FullObject<A>>(theContext,question,counterExample);
		fireExpertAction(action);
	}
	
	/**
	 * Rejects every given question, i.e., fires action 
	 * {@link de.tudresden.inf.tcs.fcaapi.Expert#REJECTED_QUESTION}
	 * @param question the given question to be rejected
	 */
	@Override
	public synchronized void askQuestion(FCAImplication<A> question) {
		 QuestionRejectedAction<A,FullObject<A>> action = 
			new QuestionRejectedAction<A,FullObject<A>>();
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
	// 	FullObject<A> counterExample = new FullObject<A>(name + "",question.getPremise());
	// 	++name;
	// 	theContext.counterExampleProvided(counterExample, question);
	// }
	
	@Override
	public void counterExampleInvalid(FullObject<A> counterExample, int reason) {
		switch (reason) {
		case Expert.COUNTEREXAMPLE_EXISTS:
			logger.error("An object with name " + counterExample.getName() + " already exists");
			break;
		case Expert.COUNTEREXAMPLE_INVALID:
			logger.error("The object " + counterExample.getName() + " is not a valid counter example");
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
	
}
