/**
 *  MicroEmulator
 *  Copyright (C) 2001-2007 Bartek Teodorczyk <barteo@barteo.net>
 *
 *  This library is free software; you can redistribute it and/or
 *  modify it under the terms of the GNU Lesser General Public
 *  License as published by the Free Software Foundation; either
 *  version 2.1 of the License, or (at your option) any later version.
 *
 *  This library is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 *  Lesser General Public License for more details.
 *
 *  You should have received a copy of the GNU Lesser General Public
 *  License along with this library; if not, write to the Free Software
 *  Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA  02111-1307  USA
 *
 *  @version $Id$
 */
package net.sf.bluecove.util;

import java.util.NoSuchElementException;

/**
 * Simple implementation of java.util.StringTokenizer for J2ME Taken from
 * com.pyx4me.core.utils
 * 
 * 
 * @author vlads
 */

public class J2MEStringTokenizer {

	private int currentPosition;

	private int newPosition;

	private String str;

	private String delimiter;

	/**
	 * Constructs a string tokenizer for the specified string. The characters in
	 * the delim argument are the delimiters for separating tokens. Delimiter
	 * characters themselves will not be treated as tokens.
	 * 
	 * @param str
	 *            a string to be parsed
	 * @param delimiter
	 *            the delimiter
	 */
	public J2MEStringTokenizer(String str, String delimiter) {
		this.str = str;
		this.delimiter = delimiter;
		this.currentPosition = 0;
		nextPosition();
	}

	/**
	 * @return True, if there is a token left
	 */
	public boolean hasMoreTokens() {
		return (newPosition != -1) && (currentPosition < newPosition);
	}

	private void nextPosition() {
		this.newPosition = this.str.indexOf(this.delimiter, this.currentPosition);
		if (this.newPosition == -1) {
			this.newPosition = this.str.length();
		} else if (this.newPosition == this.currentPosition) {
			// Zero len token
			this.currentPosition += 1;
			nextPosition();
		}
	}

	/**
	 * 
	 * @return Next token
	 * @throws NoSuchElementException
	 *             If there is no token left
	 */
	public String nextToken() throws NoSuchElementException {
		if (!hasMoreTokens()) {
			throw new NoSuchElementException();
		}

		String next = this.str.substring(this.currentPosition, this.newPosition);

		this.currentPosition = this.newPosition + 1;
		nextPosition();
		return next;
	}
}