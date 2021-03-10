package de.tudresden.inf.tcs.fcalib.test;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

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

public class TestListSet {

	public TestListSet() {
	}

	private void assertEq(ListSet<String> ls, List<String> list) {
		Assertions.assertTrue(ls.equals(list));
		Assertions.assertEquals(ls.size(), list.size());
		Assertions.assertTrue(ls.containsAll(list));
		Assertions.assertTrue(list.containsAll(ls));
		for (int i = 0; i < ls.size(); i++) {
			Assertions.assertTrue(ls.getElementAt(i).equals(list.get(i)));
		}
	}

	@Test
	public void testListSet() {
		ListSet<String> ls = new ListSet<>();
		ls.add("a");
		ls.add("b");
		ls.add("c");
		ls.add("d");

		List<String> list0 = new ArrayList<>();
		list0.add("a");
		list0.add("b");
		list0.add("c");
		list0.add("d");

		List<String> list1 = new ArrayList<>();
		list1.add("b");
		list1.add("c");
		list1.add("d");
		list1.add("a");

		List<String> list2 = new ArrayList<>();
		list2.add("c");
		list2.add("d");
		list2.add("a");
		list2.add("b");

		List<String> list3 = new ArrayList<>();
		list3.add("d");
		list3.add("a");
		list3.add("b");
		list3.add("c");

		assertEq(ls, list0);
		ls.changeOrder();
		assertEq(ls, list1);
		ls.changeOrder();
		assertEq(ls, list2);
		ls.changeOrder();
		assertEq(ls, list3);
		ls.changeOrder();
		assertEq(ls, list0);
		ls.changeOrder();
		assertEq(ls, list1);
	}

}
