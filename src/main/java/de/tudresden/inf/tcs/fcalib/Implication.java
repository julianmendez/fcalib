/**
 * An implementation of implications in FCA.
 * @author Baris Sertkaya
 */
package de.tudresden.inf.tcs.fcalib;

import java.util.Set;
import java.util.HashSet;

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

public class Implication<T> implements FCAImplication<T> {
	
	/**
	 * premise and conclusion of this implication
	 */
	final private Set<T> premise, conclusion;
	
	/**
	 * Creates a new implication with empty premise and conclusion.
	 */
	public Implication() {
		premise = new HashSet<>();
		conclusion = new HashSet<>();
	}
	
	/**
	 * Creates a new implication with the specified premise and conclusion.
	 * @param p premise 
	 * @param c conclusion
	 */
	public Implication(Set<T> p, Set<T> c) {
		premise = p;
		conclusion = c;
	}
	
	/**
	 * Returns the conclusion of this implication.
	 * @return the conclusion
	 */
	public Set<T> getConclusion() {
		return conclusion;
	}
	
	/**
	 * Returns the premise of this implication.
	 * @return the premise
	 */
	public Set<T> getPremise() {
		return premise;
	}
	
	/** 
	 * Checks if this implication is equal to a given implication.
	 * @param imp implication to be checked for equality
	 * @return <code>true</code> if this implication is equal to <code>imp</code>, 
	 * false otherwise
	 */
	public boolean equals(FCAImplication<T> imp) {
		return premise.equals(imp.getPremise()) && conclusion.equals(imp.getConclusion());
	}
	
	/**
	 *  Returns a string representation of this implication.
	 * @return a string representation
	 */
	public String toString() {
		return premise + " -> " + conclusion;
	}
	
}
