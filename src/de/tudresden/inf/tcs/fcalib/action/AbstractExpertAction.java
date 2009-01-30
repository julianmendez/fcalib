package de.tudresden.inf.tcs.fcalib.action;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;

import de.tudresden.inf.tcs.fcaapi.action.ExpertAction;
import de.tudresden.inf.tcs.fcalib.AbstractContext;


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
 * An abstract implementation of actions that the expert can perform.
 * @author Baris Sertkaya
 * Technische Universtaet Dresden
 * sertkaya@tcs.inf.tu-dresden.de
 */

public abstract class AbstractExpertAction<A,O> extends AbstractAction implements ExpertAction<A,O> {

	private static final long serialVersionUID = 1L;

	protected AbstractContext<A,O> context;
	
	// public AbstractExpertAction(AbstractContext<A,O> c) {
	// 	context = c;
	// }
	
	public void setContext(AbstractContext<A,O> c) {
		context = c;
	}
	
	public abstract void actionPerformed(ActionEvent e);
	
	public AbstractContext<A,O> getContext() {
		return context;
	}

}
