/**
 * An implication set implementation.
 * @author Baris Sertkaya
 */
package de.tudresden.inf.tcs.fcalib;

import java.util.BitSet;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Set;

import de.tudresden.inf.tcs.fcaapi.ClosureOperator;
// import de.tudresden.inf.tcs.fcaapi.FCAObject;
import de.tudresden.inf.tcs.fcaapi.FCAImplication;

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

public class ImplicationSet<A> extends LinkedHashSet<FCAImplication<A>>
		implements ClosureOperator<A> {

	private static final long serialVersionUID = 1L;

	/**
	 * The base context of this implication set.
	 */
	// final private GenericContext<A,? extends FCAObject<A>> theContext;
	// final private GenericContext<A> theContext;
	final private AbstractContext<A, ?, ?> theContext;

	/**
	 * To keeps track of in which premises an attribute occurs.
	 */
	private final Hashtable<A, Set<FCAImplication<A>>> occursInPremises;

	/**
	 * Creates an empty implication set.
	 * 
	 * @param context
	 *            context
	 */
	// public ImplicationSet(GenericContext<A,? extends FCAObject<A>> context) {
	public ImplicationSet(AbstractContext<A, ?, ?> context) {
		super();
		this.theContext = context;
		this.occursInPremises = new Hashtable<A, Set<FCAImplication<A>>>();
		for (A attr : context.getAttributes()) {
			this.occursInPremises.put(attr, new HashSet<FCAImplication<A>>());
		}
	}

	/**
	 * Returns the base context of this implication set.
	 * 
	 * @return the base context
	 */
	// public GenericContext<A,? extends FCAObject<A>> getContext() {
	public AbstractContext<A, ?, ?> getContext() {
		return this.theContext;
	}

	/**
	 * Adds an implication to this implication set.
	 * 
	 * @param imp
	 *            the implication to be added
	 * @return <code>true</code> if the implication is added, <code>false</code>
	 *         if it already exists
	 */
	@Override
	public boolean add(FCAImplication<A> imp) {
		Set<FCAImplication<A>> tmp;
		for (A attr : imp.getPremise()) {
			tmp = this.occursInPremises.get(attr);
			tmp.add(imp);
			this.occursInPremises.put(attr, tmp);
		}
		return (super.add(imp));
	}

	/**
	 * Computes the closure of a given attribute set under this implication set.
	 * Implementation the linear closure algorithm.
	 * 
	 * @param x
	 *            the attribute set to be closed
	 * @return the closure of <code>x</code> under this implication set
	 */
	@Override
	public Set<A> closure(Set<A> x) {
		Set<A> update = new HashSet<A>(x);
		Set<A> newDep = new LinkedHashSet<A>(x);
		Hashtable<FCAImplication<A>, Integer> premiseSizes = new Hashtable<FCAImplication<A>, Integer>();

		// update.addAll(x);
		// newDep.addAll(x);

		for (FCAImplication<A> imp : this) {
			premiseSizes.put(imp, imp.getPremise().size());
			if (imp.getPremise().isEmpty()) {
				newDep.addAll(imp.getConclusion());
				update.addAll(imp.getConclusion());
			}
		}
		// int impId, attrId;
		A attr;
		while (!update.isEmpty()) {
			// attrId = update.firstElement();
			Iterator<A> it = update.iterator();
			attr = it.next();
			// update.removeIndex(attrId);
			update.remove(attr);
			// for (int i = 0; i < elementOfPremises[attrId].size(); i++) {
			for (FCAImplication<A> imp : this.occursInPremises.get(attr)) {
				// impId = elementOfPremises[attrId].get(i);
				// tmpPremiseSizes.set(impId, tmpPremiseSizes.get(impId) - 1);
				int tmp = premiseSizes.get(imp);
				premiseSizes.put(imp, --tmp);
				// if (tmpPremiseSizes.get(impId) == 0) {
				if (tmp == 0) {
					// add = (get(impId)).getConclusion().difference(newDep);
					// newDep.addAll(add);
					// update.addAll(add);
					update.addAll(imp.getConclusion());
					update.removeAll(newDep);
					newDep.addAll(imp.getConclusion());
				}
			}
		}
		return newDep;
	}

	/**
	 * Checks if a given attribute set is closed under this set of implications.
	 * 
	 * @param x
	 *            the attribute set to be checked
	 * @return <code>true</code> if <code>x</code> is closed, <code>false</code>
	 *         otherwise
	 */
	@Override
	public boolean isClosed(Set<A> x) {
		return x.equals(closure(x));
	}

	/**
	 * Converts a given set into its bit vector representation.
	 * 
	 * @param s
	 *            the set to be converted to bit vector representation
	 * @return the bit vector representation of <code>s</code>
	 */
	private BitSet setToBitVector(Set<A> s) {
		BitSet b = new BitSet(this.theContext.getAttributeCount());

		for (int i = 0; i < this.theContext.getAttributeCount(); ++i) {
			if (s.contains(this.theContext.getAttributeAtIndex(i))) {
				b.set(i);
			}
		}
		return b;
	}

	/**
	 * Converts a given bit vector into its set representation.
	 * 
	 * @param b
	 *            the bit vector to be converted to set representation
	 * @return the set representation of <code>b</code>
	 */
	public Set<A> bitVectorToSet(BitSet b) {
		Set<A> s = new LinkedHashSet<A>();

		for (int i = 0; i < this.theContext.getAttributeCount(); ++i) {
			if (b.get(i)) {
				s.add(this.theContext.getAttributeAtIndex(i));
			}
		}
		return s;
	}

	/**
	 * Given an attribute set <code>x</code>, returns the closed set coming
	 * after <code>x</code> in the lectic order of the closed sets of this
	 * implication set.
	 * 
	 * @param x
	 *            attribut set
	 * @return the closed set coming after <code>x</code>
	 */
	public Set<A> nextClosure(Set<A> x) {
		// AttributeSet<A> tmp = x.clone();
		BitSet tmp = setToBitVector(x);

		// if (x.cardinality() == theContext.getAttributeCount()) {
		// return null;
		// }
		if (x.size() == this.theContext.getAttributeCount()) {
			return null;
		}
		label: for (int i = this.theContext.getAttributeCount() - 1; i >= 0; i--) {
			// if (tmp.containsIndex(i)) {
			if (tmp.get(i)) {
				// tmp.removeIndex(i);
				tmp.flip(i);
			} else {
				// tmp.addIndex(i);
				tmp.flip(i);
				// tmp = closure(tmp);
				tmp = setToBitVector(closure(bitVectorToSet(tmp)));
				// for (int j = tmp.nextElement(0); j >= 0 && j < i; j =
				// tmp.nextElement(j + 1)) {
				for (int j = tmp.nextSetBit(0); (j >= 0) && (j < i); j = tmp
						.nextSetBit(j + 1)) {
					// if (!x.containsIndex(j)) {
					if (!x.contains(this.theContext.getAttributeAtIndex(j))) {
						// tmp = x.clone();
						tmp = setToBitVector(x);
						// tmp.removeIndex(i, theContext.getAttributeCount());
						tmp.clear(i, this.theContext.getAttributeCount());
						continue label;
					}
				}
				return bitVectorToSet(tmp);
			}
		}
		return bitVectorToSet(tmp);
	}

	/**
	 * Returns the set of closed sets of this implication set that are
	 * lectically bigger than <code>x</code>.
	 * 
	 * @param x
	 *            the starting attribute set
	 * @return set of closed sets that are lectically bigger than <code>x</code>
	 */
	public Set<Set<A>> closuresStartingFrom(Set<A> x) {
		Set<A> tmp;
		Set<Set<A>> result = new LinkedHashSet<Set<A>>();

		tmp = closure(x);
		result.add(tmp);
		tmp = nextClosure(tmp);
		while (tmp != null) {
			result.add(tmp);
			tmp = nextClosure(tmp);
		}
		return result;
	}

	/**
	 * Returns the set of all closed sets of this implication set.
	 * 
	 * @return the set of all closed sets of this implication set
	 */
	@Override
	public Set<Set<A>> allClosures() {
		return closuresStartingFrom(new HashSet<A>());
	}

}
