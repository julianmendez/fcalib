package de.tudresden.inf.tcs.fcalib.action;

import java.awt.event.ActionEvent;

import org.apache.log4j.Logger;

import de.tudresden.inf.tcs.fcaapi.FCAObject;

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
* The expert action that changes the linear order between the attributes.
* @author Baris Sertkaya
* Technische Universtaet Dresden
* sertkaya@tcs.inf.tu-dresden.de
*/
public class ChangeAttributeOrderAction<A,I,O extends FCAObject<A,I>> extends AbstractExpertAction<A,I,O> {
	
	/**
	 * The logger.
	 */
	private static final Logger logger = Logger.getLogger(ChangeAttributeOrderAction.class);
	
	private static final long serialVersionUID = 1L;
	
	/**
	 * Changes the linear order on the attribute set
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		getContext().getAttributes().changeOrder();
		logger.info("Attribute order changed:" + getContext().getAttributes());
	}
	
}