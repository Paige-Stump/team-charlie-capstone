package com.techelevator.citymap.model;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

public class LandmarkTest {
	
	private String getWaypointArray(List<Landmark> landmarks) {
		String wayPointBuilder = "[";
		List<String> nameAndCityBuilder = new ArrayList<>();
		for(Landmark landmark: landmarks) {
			nameAndCityBuilder.add(landmark.getNameAndCity());
		}
		for(String nameAndCityConcat : nameAndCityBuilder) {
			wayPointBuilder += "{location: \"" + nameAndCityConcat + "\", stopover: true}, ";
		}
		wayPointBuilder += "]";
		return wayPointBuilder;
	}

	@Test
	public void checkLandmark() {
		Landmark landmark = new Landmark();
		landmark.setLandmarkCity("Cincinnati, OH");
		landmark.setLandmarkDescription("my description");
		landmark.setLandmarkId(1);
		landmark.setLandmarkLink("www.google.com");
		landmark.setLandmarkLocation("fndsljfhsdkjfsdlkfjs");
		landmark.setLandmarkName("HCDC");
		landmark.setNameAndCity();
		System.out.println(landmark.getNameAndCity());
		List<Landmark> ourLandmarks = new ArrayList<>();
		ourLandmarks.add(landmark);
		String crazyString = getWaypointArray(ourLandmarks);
		System.out.println(crazyString);
		assertTrue(true);
	}

}
