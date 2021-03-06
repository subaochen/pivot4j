/*
 * ====================================================================
 * This software is subject to the terms of the Common Public License
 * Agreement, available at the following URL:
 *   http://www.opensource.org/licenses/cpl.html .
 * You must accept the terms of that agreement to use this software.
 * ====================================================================
 */
package org.pivot4j.mdx.metadata;

import org.olap4j.OlapException;
import org.olap4j.mdx.IdentifierNode;
import org.olap4j.metadata.Cube;
import org.olap4j.metadata.Member;
import org.pivot4j.PivotException;
import org.pivot4j.mdx.ExpVisitor;

public class MemberExp extends AbstractMetadataExp<Member> {

	private static final long serialVersionUID = 7794058991628436993L;

	public MemberExp() {
	}

	/**
	 * @param member
	 */
	public MemberExp(Member member) {
		super(member);
	}

	/**
	 * @param name
	 * @param uniqueName
	 */
	public MemberExp(String name, String uniqueName) {
		super(name, uniqueName);
	}

	/**
	 * @see org.pivot4j.mdx.metadata.AbstractMetadataExp#lookupMetadata(org.olap4j.metadata.Cube)
	 */
	@Override
	protected Member lookupMetadata(Cube cube) {
		try {
			return cube.lookupMember(IdentifierNode.parseIdentifier(
					getUniqueName()).getSegmentList());
		} catch (OlapException e) {
			throw new PivotException(e);
		}
	}

	/**
	 * @see org.pivot4j.mdx.Exp#accept(org.pivot4j.mdx.ExpVisitor)
	 */
	@Override
	public void accept(ExpVisitor visitor) {
		if (visitor instanceof MetadataExpVisitor) {
			((MetadataExpVisitor) visitor).visitMember(this);
		}
	}

	/**
	 * @see org.pivot4j.mdx.Exp#copy()
	 */
	public MemberExp copy() {
		return new MemberExp(getName(), getUniqueName());
	}
}
