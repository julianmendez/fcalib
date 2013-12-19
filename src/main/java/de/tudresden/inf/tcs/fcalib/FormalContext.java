package de.tudresden.inf.tcs.fcalib;

import java.util.Set;
import java.util.HashSet;

import de.tudresden.inf.tcs.fcaapi.utils.IndexedSet;
import de.tudresden.inf.tcs.fcaapi.FCAImplication;
import de.tudresden.inf.tcs.fcaapi.ClosureOperator;
import de.tudresden.inf.tcs.fcaapi.Concept;
import de.tudresden.inf.tcs.fcaapi.Expert;
import de.tudresden.inf.tcs.fcaapi.exception.IllegalObjectException;
import de.tudresden.inf.tcs.fcaapi.exception.IllegalAttributeException;
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
 * Formal context implementation extending {@link de.tudresden.inf.tcs.fcalib.AbstractContext}
 * @author Baris Sertkaya
 * Technische Universtaet Dresden
 * sertkaya@tcs.inf.tu-dresden.de
*/
public class FormalContext<A,I> extends AbstractContext<A,I,FullObject<A,I>> implements ClosureOperator<A> {

	/**
	 * The objects of this formal context.
	 */
	protected IndexedSet<FullObject<A,I>> objects;
	
	/**
	 * The expert for this formal context.
	 */
	protected Expert<A,I,FullObject<A,I>> expert = null;
	
	/**
	 * Creates a formal context with empty set of attributes and objects.
	 */
	public FormalContext() {
		objects = new ListSet<FullObject<A,I>>();
	}
	
	/** 
	 * Adds a given object to the set of objects of this context.
	 * @param o the object to be added
	 * @throws IllegalObjectException if an object with the name of <code>o</code> already exists in this
	 * context
	 * @return <code>true</code> 
	 */
	@Override
	public boolean addObject(FullObject<A,I> o) throws IllegalObjectException {
		for (FullObject<A,I> object : objects) {
			if (object.getIdentifier().equals(o.getIdentifier())) {
				throw new IllegalObjectException("An object with name " + object.getName() + " already exists");
			}
		}
		return objects.add(o);
	}
	
	/** 
	 * Returns the set of objects of this context.
	 * @return the set of objects
	 */
	public IndexedSet<FullObject<A,I>> getObjects() {
		return objects;
	}
	
	/**
	 * Returns the object at a specified index.
	 * @param index index of the object that is required
	 * @return the object at index <code>index</code>
	 */
	public FullObject<A,I> getObjectAtIndex(int index) {
		return objects.getElementAt(index);
	}
	
	// /**
	//  * Checks if this context contains a given object.
	//  * @param object the object to be searched
	//  * @return <code>true</code> the <code>object</code> is found, <code>false</code> otherwise
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
	 * Returns the object whose identifier is given.
	 * @param id the given identifier
	 * @return the object with identifier <code>id</code>, <code>null</code> if such an object
	 * does not exist
	 */
	public FullObject<A,I> getObject(I id) {
		for (FullObject<A,I> object : getObjects()) {
			if (object.getIdentifier().equals(id)) {
				return object;
			}
		}
		return null;
	}
	
