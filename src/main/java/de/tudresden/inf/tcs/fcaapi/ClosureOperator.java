/**
 * Interface describing a closure operator.
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

public interface ClosureOperator<T> {

	/**
	 * Computes the closure of a given set under this closure operator.
	 * 
	 * @param s
	 *            the set whose closure is to be computed
	 * @return closure of <code>s</code> under this closure operator
	 */
	Set<T> closure(Set<T> s);

	/**
	 * Checks whether a given set is closed under this closure operator.
	 * 
	 * @param s
	 *            the set to be checked for closedness
	 * @return <code>true</code> if <code>s</code> is closed, <code>false</code>
	 *         otherwise
	 */
	boolean isClosed(Set<T> s);

	/**
	 * Computes all closed sets.
	 * 
	 * @return all sets that are closed under this closure operator
	 */
	Set<Set<T>> allClosures();

}
