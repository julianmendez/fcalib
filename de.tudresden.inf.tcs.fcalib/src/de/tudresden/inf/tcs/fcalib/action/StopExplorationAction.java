package de.tudresden.inf.tcs.fcalib.action;

import java.awt.event.ActionEvent;

import org.apache.log4j.Logger;

import de.tudresden.inf.tcs.fcaapi.FCAObject;
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
 * The expert action that stops the exploration. It does not reset exploration so that it 
 * can be restarted again.
 * @author Baris Sertkaya
 * Technische Universtaet Dresden
 * sertkaya@tcs.inf.tu-dresden.de
 */

public class StopExplorationAction<A,I,O extends FCAObject<A,I>> extends AbstractExpertAction<A,I,O> {

	private static final long serialVersionUID = 1L;
	
	/**
	 * The logger.
	 */
	private static final Logger logger = Logger.getLogger(StopExplorationAction.class);
	
	/**
	 * Just writes an info log saying that the expert stopped exploration.
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		logger.info("=== Expert stopped exploration ===");
	}
	
}
