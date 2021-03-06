/*
 * Copyright (c) 2007 Tom Parker <thpr@users.sourceforge.net>
 * 
 * This program is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 * 
 * This program is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 * 
 * You should have received a copy of the GNU Lesser General Public License
 * along with this library; if not, write to the Free Software Foundation, Inc.,
 * 51 Franklin Street, Fifth Floor, Boston, MA 02110-1301, USA
 */
package plugin.lsttokens.kit.ability;

import org.junit.Test;

import pcgen.cdom.enumeration.ListKey;
import pcgen.cdom.enumeration.Type;
import pcgen.core.Ability;
import pcgen.core.AbilityCategory;
import pcgen.core.kit.KitAbilities;
import pcgen.persistence.PersistenceLayerException;
import pcgen.rules.persistence.CDOMSubLineLoader;
import pcgen.rules.persistence.token.CDOMPrimaryToken;
import plugin.lsttokens.testsupport.AbstractKitTokenTestCase;

public class AbilityTokenTest extends AbstractKitTokenTestCase<KitAbilities>
{

	static AbilityToken token = new AbilityToken();
	static CDOMSubLineLoader<KitAbilities> loader = new CDOMSubLineLoader<>(
			"SKILL", KitAbilities.class);

	@Override
	public Class<KitAbilities> getCDOMClass()
	{
		return KitAbilities.class;
	}

	@Override
	public CDOMSubLineLoader<KitAbilities> getLoader()
	{
		return loader;
	}

	@Override
	public CDOMPrimaryToken<KitAbilities> getToken()
	{
		return token;
	}

	@Test
	public void testInvalidInputEmptyCount() throws PersistenceLayerException
	{
		assertTrue(parse("CATEGORY=FEAT|Fireball"));
		assertConstructionError();
	}

	@Test
	public void testRoundRobinSimple() throws PersistenceLayerException
	{
		Ability ab = primaryContext.getReferenceContext().constructCDOMObject(Ability.class,
				"Fireball");
		primaryContext.getReferenceContext().reassociateCategory(AbilityCategory.FEAT, ab);
		ab = secondaryContext.getReferenceContext()
				.constructCDOMObject(Ability.class, "Fireball");
		secondaryContext.getReferenceContext().reassociateCategory(AbilityCategory.FEAT, ab);
		runRoundRobin("CATEGORY=FEAT|Fireball");
	}

	@Test
	public void testRoundRobinType() throws PersistenceLayerException
	{
		Ability ab = primaryContext.getReferenceContext().constructCDOMObject(Ability.class,
				"Fireball");
		primaryContext.getReferenceContext().reassociateCategory(AbilityCategory.FEAT, ab);
		ab.addToListFor(ListKey.TYPE, Type.getConstant("Test"));
		ab = secondaryContext.getReferenceContext()
				.constructCDOMObject(Ability.class, "Fireball");
		secondaryContext.getReferenceContext().reassociateCategory(AbilityCategory.FEAT, ab);
		ab.addToListFor(ListKey.TYPE, Type.getConstant("Test"));
		runRoundRobin("CATEGORY=FEAT|TYPE=Test");
	}

	@Test
	public void testRoundRobinTwo() throws PersistenceLayerException
	{
		Ability ab = primaryContext.getReferenceContext().constructCDOMObject(Ability.class,
				"Fireball");
		primaryContext.getReferenceContext().reassociateCategory(AbilityCategory.FEAT, ab);
		ab = secondaryContext.getReferenceContext()
				.constructCDOMObject(Ability.class, "Fireball");
		secondaryContext.getReferenceContext().reassociateCategory(AbilityCategory.FEAT, ab);
		ab = primaryContext.getReferenceContext().constructCDOMObject(Ability.class, "English");
		primaryContext.getReferenceContext().reassociateCategory(AbilityCategory.FEAT, ab);
		ab = secondaryContext.getReferenceContext().constructCDOMObject(Ability.class, "English");
		secondaryContext.getReferenceContext().reassociateCategory(AbilityCategory.FEAT, ab);
		runRoundRobin("CATEGORY=FEAT|English" + getJoinCharacter() + "Fireball");
	}

	@Test
	public void testInvalidListEnd() throws PersistenceLayerException
	{
		assertFalse(parse("CATEGORY=FEAT|TestWP1" + getJoinCharacter()));
	}

	private char getJoinCharacter()
	{
		return '|';
	}

	@Test
	public void testInvalidListStart() throws PersistenceLayerException
	{
		assertFalse(parse("CATEGORY=FEAT|" + getJoinCharacter() + "TestWP1"));
	}

	@Test
	public void testInvalidListDoubleJoin() throws PersistenceLayerException
	{
		assertFalse(parse("CATEGORY=FEAT|TestWP2" + getJoinCharacter() + getJoinCharacter()
				+ "TestWP1"));
	}

	//TODO Doesn't test TYPE=
}
