/*
 * Copyright 2010(C) James Dempsey
 *
 * This library is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 2.1 of the License, or (at your option) any later version.
 *
 * This library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this library; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA 02111-1307 USA
 *
 *
 */
package pcgen.gui2.prefs;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.BorderFactory;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;

import pcgen.core.SettingsHandler;
import pcgen.gui2.tools.Utility;
import pcgen.system.LanguageBundle;
import pcgen.system.PCGenSettings;

/**
 * The Class {@code LevelUpPanel} is responsible for
 * displaying levelling up related preferences and allowing the 
 * preferences to be edited by the user.
 * 
 * 
 */
@SuppressWarnings("serial")
public class LevelUpPanel extends PCGenPrefsPanel
{
	private static final String in_levelUp =
		LanguageBundle.getString("in_Prefs_levelUp");

//	private static String in_enforceSpending =
//		LanguageBundle.getString("in_Prefs_enforceSpending");
//	private static String in_hpWindow =
//		LanguageBundle.getString("in_Prefs_hpWindow");
	private static final String in_statWindow =
		LanguageBundle.getString("in_Prefs_statWindow");
	private static final String in_warnFirstLevelUp =
		LanguageBundle.getString("in_Prefs_warnFirstLevelUp");

//	private JCheckBox hpDialogShownAtLevelUp = new JCheckBox();
	private final JCheckBox showWarningAtFirstLevelUp = new JCheckBox();
	private final JCheckBox statDialogShownAtLevelUp = new JCheckBox();
//	private JCheckBox enforceSpendingBeforeLevelUp = new JCheckBox();
	//	private JCheckBox featDialogShownAtLevelUp = new JCheckBox();
	
	/**
	 * Instantiates a new leveling up panel.
	 */
	public LevelUpPanel()
	{
		GridBagLayout gridbag = new GridBagLayout();
		GridBagConstraints c = new GridBagConstraints();
		JLabel label;
		Border etched = null;
		TitledBorder title1 =
				BorderFactory.createTitledBorder(etched, in_levelUp);

		title1.setTitleJustification(TitledBorder.LEFT);
		this.setBorder(title1);
		gridbag = new GridBagLayout();
		this.setLayout(gridbag);
		c = new GridBagConstraints();
		c.fill = GridBagConstraints.HORIZONTAL;
		c.anchor = GridBagConstraints.LINE_START;
		c.insets = new Insets(2, 2, 2, 2);

//		Utility.buildConstraints(c, 0, 0, 2, 1, 0, 0);
//		label = new JLabel(in_hpWindow + ": ");
//		gridbag.setConstraints(label, c);
//		this.add(label);
//		Utility.buildConstraints(c, 2, 0, 1, 1, 0, 0);
//		gridbag.setConstraints(hpDialogShownAtLevelUp, c);
//		this.add(hpDialogShownAtLevelUp);

		//		Utility.buildConstraints(c, 0, 1, 2, 1, 0, 0);
		//		label = new JLabel(in_featWindow + ": ");
		//		gridbag.setConstraints(label, c);
		//		this.add(label);
		//		Utility.buildConstraints(c, 2, 1, 1, 1, 0, 0);
		//		gridbag.setConstraints(featDialogShownAtLevelUp, c);
		//		this.add(featDialogShownAtLevelUp);

		Utility.buildConstraints(c, 0, 2, 2, 1, 0, 0);
		statDialogShownAtLevelUp.setText(in_statWindow);
		gridbag.setConstraints(statDialogShownAtLevelUp, c);
		this.add(statDialogShownAtLevelUp);

		Utility.buildConstraints(c, 0, 3, 2, 1, 0, 0);
		showWarningAtFirstLevelUp.setText(in_warnFirstLevelUp);
		gridbag.setConstraints(showWarningAtFirstLevelUp, c);
		this.add(showWarningAtFirstLevelUp);

//		Utility.buildConstraints(c, 0, 4, 2, 1, 0, 0);
//		label = new JLabel(in_enforceSpending + ": ");
//		gridbag.setConstraints(label, c);
//		this.add(label);
//		Utility.buildConstraints(c, 2, 4, 1, 1, 0, 0);
//		gridbag.setConstraints(enforceSpendingBeforeLevelUp, c);
//		this.add(enforceSpendingBeforeLevelUp);

		Utility.buildConstraints(c, 5, 20, 1, 1, 1, 1);
		c.fill = GridBagConstraints.BOTH;
		label = new JLabel(" ");
		gridbag.setConstraints(label, c);
		this.add(label);
	}

	/**
	 * @see pcgen.gui2.prefs.PCGenPrefsPanel#getTitle()
	 */
	@Override
	public String getTitle()
	{
		return in_levelUp;
	}
	
	/**
	 * @see pcgen.gui2.prefs.PreferencesPanel#applyPreferences()
	 */
	@Override
	public void setOptionsBasedOnControls()
	{
//		SettingsHandler.setShowHPDialogAtLevelUp(hpDialogShownAtLevelUp
//			.isSelected());
		//SettingsHandler.setShowFeatDialogAtLevelUp(featDialogShownAtLevelUp.isSelected());
		SettingsHandler.setShowStatDialogAtLevelUp(statDialogShownAtLevelUp
			.isSelected());
		PCGenSettings.OPTIONS_CONTEXT.setBoolean(
			PCGenSettings.OPTION_SHOW_WARNING_AT_FIRST_LEVEL_UP,
			showWarningAtFirstLevelUp.isSelected());
//		SettingsHandler.setShowWarningAtFirstLevelUp(showWarningAtFirstLevelUp
//			.isSelected());
//		SettingsHandler
//			.setEnforceSpendingBeforeLevelUp(enforceSpendingBeforeLevelUp
//				.isSelected());
	}

	/**
	 * @see pcgen.gui2.prefs.PreferencesPanel#initPreferences()
	 */
	@Override
	public void applyOptionValuesToControls()
	{
//		hpDialogShownAtLevelUp.setSelected(SettingsHandler
//			.getShowHPDialogAtLevelUp());
		//featDialogShownAtLevelUp.setSelected(SettingsHandler.getShowFeatDialogAtLevelUp());
		statDialogShownAtLevelUp.setSelected(SettingsHandler
			.getShowStatDialogAtLevelUp());
		showWarningAtFirstLevelUp.setSelected(PCGenSettings.OPTIONS_CONTEXT
			.getBoolean(PCGenSettings.OPTION_SHOW_WARNING_AT_FIRST_LEVEL_UP,
				true));
		//		enforceSpendingBeforeLevelUp.setSelected(SettingsHandler
//			.getEnforceSpendingBeforeLevelUp());
	}

}
