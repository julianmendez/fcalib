/**
 * Interface describing FCA objects.
 * @author Baris Sertkaya
 */
package de.tudresden.inf.tcs.fcaapi;

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

public interface FCAObject<A, I> {

	/**
	 * Returns the identifier of this object. Identifiers are unique in a
	 * context.
	 * 
	 * @return the identifier of this object
	 */
	I getIdentifier();

	/**
	 * Returns the name of this object.
	 * 
	 * @return name of this object
	 */
	String getName();

	/**
	 * Returns the description of this object.
	 * 
	 * @return description of this object
	 */
	ObjectDescription<A> getDescription();

	// /**
	// * Adds a given attribute to this object.
	// * @param attribute the given attribute
	// * @return <code>true</code> if <code>attribute</code> is successfully
	// added, <code>false</code>
	// * otherwise
	// * @throws IllegalAttributeException if <code>attribute</code> is not in
	// the attributes of the
	// * base context
	// */
	// boolean addAttribute(A attribute) throws
	// IllegalAttributeException;

	// /**
	// * Adds the attributes in a given set of attribute to this object.
	// * @param attributes the given set of attribute
	// * @return <code>true</code> if all of the <code>attributes</code> is
	// successfully added,
	// * <code>false</code> otherwise
	// * @throws IllegalAttributeException if at least one of the
	// <code>attributes</code> is not in the
	// * attributes of the base context
	// */
	// boolean addAttributes(Set<A> attributes) throws
	// IllegalAttributeException;

	// /**
	// * Checks whether this object has a given attribute.
	// * @param attribute the given attribute
	// * @return <code>true</code> if the description of this object contains
	// the <code>attribute</code>,
	// * <code>false</code> otherwise
	// * @throws IllegalAttributeException if <code>attribute</code> is not in
	// the attributes of the
	// * base context
	// */
	// boolean hasAttribute(A attribute) throws
	// IllegalAttributeException;

	// /**
	// * Checks whether this object has the given attributes.
	// * @param attributes the given attributes
	// * @return <code>true</code> if the description of this object contains
	// all of the
	// * <code>attributes</code>, <code>false</code> otherwise
	// * @throws IllegalAttributeException if at least one of the
	// <code>attributes</code> is not in the
	// * attributes of the base context
	// */
	// boolean hasAttributes(Set<A> attributes) throws
	// IllegalAttributeException;

	/**
	 * Checks whether this object respects a given implication.
	 * 
	 * @param implication
	 *            the given implication
	 * @return <code>true</code> if this object respects
	 *         <code>implication</code>, <code>false</code> otherwise
	 */
	boolean respects(FCAImplication<A> implication);

}
