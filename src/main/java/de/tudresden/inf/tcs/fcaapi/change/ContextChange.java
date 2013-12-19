package de.tudresden.inf.tcs.fcaapi.change;

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

public interface ContextChange<A> {

	int OBJECT_HAS_ATTRIBUTE_MODIFICATION = 0;
	int NEW_IMPLICATION_MODIFICATION = 1;
	int NEW_OBJECT_MODIFICATION = 2;
	int AUTOMATICALLY_ACCEPTED_IMPLICATION = 3;
	int AUTOMATICALLY_REJECTED_IMPLICATION = 4;

	/**
	 * Undoes this context modification.
	 */
	void undo();

	/**
	 * Returns the type of this context modification. Type is one of
	 * {@link #NEW_IMPLICATION_MODIFICATION},
	 * {@link #OBJECT_HAS_ATTRIBUTE_MODIFICATION},
	 * {@link #NEW_OBJECT_MODIFICATION}.
	 * 
	 * @return the type of this context modification
	 */
	int getType();

}
