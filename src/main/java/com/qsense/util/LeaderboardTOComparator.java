package com.qsense.util;

import java.util.Comparator;

import com.qsense.transfer.LeaderboardTO;

public class LeaderboardTOComparator implements Comparator<LeaderboardTO> {
	/**
	 * Comparator to sort LeaderboardTO list or array in order of
	 * sortOrder
	 */

	@Override
	public int compare(LeaderboardTO a1, LeaderboardTO a2) {
		return a1.getSortOrder().compareTo(a2.getSortOrder());
	}
}
