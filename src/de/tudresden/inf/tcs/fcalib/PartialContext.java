
package de.tudresden.inf.tcs.fcalib;

import java.util.Set;
import java.util.HashSet;

import de.tudresden.inf.tcs.fcaapi.FCAImplication;
import de.tudresden.inf.tcs.fcaapi.exception.IllegalObjectException;
import de.tudresden.inf.tcs.fcaapi.exception.IllegalAttributeException;
import de.tudresden.inf.tcs.fcaapi.utils.IndexedSet;
import de.tudresden.inf.tcs.fcalib.utils.ListSet;


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
 * A partial context implementation. Extends AbstractContext.
 * @author Baris Sertkaya
 * Technische Universtaet Dresden
 * sertkaya@tcs.inf.tu-dresden.de
 */


public class PartialContext<A> extends AbstractContext<A,PartialObject<A>> {

	/**
	 * The objects of this partial context.
	 */
	protected ListSet<PartialObject<A>> objects;
	
	/**
	 * Creates a partial context with empty set of objects and attributes.
	 */
	public PartialContext() {
		objects = new ListSet<PartialObject<A>>();
	}
	
	/**
	 * Returns the set of objects of this partial context.
	 * @return the set of objects of this partial context
	 */
	@Override
	public IndexedSet<PartialObject<A>> getObjects() {
		return objects;
	}
	
	/**
	 * Returns the object whose name is given.
	 * @param name the given name
	 * @return the object with name <code>name</code>, <code>null</code> if such an object
	 * does not exist
	 */
	public PartialObject<A> getObject(String name) {
		for (PartialObject<A> object : getObjects()) {
			if (object.getName().equals(name)) {
				return object;
			}
		}
		return null;
	}
	
	/**
	 * Returns the object at the specified index.
	 * @param index index of the requested object
	 * @return the object at index <code>index</code>
	 */
	public PartialObject<A> getObjectAtIndex(int index) {
		return objects.getElementAt(index);
	}
	
	/**
	 * Returns the number of objects of this partial context.
	 * @return the number of objects in this partial context
	 */
	@Override
	public int getObjectCount() {
		return getObjects().size();
	}
	
	/**
	 * Removes a given object from the set of objects of this context.
	 * @param object the object to be removed
	 * @return <code>true</code> if <code>object</code> is successfully removed, <code>false</code>
	 * otherwise
	 * @throws IllegalObjectException if <code>object</code> does not exist
	 */
	@Override
	public boolean removeObject(String name) throws IllegalObjectException {
		boolean removed = getObjects().remove(getObject(name));
		if (!removed) {
			throw new IllegalObjectException("Object" + name + "not successfully removed");
		}
		return true;
	}
	
	/**
	 * Removes a given  object from the set of objects of this context.
	 * @param object the object to be removed
	 * @return <code>true</code> if the object <code>object</code> is successfully removed, 
	 * <code>false</code>  otherwise
	 * @throws IllegalObjectException if the object <code>object</code> does not exist
	 */
	@Override
	public boolean removeObject(PartialObject<A> object) throws IllegalObjectException {
		boolean removed = getObjects().remove(object);
		if (!removed) {
			throw new IllegalObjectException("Object" + object + "not successfully removed");
		}
		return true;
	}
	
	/**
	 * Checks whether this context refutes a given implication, i.e., there exists an object
	 * that does not respect the given implication.
	 * @param imp the implication to be tested
	 * @return <code>true</code> if all objects in this context respect <code>imp</code>, 
	 * <code>false</code> otherwise
	 */
	@Override
	public boolean refutes(FCAImplication<A> imp) {
		for (PartialObject<A> object: getObjects()) {
			if (!object.respects(imp)) {
				return true;
			}
		}
		return false;
	}
	
	/**
	 * Checks whether a counterexample provided by the expert is valid, i.e., it is really
	 * a counterexample to the given implication.
	 * @param counterExample the counterexample to be checked for validity
	 * @param imp the given implication
	 * @return <code>true</code> if <code>counterExample</code> is a valid counterexample to
	 * <code>imp</code>, <code>false</code> otherwise
	 */
	@Override
	public boolean isCounterExampleValid(PartialObject<A> counterExample, FCAImplication<A> imp) {
		if (counterExample.respects(imp)) {
			return false;
		}
		return true;
	}
	
	/** 
	 * Adds a given object to the set of objects of this context.
	 * @param o the object to be added
	 * @throws IllegalObjectException if an object with the name of <code>o</code> already exists in this
	 * context
	 * @return <code>true</code> 
	 */
	@Override
	public boolean addObject(PartialObject<A> o) throws IllegalObjectException {
		for (PartialObject<A> object : getObjects()) {
			if (object.getName().equals(o.getName())) {
				throw new IllegalObjectException("An object with name " + object.getName() + " already exists");
			}
		}
		getObjects().add(o);
		return true;
	}
	
