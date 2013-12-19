/**
 * Interface for FCA implications.
 * @author Baris Sertkaya
 */
package de.tudresden.inf.tcs.fcaapi;

import java.util.Set;

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

public interface FCAImplication<A> {

	/**
	 * Returns the premise of this implication.
	 * 
	 * @return the premise
	 */
	Set<A> getPremise();

	/**
	 * Returns the conclusion of this implication.
	 * 
	 * @return the conclusion
	 */
	Set<A> getConclusion();

	/**
	 * Checks if this implication is equal to a given implication.
	 * 
	 * @param imp
	 *            implication to be checked for equality
	 * @return <code>true</code> if the premise of this implication is equal to
	 *         the premise of <code>imp</code> and the conclusion of this
	 *         implication is equal to the conclusion of <code>imp</code>, false
	 *         otherwise
	 */
	boolean equals(FCAImplication<A> imp);

}
