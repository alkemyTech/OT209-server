package com.alkemy.ong.utility;

import java.util.Comparator;

import com.alkemy.ong.models.entity.Slide;

public class ComparatorSlide implements Comparator<Slide>{

	@Override
	public int compare(Slide o1, Slide o2) {
		
		return o1.getSlideOrder() - o2.getSlideOrder();
	}

}
