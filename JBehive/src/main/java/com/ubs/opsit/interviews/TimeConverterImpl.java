package com.ubs.opsit.interviews;

import java.util.Arrays;

public class TimeConverterImpl implements TimeConverter{
	
	private static final String FIVE_HOUR_ROW = "0000";
	private static final String ONE_HOUR_ROW = "0000";
	private static final String FIVE_MINUTES_ROW = "OOOOOOOOOOO";
	private static final String ONE_MINUTE_ROW = "OOOO";
	private static final String RED = "R";
	private static final String YELLOW = "Y";
	private static final String OFF = "O";

	public String convertTime(String aTime) {
		/*
		 * Split time HH:MM:SS on colon and obtain hours, minutes and seconds for processing
		 */
		String[] timeComponents = aTime.split(":");
	    int  hours = Integer.parseInt(timeComponents[0]);
	    int  minutes =  Integer.parseInt(timeComponents[1]);
	    int  seconds =  Integer.parseInt(timeComponents[2]);
	    // Array to hold individual time components
	    String[] timeComps = new String[]{determineLampsForSecond(seconds), determineLampsForFiveHours(hours), determineLampsForOneHour(hours), determineLampsForFiveMinutes(minutes), determineLampsForOneMinute(minutes)};
		return Arrays.toString(timeComps);
	}
	
	public static void main(String[] args){
		System.out.println("Berlin Time - " + new TimeConverterImpl().convertTime("20:37:10"));
	}

	/**
	 * Determine how many five hour lamps are on
	 * @param hours
	 * @return
	 */
	private static String determineLampsForFiveHours(int hours) {
	    StringBuilder result = new StringBuilder(FIVE_HOUR_ROW);

	    for (int i = 0; i < (hours / 5); i++) {
	      result.replace(i, i + 1, RED);
	    }
	    return result.toString();
	  }

	/**
	 * Determine how many one hour lamps are on
	 * @param hours
	 * @return
	 */
	  private static String determineLampsForOneHour(int hours) {
	    StringBuilder result = new StringBuilder(ONE_HOUR_ROW);

	    for (int i = 0; i < (hours % 5); i++) {
	      result.replace(i, i + 1, RED);
	    }
	    return result.toString();
	  }

	  /**
	   * Determine how many five minutes lamps are on
	   * @param minutes
	   * @return
	   */
	  private static String determineLampsForFiveMinutes(int minutes) {
	    StringBuilder result = new StringBuilder(FIVE_MINUTES_ROW);

	    for (int i = 0; i < (minutes / 5); i++) {
	      if ((i + 1) % 3 == 0) {
	        result.replace(i, i + 1, RED);
	      } else {
	        result.replace(i, i + 1, YELLOW);
	      }
	    }
	    return result.toString();
	  }

	  
	  /**
	   * Determine how many one minute lamps are on
	   * @param minutes
	   * @return
	   */
	  private static String determineLampsForOneMinute(int minutes) {
	    StringBuilder result = new StringBuilder(ONE_MINUTE_ROW);

	    for (int i = 0; i < (minutes % 5); i++) {
	      result.replace(i, i + 1, YELLOW);
	    }
	    return result.toString();
	  }

	  /**
	   * Determine second lamps
	   * @param seconds
	   * @return
	   */
	  private static String determineLampsForSecond(int seconds) {
	    if (seconds % 2 == 0) {
	      return YELLOW;
	    }
	    return OFF;
	  }
	
}
