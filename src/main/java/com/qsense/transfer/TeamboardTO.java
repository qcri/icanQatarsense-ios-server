
package com.qsense.transfer;

import java.util.List;


public class TeamboardTO {

	List<TeamboardAttributesTO> groupTeamboardAttributes;
	
	private List<TeamboardAttributesTO> currentUserTeamboardAttributes;
	
	List<JournalTO> journal;
	
	List<JournalTO> topContributors;

	public List<TeamboardAttributesTO> getGroupTeamboardAttributes() {
		return groupTeamboardAttributes;
	}

	public void setGroupTeamboardAttributes(
			List<TeamboardAttributesTO> groupTeamboardAttributes) {
		this.groupTeamboardAttributes = groupTeamboardAttributes;
	}

	public List<JournalTO> getJournal() {
		return journal;
	}

	public void setJournal(List<JournalTO> journal) {
		this.journal = journal;
	}

	public List<JournalTO> getTopContributors() {
		return topContributors;
	}

	public void setTopContributors(List<JournalTO> topContributors) {
		this.topContributors = topContributors;
	}

	public List<TeamboardAttributesTO> getCurrentUserTeamboardAttributes() {
		return currentUserTeamboardAttributes;
	}

	public void setCurrentUserTeamboardAttributes(
			List<TeamboardAttributesTO> currentUserTeamboardAttributes) {
		this.currentUserTeamboardAttributes = currentUserTeamboardAttributes;
	}

}
