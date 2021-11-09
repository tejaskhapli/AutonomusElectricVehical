package com.auto.ele.veh.application.request;

import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Component;
import com.auto.ele.veh.exception.InsufficientFuelException;
import com.auto.ele.veh.model.route.ChargingStation;
import lombok.NoArgsConstructor;

/**
 * This class contains logic to fetch the minimum required charging stations from Source To Destination.
 * 
 * @author tejaskhapli
 *
 */
@Component
@NoArgsConstructor
public class ChargingStationFinder {

  public List<ChargingStation> getVisitingStations(List<ChargingStation> csList) throws InsufficientFuelException {

    ChargingStation src = csList.get(0);
    ChargingStation dest = csList.get(csList.size() - 1);
    List<ChargingStation> visitedList = new ArrayList<>();

    // No Charging Station Required
    if (src.getLimit() >= dest.getDistance()) {

      return visitedList;
    } else {

      // Start from Source
      int curPos = 0;
      int availableChargeLevel = 0;

      while (curPos < csList.size() - 1) {

        ChargingStation curStn = csList.get(curPos);
        int curLimit = curStn.getLimit() + availableChargeLevel;
        int remDis = dest.getDistance() - curStn.getDistance();

        if (curLimit >= remDis) {
          return visitedList;
        }

        // Find indexes of all the stations upto which vehical can travel with current charge level.
        // Store those stations in temp array
        List<Integer> scopeList = getReachableStations(curPos, csList, curLimit);

        if (scopeList.isEmpty()) {
          throw new InsufficientFuelException("Unable to reach the destination with the current fuel level");
        }

        // Post Processing
        int nextVisNodeIndex = getNextVisitedNode(scopeList, csList, curLimit, curStn);
        int distanceTravelled = csList.get(nextVisNodeIndex).getDistance() - curStn.getDistance();
        availableChargeLevel = curLimit - distanceTravelled;
        curPos = nextVisNodeIndex;

        visitedList.add(csList.get(curPos));
      }
    }

    return visitedList;
  }

  private List<Integer> getReachableStations(int curPos, List<ChargingStation> csList, int curLimit) {

    List<Integer> scopeList = new ArrayList<>();
    for (int i = curPos + 1; i < csList.size() - 1; i++) {

      int disFromCurNode = csList.get(i).getDistance() - csList.get(curPos).getDistance();

      if (disFromCurNode <= curLimit) {
        scopeList.add(i);
      }
    }
    return scopeList;
  }

  private int getNextVisitedNode(List<Integer> indexList, List<ChargingStation> csList, int initLimit,
          ChargingStation baseStn) {

    ChargingStation dest = csList.get(csList.size() - 1);
    int remDis = 0;
    int min = Integer.MAX_VALUE;
    int resIndex = 0;

    for (int i = 0; i < indexList.size(); i++) {

      ChargingStation curStn = csList.get(indexList.get(i));
      remDis = dest.getDistance() - curStn.getDistance();
      int curLimit = initLimit - (curStn.getDistance() - baseStn.getDistance()) + curStn.getLimit();

      if (remDis <= curLimit) {
        return indexList.get(i);
      }

      if (min > remDis - (curStn.getLimit() + curLimit)) {
        min = remDis - (curStn.getLimit() + curLimit);
        resIndex = indexList.get(i);
      }

    }
    return resIndex;
  }
}
