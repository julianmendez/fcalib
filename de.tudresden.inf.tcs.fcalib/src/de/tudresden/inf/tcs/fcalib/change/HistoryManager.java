package de.tudresden.inf.tcs.fcalib.change;

import java.util.ArrayList;

import de.tudresden.inf.tcs.fcaapi.change.ContextChange;


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


public class HistoryManager<A,B extends ContextChange<A>> extends ArrayList<ContextChange<A>> {
	
	private static final long serialVersionUID = 1L;

	public void push(ContextChange<A> change) {
		add(change);
	}
	
	public void undo(ContextChange<A> change) {
		change.undo();
		remove(change);
	}
	
	public void undo(int index) {
		get(index).undo();
		remove(index);
	}
	
	public void undoLast() {
		undo(size()-1);
	}
	
	public void undoLastN(int n) {
		for (int i = 0; i < n; ++i) {
			undoLast();
		}
	}
	
	public void undoAll() {
		undoLastN(size());
	}
	
}