	/**
	 * Removes the object from the set of objects of this context whose identifer is given.
	 * @param id identifer of the object to be removed
	 * @return <code>true</code> if the object with identifer <code>id</code> is successfully removed, 
	 * <code>false</code>  otherwise
	 * @throws IllegalObjectException if an object with identifer <code>id</code> does not exist
	 */
	@Override
	public boolean removeObject(I id) throws IllegalObjectException {
		boolean removed = getObjects().remove(getObject(id));
		if (!removed) {
			throw new IllegalObjectException("Object" + id + "not successfully removed");
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
	public boolean removeObject(FullObject<A,I> object) throws IllegalObjectException {
		boolean removed = getObjects().remove(object);
		if (!removed) {
			throw new IllegalObjectException("Object" + object.getIdentifier() + "not successfully removed");
		}
		return true;
	}
	
	
	/**
	 * Clears the set of objects.
	 */
	public void clearObjects() {
		objects.clear();
	}
	
	/**
	 * Adds a given attribute to the attributes of the given object. 
	 * @param attribute the attribute to be added
	 * @param id the identifer of the object where <code>attribute</code> is to be added
	 * @return <code>true</code> if of the <code>attribute</code> is successfully added, 
	 * <code>false</code> otherwise
	 * @throws IllegalAttributeException if <code>object</code> already has the attribute
	 * @throws IllegalObjectException if the object with identifier <code>id</code> does not exist in this 
	 * context
	 */
	@Override
	public boolean addAttributeToObject(A attribute, I id) throws IllegalAttributeException,
	IllegalObjectException {
		if (!getAttributes().contains(attribute)) {
			throw new IllegalAttributeException("Attribute " + attribute + "does not exist");
		}
		if (!containsObject(id)) {
			throw new IllegalObjectException("Object " + id + "does not exist");
		}
		if (getObject(id).getDescription().containsAttribute(attribute)) {
			throw new IllegalAttributeException("Object already has attribute " + attribute); 
		}
		return getObject(id).getDescription().addAttribute(attribute);
	}
	/**
	 * Removes a given attribute from the attributes of the given object. 
	 * @param attribute the attribute to be added
	 * @param id identifier of the object where <code>attribute</code> is to be removed from
	 * @return <code>true</code> if of the <code>attribute</code> is successfully added, 
	 * <code>false</code> otherwise
	 * @throws IllegalAttributeException if <code>object</code> already has the attribute
	 * @throws IllegalObjectException if the object with identifier <code>id</code> does not exist in this 
	 * context
	 */
	public boolean removeAttributeFromObject(A attribute, I id) throws IllegalAttributeException,
	IllegalObjectException {
		if (!getAttributes().contains(attribute)) {
			throw new IllegalAttributeException("Attribute " + attribute + "does not exist");
		}
		if (!containsObject(id)) {
			throw new IllegalObjectException("Object " + id + "does not exist");
		}
		if (!getObject(id).getDescription().containsAttribute(attribute)) {
			throw new IllegalAttributeException("Object does not have attribute " + attribute); 
		}
		return getObject(id).getDescription().removeAttribute(attribute);
	}
	
	/**
	 * Checks whether a specified object has the specified attribute.
	 * @param obj the object given for check
	 * @param attribute the attribute given for check
	 * @return <code>true</code> if the object with identifier <code>id</code> has the <code>attribute</code>
	 * <code>false</code> otherwise
	 */
	public boolean objectHasAttribute(FullObject<A,I> obj, A attribute) {
		return obj.getDescription().containsAttribute(attribute);
	}
	
	/**
	 * Computes the second derivative of a given set of attributes.
	 * @param x the attribute set whose second derivative is to be computed
	 * @return the second derivative of <code>x</code>
	 */
	@Override
	public Set<A> doublePrime(Set<A> x) {
		Set<A> tmp = new HashSet<A>(getAttributes());
		
		for (FullObject<A,I> object : getObjects()) {
			if (object.getDescription().containsAttributes(x)) {
				tmp.retainAll(object.getDescription().getAttributes());
			}
		}
		return tmp;
	}
	
	/**
	 * Computes the closure of a given attribute set in this formal context, i.e., its second
	 * derivative. (just a renaming for de.tudresden.inf.tcs.fcalib.FormalContext#doublePrime)
	 * @param x the set whose closure is going to be computed
	 * @return closure of <code>x</code>
	 */
	public Set<A> closure(Set<A> x) {
		return doublePrime(x);
	}
	
	/**
	 * Checks whether a given attribute set is closed, i.e., whether it is equal to its second
	 * derivative
	 * @param x the set that is to be checked for closedness
	 * @return <code>true</code> if <code>x</code> is closed
	 */
	public boolean isClosed(Set<A> x) {
		return x.equals(closure(x));
	}
	
	/**
	 * Computes all closed attribute sets of this formal context, i.e., all concept intents.
	 * @return all closed attribute sets
	 */
	public Set<Set<A>> allClosures() {
		return null;
	}

	/**
	 * Computes the stem base of this formal context.
	 * @return the stem base of this formal context
	 */
	public Set<FCAImplication<A>> getStemBase() {
		// TODO Auto-generated method stub
		return null;
	}

	
	/**
	 * Computes the Duquenne Guigues base of this formal context.
	 * @return the Duquenne Guigues base of this formal context
	 * @see de.tudresden.inf.tcs.fcalib.FormalContext#getStemBase()
	 */
	public Set<FCAImplication<A>> getDuquenneGuiguesBase() {
		// TODO Auto-generated method stub
		return null;
	}
	
	/**
	 * Returns the set of intents of this context.
	 * @return the set of intents
	 */
	public Set<Set<A>> getIntents() {
		return allClosures();
	}
	
	/**
	 * Returns the set of extents of this context.
	 * @return the set of extents
	 */
	public Set<FullObject<A,I>> getExtents() {
		return null;
	}

	/**
	 * Returns the set of formal concepts of this context.
	 * @return the set of formal concepts
	 */
	public Set<Concept<A,FullObject<A,I>>> getConcepts() {
		return null;
	}
	
	/**
	 * Returns the set of formal concepts of this context.
	 * @return the set of formal concepts
	 */
	public Set<Concept<A,FullObject<A,I>>> getConceptLattice() {
		return null;
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
		for (FullObject<A,I> object: objects) {
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
	public boolean isCounterExampleValid(FullObject<A,I> counterExample, FCAImplication<A> imp) {
		if (counterExample.respects(imp)) {
			return false;
		}
		return true;
	}

	@Override
	public boolean followsFromBackgroundKnowledge(FCAImplication<A> implication) {
		// TODO:
		return false;
	}
	
	/**
	 * Sets the expert for this context to the given expert
	 * @param e the given expert
	 */
	public void setExpert(Expert<A,I,FullObject<A,I>> e) {
		expert = e;
	}
	
	/**
	 * Returns the expert of this context.
	 * @return the expert of this context
	 */
	public Expert<A,I,FullObject<A,I>> getExpert() {
		return expert;
	}
}
