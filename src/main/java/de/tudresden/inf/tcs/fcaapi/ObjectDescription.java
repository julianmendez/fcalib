/**
 * FCAObject description interface.
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

public interface ObjectDescription<A> {

	/**
	 * Checks whether this object description contains a given attribute.
	 *
	 * @param attribute
	 *            the given attribute
	 * @return <code>true</code> if this object description has the
	 *         <code>attribute</code>, <code>false</code> otherwise
	 */
	boolean containsAttribute(A attribute);

	/**
	 * Checks whether this object description contains all of the attributes in
	 * a given set.
	 *
	 * @param attrs
	 *            the given attribute set to be checked
	 * @return <code>true</code> if this object description contains all of the
	 *         attributes in <code>attrs</code>, <code>false</code> otherwise
	 */
	boolean containsAttributes(Set<A> attrs);

	/**
	 * Adds a given attribute to this object description.
	 *
	 * @param attribute
	 *            the given attribute
	 * @return <code>true</code> if <code>attribute</code> is successfully
	 *         added, <code>false</code> otherwise
	 */
	boolean addAttribute(A attribute);

	/**
	 * Adds the attributes in a given set of attributes to this object
	 * description.
	 *
	 * @param attrs
	 *            set of attributes to be added
	 * @return <code>true</code> if all attributes in <code>attrs</code> are
	 *         successfully added, <code>false</code> otherwise
	 */
	boolean addAttributes(Set<A> attrs);

	/**
	 * Removes a specified attribute from this object description.
	 *
	 * @param attribute
	 *            the attribute to be removed
	 * @return <code>true</code> if <code>attribute</code> is successfully
	 *         removed, <code>false</code> otherwise
	 */
	boolean removeAttribute(A attribute);

	/**
	 * Produces a deep-copy of this object description.
	 *
	 * @return a deep-copy of this object description
	 * @throws CloneNotSupportedException
	 *             if clone is not supported
	 */
	Object clone() throws CloneNotSupportedException;

}
