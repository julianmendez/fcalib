/**
 * Interface describing a context.
 * @author Baris Sertkaya
 *
 */

package de.tudresden.inf.tcs.fcaapi;

import java.util.Set;

import de.tudresden.inf.tcs.fcaapi.action.ExpertActionListener;
import de.tudresden.inf.tcs.fcaapi.exception.IllegalAttributeException;
import de.tudresden.inf.tcs.fcaapi.exception.IllegalObjectException;
import de.tudresden.inf.tcs.fcaapi.utils.IndexedSet;

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

public interface Context<A, I, O extends FCAObject<A, I>> extends
		ExpertActionListener<A, I> {

	/**
	 * Adds a given attribute to the set of attributes of this context.
	 * 
	 * @param attr
	 *            the given attribute
	 * @return <code>true</code> if <code>attr</code> is successfully added,
	 *         <code>false</code> otherwise
	 * @throws IllegalAttributeException
	 *             if <code>attr</code> has already been added
	 */
	boolean addAttribute(A attr) throws IllegalAttributeException;

	/**
	 * Adds the attributes in a given set of attributes to the attributes of
	 * this context.
	 * 
	 * @param attrs
	 *            the given set of attributes
	 * @return <code>true</code> if all of the <code>attrs</code> is
	 *         successfully added, <code>false</code> otherwise
	 * @throws IllegalAttributeException
	 *             if at least one of the <code>attrs</code> has already been
	 *             added
	 */
	boolean addAttributes(Set<A> attrs) throws IllegalAttributeException;

	/**
	 * Returns the set of attributes of this context.
	 * 
	 * @return the set of attributes
	 */
	IndexedSet<A> getAttributes();

	/**
	 * Returns the number of attributes of this context.
	 * 
	 * @return number of attributes
	 */
	int getAttributeCount();

	/**
	 * Returns the attribute at a specified index.
	 * 
	 * @param index
	 *            index of the attribute that is required
	 * @return the attribute at index <code>index</code>
	 */
	A getAttributeAtIndex(int index);

	/**
	 * Adds a given object to the set of objects of this context.
	 * 
	 * @param object
	 *            the given object to be added
	 * @return <code>true</code> if <code>object</code> is successfully added,
	 *         <code>false</code> otherwise
	 * @throws IllegalObjectException
	 *             if <code>object</code> has already been added
	 */
	boolean addObject(O object) throws IllegalObjectException;

	/**
	 * Returns the number of objects of this context.
	 * 
	 * @return number of objects
	 */
	int getObjectCount();

	/**
	 * Removes the object from the set of objects of this context whose name is
	 * given.
	 * 
	 * @param id
	 *            identifier of the object to be removed
	 * @return <code>true</code> if the object with name <code>name</code> is
	 *         successfully removed, <code>false</code> otherwise
	 * @throws IllegalObjectException
	 *             if an object with name <code>name</code> does not exist
	 */
	boolean removeObject(I id) throws IllegalObjectException;

	/**
	 * Removes a given object from the set of objects of this context.
	 * 
	 * @param object
	 *            the object to be removed
	 * @return <code>true</code> if the object <code>object</code> is
	 *         successfully removed, <code>false</code> otherwise
	 * @throws IllegalObjectException
	 *             if the object <code>object</code> does not exist
	 */
	boolean removeObject(O object) throws IllegalObjectException;

	/**
	 * Adds the objects in a given set of objects to the objects of this
	 * context.
	 * 
	 * @param objects
	 *            the given set of objects
	 * @return <code>true</code> if all of the <code>objects</code> is
	 *         successfully added, <code>false</code> otherwise
	 * @throws IllegalObjectException
	 *             if at least one of the objects in <code>objects</code> has
	 *             already been added
	 */
	boolean addObjects(Set<O> objects) throws IllegalObjectException;

	/**
	 * Clears the set of objects.
	 */
	void clearObjects();

	/**
	 * Returns the object whose identifier is given.
	 * 
	 * @param id
	 *            the given identifier
	 * @return the object with identifier <code>id</code>, <code>null</code> if
	 *         such an object does not exist
	 */
	O getObject(I id);

	/**
	 * Checks if this context contains an object with the given identifier.
	 * 
	 * @param id
	 *            identifier of the object to be searched
	 * @return <code>true</code> if an object with identifier <code>id</code> is
	 *         found, <code>false</code> otherwise
	 */
	boolean containsObject(I id);

	/**
	 * Returns the set of objects of this context.
	 * 
	 * @return the set of objects
	 */
	IndexedSet<O> getObjects();

	/**
	 * Returns the object at a specified index.
	 * 
	 * @param index
	 *            index of the object that is required
	 * @return the object at index <code>index</code>
	 */
	O getObjectAtIndex(int index);

	/**
	 * Adds a given attribute to the attributes of the given object.
	 * 
	 * @param attribute
	 *            the attribute to be added
	 * @param id
	 *            identifier of the object to which <code>attribute</code> is to
	 *            be added
	 * @return <code>true</code> if of the <code>attribute</code> is
	 *         successfully added, <code>false</code> otherwise
	 * @throws IllegalAttributeException
	 *             if the object with identifier <code>id</code> already has the
	 *             attribute
	 * @throws IllegalObjectException
	 *             if an object with identifier <code>id</code> does not exist
	 *             in this context
	 */
	boolean addAttributeToObject(A attribute, I id)
			throws IllegalAttributeException, IllegalObjectException;

	/**
	 * Checks whether a specified object has the specified attribute.
	 * 
	 * @param object
	 *            the object given for check
	 * @param attribute
	 *            the attribute given for check
	 * @return <code>true</code> if the <code>object</code> has the
	 *         <code>attribute</code> <code>false</code> otherwise
	 */
	boolean objectHasAttribute(O object, A attribute);

	/**
	 * Returns the second derivative of a given set of attributes.
	 * 
	 * @param attributes
	 *            the given set of attributes
	 * @return set of attributes equivalent to the second derivative of
	 *         <code>attributes</code>
	 */
	Set<A> doublePrime(Set<A> attributes);

	/**
	 * Returns the stem base of this context.
	 * 
	 * @return set of implications that is the stem base of this context
	 */
	Set<FCAImplication<A>> getStemBase();

	/**
	 * @see de.tudresden.inf.tcs.fcaapi.Context#getStemBase()
	 * @return the Duquenne Guigues Base of this context
	 */
	Set<FCAImplication<A>> getDuquenneGuiguesBase();

	/**
	 * Checks whether an expert is set for this context.
	 * 
	 * @return <code>true</code> if an expert is set <code>false</code>
	 *         otherwise
	 */
	boolean isExpertSet();

	/**
	 * Returns the expert for attribute exploration.
	 * 
	 * @return the expert
	 */
	Expert<A, I, O> getExpert();

	/**
	 * Sets the expert for attribute exploration.
	 * 
	 * @param e
	 *            the given expert
	 */
	void setExpert(Expert<A, I, O> e);

	/**
	 * Returns the current set of implications of this context.
	 * 
	 * @return the current set of implications
	 */
	Set<FCAImplication<A>> getImplications();

}
