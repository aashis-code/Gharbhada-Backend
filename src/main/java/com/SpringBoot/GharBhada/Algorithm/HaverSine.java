package com.SpringBoot.GharBhada.Algorithm;

import java.util.ArrayList;
import java.util.List;

public class HaverSine {
	
	public  List<double[]> getLocations(double lattitude, double longitude,List<double[]> locations)
	{
			
			List<double[]> locationsList = new ArrayList<>();	
			
			for (double[] location : locations) {
				double array [ ] = new double [2];
				double lattitude2 = location[0];
				double longitude2 = location[1];
				
				// distance between latitudes and longitudes
				double dLat = Math.toRadians(lattitude2 - lattitude);
				double dLon = Math.toRadians(longitude2 - longitude);
				
				// convert to radians
				double newLattitude = Math.toRadians(lattitude);
				double newlattitude2 = Math.toRadians(lattitude2);
				
				// apply formulae
				double a = Math.pow(Math.sin(dLat / 2), 2) + 
				   Math.pow(Math.sin(dLon / 2), 2) * 
				   Math.cos(newLattitude) * 
				   Math.cos(newlattitude2);
				double rad = 6371;
				double c = 2 * Math.asin(Math.sqrt(a));
				
				//Calculating the distance in KiloMeter
				double distance = rad * c;
				
				if(distance <= 30) {
					array[0] = lattitude2;
					array[1] = longitude2;
					locationsList.add(array);
				}
			}

	return locationsList;
	}

}
