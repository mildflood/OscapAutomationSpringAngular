/**
 * Author Jonas  Okwara
 * Date 08-24-18
 * Class to convert input data
 * to appropriate formats
 */
package org.hcqis.ventech.cloud.automation.util;

import java.util.HashMap;
import java.util.Map;

public class InputConverterUtility {

	public InputConverterUtility() {

	}

	
	
	
	/**
	 * Convert form input for AM and PM into their appropriate String formats
	 * 
	 * @param formInput
	 * @return String
	 */
	public String convertAMPMInput(int formInput) {

		String inputParameter = null;

		if (formInput != 1) {

			inputParameter = "AM";

		} else {

			inputParameter = "PM";
		}

		return inputParameter;

	}

	/**
	 * Author Jonas Okwara Date 08-24-18
	 * 
	 * @param formInput
	 * @return
	 */
	public  String convertWeekDayInput(int formInput) {

		String inputParameter = null;

		Map<Integer, String> formatMap = new HashMap<>();
		formatMap.put(1, "Sunday");
		formatMap.put(2, "Monday");
		formatMap.put(3, "Tuesday");
		formatMap.put(4, "Wednesday");
		formatMap.put(5, "Thursday");
		formatMap.put(6, "Friday");
		formatMap.put(7, "Saturday");

		for (Map.Entry<Integer, String> eachFormat : formatMap.entrySet()) {

			if (formInput == eachFormat.getKey()) {
				inputParameter = eachFormat.getValue();

			}
		}
		return inputParameter;

	} 
	
	
	public String convertMonthInput(int formInput) { 
		
		String inputParameter = null;

		Map<Integer, String> formatMap = new HashMap<>();
		formatMap.put(0, "January");
		formatMap.put(1, "February");
		formatMap.put(2, "March");
		formatMap.put(3, "April");
		formatMap.put(4, "May");
		formatMap.put(5, "June");
		formatMap.put(6, "July");
		formatMap.put(7, "August");
		formatMap.put(8, "September");
		formatMap.put(9, "October");
		formatMap.put(10, "November");
		formatMap.put(11, "December");

		for (Map.Entry<Integer, String> eachFormat : formatMap.entrySet()) {

			if (formInput == eachFormat.getKey()) {
				
				inputParameter = eachFormat.getValue();

			}
		}
		return inputParameter;
	}

	

	
}