	@Override
	public void clearObjects() {
		getObjects().clear();
	}
	
	/**
	 * Adds a given attribute to the attributes of the given object. 
	 * @param attribute the attribute to be added
	 * @param name name of the object where <code>attribute</code> is to be added
	 * @return <code>true</code> if of the <code>attribute</code> is successfully added, 
	 * <code>false</code> otherwise
	 * @throws IllegalAttributeException if the object <code>name</code> already has the attribute
	 * @throws IllegalObjectException if an object with name <code>name</code> does not exist in this 
	 * context
	 */
	public boolean addAttributeToObject(A attribute, String name) throws IllegalAttributeException,
	IllegalObjectException {
		if (!getAttributes().contains(attribute)) {
			throw new IllegalAttributeException("Attribute " + attribute + "does not exist");
		}
		if (!containsObject(name)) {
			throw new IllegalObjectException("Object " + name + "does not exist");
		}
		if (getObject(name).getDescription().containsAttribute(attribute)) {
			throw new IllegalAttributeException("Object already has attribute " + attribute); 
		}
		return getObject(name).getDescription().addAttribute(attribute);
	}
	
	/**
	 * Removes a given attribute from the attributes of the given object. 
	 * @param attribute the attribute to be removed
	 * @param name the name of the object from which  <code>attribute</code> is to be removed
	 * @return <code>true</code> if of the <code>attribute</code> is successfully removed, 
	 * <code>false</code> otherwise
	 * @throws IllegalAttributeException if the object <code>name</code>  does not have the attribute
	 * @throws IllegalObjectException if the an object with name <code>name</code> does not exist 
	 * in this context
	 */
	public boolean removeAttributeFromObject(A attribute, String name) throws IllegalAttributeException,
	IllegalObjectException {
		if (!getAttributes().contains(attribute)) {
			throw new IllegalAttributeException("Attribute " + attribute + "does not exist");
		}
		if (!containsObject(name)) {
			throw new IllegalObjectException("Object " + name + "does not exist");
		}
		if (!getObject(name).getDescription().containsAttribute(attribute)) {
			throw new IllegalAttributeException("Object does not have attribute " + attribute); 
		}
		return getObject(name).getDescription().removeAttribute(attribute);
	}
	
	/**
	 * Checks whether a specified object has the specified attribute.
	 * @param object the object given for check
	 * @param attribute the attribute given for check
	 * @return <code>true</code> if the <code>object</code> has the <code>attribute</code>
	 * <code>false</code> otherwise
	 */
	public boolean objectHasAttribute(PartialObject<A> object, A attribute) {
		return object.getDescription().containsAttribute(attribute);
	}
	
	// /**
	//  * Checks if this context contains an object with the given name.
	//  * @param name name of the object to be searched
	//  * @return <code>true</code> if an object with name <code>name</code> is found, 
	//  * <code>false</code> otherwise
	//  */
	// public boolean containsObject(String name) {
	// 	if (getObject(name) == null) {
	// 		return false;
	// 	}
	// 	else {
	// 		return true;
	// 	}
	// }
	
	/**
	 * Computes the second derivative of a given set of attributes.
	 * @param x the attribute set whose second derivative is to be computed
	 * @return the second derivative of <code>x</code>
	 */
	@Override
	public Set<A> doublePrime(Set<A> x) {
		Set<A> tmp = new HashSet<A>(getAttributes());
		
		for (PartialObject<A> object : getObjects()) {
			if (object.getDescription().containsAttributes(x)) {
				tmp.removeAll(object.getDescription().getNegatedAttributes());
			}
		}
		return tmp;
	}

	// /**
	//  * Performs the necessary operations when the expert confirms a question. For instance add the
	//  * confirmed implication to the base, and update the partial object descriptions with the new 
	//  * implications. 
	//  * @param question the question that the expert has confirmed
	//  */
	// protected void expertConfirmedQuestion(FCAImplication<A> question) {
	// 	getImplications().add(question);
	// 	// TODO: update the descriptions of the partial objects in the context!
	// }
	
	protected boolean followsFromBackgroundKnowledge(FCAImplication<A> implication) {
		// TODO: 
		return false;
	}
	
	public Set<FCAImplication<A>> getStemBase() {
		// TODO: 
		return null;
	}

	public Set<FCAImplication<A>> getDuquenneGuiguesBase() {
		// TODO: 
		return null;
	}
	
}
