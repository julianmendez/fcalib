/**
 * Interface for listenning to expert actions.
 * @author Baris Sertkaya
 */
package de.tudresden.inf.tcs.fcaapi.action;

import java.util.EventListener;

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

public interface ExpertActionListener<A, I> extends EventListener {

	/**
	 * Invoked when an expert action occurs. Should check the type of the action
	 * and react accordingly. It should at least handle the following three
	 * action types:
	 * de.tudresden.inf.tcs.fcaapi.Expert#CONFIRMED_QUESTION,
	 * de.tudresden.inf.tcs.fcaapi.Expert#REJECTED_QUESTION, or
	 * de.tudresden.inf.tcs.fcaapi.Expert#PROVIDED_COUNTEREXAMPLE. For
	 * each of these action types it should call one ofthe appropriate methods.
	 * 
	 * @param action
	 *            the action performed by the expert
	 */
	void expertPerformedAction(ExpertAction action);

}
