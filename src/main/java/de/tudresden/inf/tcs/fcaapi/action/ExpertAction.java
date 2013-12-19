package de.tudresden.inf.tcs.fcaapi.action;

import java.awt.event.ActionEvent;

import de.tudresden.inf.tcs.fcaapi.Context;

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

/**
 * Interface describing actions that the expert can perform.
 * 
 * @author Baris Sertkaya Technische Universtaet Dresden
 *         sertkaya@tcs.inf.tu-dresden.de
 */

public interface ExpertAction {

	void actionPerformed(ActionEvent e);

	// <C extends Context<A,I,FCAObject<A,I>>> C getContext();
	// <C extends Context<?,?,?>> C getContext();
	Context<?, ?, ?> getContext();

}
