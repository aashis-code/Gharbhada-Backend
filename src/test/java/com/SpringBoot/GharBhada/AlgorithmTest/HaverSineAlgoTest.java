package com.SpringBoot.GharBhada.AlgorithmTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import com.SpringBoot.GharBhada.Algorithm.HaverSine;

public class HaverSineAlgoTest {

	
	@Test
	public void haverSineAlgoTest() {
		HaverSine haverSine = new HaverSine();
		
		 // Test data
        double inputLatitude = 10.0;
        double inputLongitude = 20.0;
        
        List<double[]> locations = new ArrayList<>();
        locations.add(new double[]{10.1, 20.1});
        locations.add(new double[]{10.2, 20.2});
        
        List<double[]> result = haverSine.getLocations(inputLatitude, inputLongitude, locations);
        
        assertEquals(1, result.size());
        
//        assertEquals(10.1, result.get(0)[0], 0.0001);
//        assertEquals(20.1, result.get(0)[1], 0.0001);
		
	}
}
