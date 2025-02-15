package com.train.ticket.reservation.domain.train;

public class TrainSection {
	private Section sectionA = new Section("SectionA");
	private Section sectionB = new Section("SectionB");

	public Section getSectionA() {
		return sectionA;
	}

	public Section getSectionB() {
		return sectionB;
	}

}
