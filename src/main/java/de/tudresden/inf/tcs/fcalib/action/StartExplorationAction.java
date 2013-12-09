package de.tudresden.inf.tcs.fcalib.action;

import java.util.Collections;
import java.util.Set;

import java.awt.event.ActionEvent;

import org.apache.log4j.Logger;

import de.tudresden.inf.tcs.fcaapi.FCAObject;

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
 * The expert action that starts the exploration.
 * @author Baris Sertkaya
 * Technische Universtaet Dresden
 * sertkaya@tcs.inf.tu-dresden.de
 */

public class StartExplorationAction<A,I,O extends FCAObject<A,I>> extends AbstractExpertAction<A,I,O> {

	/**
	 * The logger.
	 */
	private static final Logger logger = Logger.getLogger(StartExplorationAction.class);
	
	private static final long serialVersionUID = 1L;
	
	/**
	 * First checks if the expert is set, if yes then starts the exploration with 
	 * an empty initial premise.
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		getContext().initializeExploration();
		if (!getContext().isExpertSet()) {
			logger.fatal("=== Expert illegal, first set the expert! ===");
		}
		else {
			logger.info("== Exploration starting ===");
			Set<A> initialPremise = Collections.emptySet();
			getContext().continueExploration(initialPremise);
		}
	}
}
