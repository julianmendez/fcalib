package de.tudresden.inf.tcs.fcalib.action;

import java.awt.event.ActionEvent;

import org.apache.log4j.Logger;

import de.tudresden.inf.tcs.fcalib.AbstractContext;
import de.tudresden.inf.tcs.fcaapi.FCAImplication;
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
 * The expert action for confirming a question.
 * @author Baris Sertkaya
 * Technische Universtaet Dresden
 * sertkaya@tcs.inf.tu-dresden.de
 */

public class QuestionConfirmedAction<A,O> extends AbstractExpertAction<A, O> {

	private static final long serialVersionUID = 1L;
	
	private FCAImplication<A> question;
	
	/**
	 * The logger.
	 */
	private static final Logger logger = Logger.getLogger(QuestionConfirmedAction.class);
	
	// public QuestionConfirmedAction(AbstractContext<A,O> c,FCAImplication<A> q) {
	// 	// super(c);
	// 	setContext(c);
	// 	question = q;
	// }
	
	public void setQuestion(FCAImplication<A> q) {
		question = q;
	}
	
	/**
	 * Adds the confirmed question to the set of implications of the context,
	 * continues exploration with the next premise computed by using the new set of implications.
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		logger.info("Expert confirmed implication: " + question);
		getContext().getImplications().add(getQuestion());
		getContext().continueExploration(getContext().getNextPremise(getQuestion().getPremise()));
	}
	
	/**
	 * Returns the question that is confirmed.
	 * @return the confirmed question
	 */
	public FCAImplication<A> getQuestion() {
		return question;
	}
}
