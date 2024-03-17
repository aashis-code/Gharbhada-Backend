package com.SpringBoot.GharBhada.Utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.SpringBoot.GharBhada.Algorithm.HaverSine;
import com.SpringBoot.GharBhada.Entity.Home;

public class FilterHome {

	@Autowired
	private HaverSine haverSine;

	public List<Home> getFilterHomes(double lattitude, double longitude, List<Home> homes) {
		List<Home> listOfHome = new ArrayList<>();
		List<double[]> locationList = new ArrayList<>();
		homes.stream().forEach((home) -> {
			double newLatitude = home.getLatitude();
			double newLongitude = home.getLongitude();
			double[] array = { newLatitude, newLongitude };
			locationList.add(array);
		});
		List<double[]> locations = haverSine.getLocations(lattitude, longitude, locationList);

		homes.stream().forEach((home) -> {
			double newLatitude = home.getLatitude();
			double newLongitude = home.getLongitude();
			double[] array = { newLatitude, newLongitude };
//			if (locations.contains(array)) { --> contains check for reference equality
//				listOfHome.add(home);
//			}
			if(locations.stream().anyMatch( arr -> Arrays.equals(arr, array))) {
				listOfHome.add(home);
			}

		});

		return listOfHome;
	}

}
