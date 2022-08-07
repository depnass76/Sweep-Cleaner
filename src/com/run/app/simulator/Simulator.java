package com.run.app.simulator;
import java.util.Map;

import org.w3c.dom.Document;

import com.run.app.cleansweep.CleanSweep;
import com.run.app.cleansweep.FloorPlan;
import com.run.app.cleansweep.LoggingUtility;
import com.run.app.cleansweep.Tile;

public class Simulator {
	public static void main(String[] args) {
		String filePath;
		if (args.length == 0) {
			filePath = "floorplans//samplehome0.xml";
		} else {
			filePath = args[0];
		}

		try {
			Document doc = FloorPlanUtility.loadFromFile(filePath);
			FloorPlan floorPlan = FloorPlanUtility.loadFloorPlan(doc);
			Tile homeTile = null;
			for (int i = 0; i < floorPlan.numberOfFloors(); i++) {
				Tile home = floorPlan.getFloor(i).getHomeTile();
				if (home != null) {
					homeTile = home;
				}
			}

			if (homeTile != null) {
				CleanSweep cs = new CleanSweep(homeTile);

				LoggingUtility.logStart();

				Map<Integer, Tile> m = cs.cleanFloor();

				LoggingUtility.logInternalMap(m);

				LoggingUtility.logFinish();
			}
		} catch (Exception e) {
			System.err.println("Exception in main: " + e.getMessage());
			e.printStackTrace();
		}
	}
}
