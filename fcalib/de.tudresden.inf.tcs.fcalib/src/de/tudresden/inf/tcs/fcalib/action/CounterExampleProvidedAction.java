package de.tudresden.inf.tcs.fcalib.action;

import java.awt.event.ActionEvent;

import org.apache.log4j.Logger;

import de.tudresden.inf.tcs.fcaapi.FCAImplication;
import de.tudresden.inf.tcs.fcaapi.Expert;
import de.tudresden.inf.tcs.fcaapi.FCAObject;
import de.tudresden.inf.tcs.fcaapi.exception.IllegalObjectException;
import de.tudresden.inf.tcs.fcalib.AbstractContext;
import de.tudresden.inf.tcs.fcalib.action.AbstractExpertAction;


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

/**
 * The expert action fired when a counterexample is provided.
 * @author Baris Sertkaya
 * Technische Universtaet Dresden
 * sertkaya@tcs.inf.tu-dresden.de
 */

public class CounterExampleProvidedAction<A,I,O extends FCAObject<A,I>> extends AbstractExpertAction<A,I,O> {

	private static final long serialVersionUID = 1L;
	
	private FCAImplication<A> question;
	
	private O counterExample;
	
	/**
	 * The logger.
	 */
	private static final Logger logger = Logger.getLogger(CounterExampleProvidedAction.class);
	
	public CounterExampleProvidedAction(AbstractContext<A,I,O> c,FCAImplication<A> q,O ce) {
		// super(c);
		setContext(c);
		question = q;
		counterExample = ce;
	}
	
	/**
	 * Returns the question rejected with the counterexample in this action is provided.
	 * @return the question rejected with the counterexample in this action is provided.
	 */
	public FCAImplication<A> getQuestion() {
		return question;
	}
	
	/**
	 * Returns the counterexample provided by the expert with this action.
	 * @return the counterexample provided by the expert with this action.
	 */
	public O getCounterExample() {
		return counterExample;
	}
	
	/**
	 * Checks whether the counterexample is valid. If it is it adds the counterexample to 
	 * the set of objects and continues the exploration with the same premise. If it is not,
	 * then tells the expert why it is not valid and requests another counterexample.
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		logger.info("Expert provided counterexample: " + counterExample);
		// check whether counterexample is valid
		try {
			if (getContext().isCounterExampleValid(counterExample, question)) {
				getContext().addObject(counterExample);
				getContext().continueExploration(getQuestion().getPremise());
			}
			else {
				getContext().getExpert().counterExampleInvalid(getCounterExample(), 
						Expert.COUNTEREXAMPLE_INVALID);
				getContext().getExpert().requestCounterExample(getQuestion());
			}
		}
		catch (IllegalObjectException x) {
			getContext().getExpert().counterExampleInvalid(getCounterExample(), 
					Expert.COUNTEREXAMPLE_EXISTS);
			getContext().getExpert().requestCounterExample(getQuestion());
		}
	}
}
