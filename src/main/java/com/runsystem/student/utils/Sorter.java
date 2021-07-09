package com.runsystem.student.utils;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import com.runsystem.student.dto.StudentDTO;

public class Sorter {

	String sortName;
	String sortBy;
	List<StudentDTO> result;

	public Sorter(String sortName, String sortBy, List<StudentDTO> result) {
		super();
		this.sortName = sortName;
		this.sortBy = sortBy;
		this.result = result;
	}

	public List<StudentDTO> sort() {
		List<StudentDTO> sortedList = null;
//		Check sort by field and sort
		switch (sortName) {
		case "StudentName":
			sortedList = result.stream().sorted((o1, o2) -> {
				return o1.getStudentName().compareTo(o2.getStudentName());
			}).collect(Collectors.toList());
			break;
		case "StudentCode":
			sortedList = result.stream().sorted((o1, o2) -> {
				return o1.getStudentCode().compareTo(o2.getStudentCode());
			}).collect(Collectors.toList());
			break;
		case "Address":
			sortedList = result.stream().sorted((o1, o2) -> {
				return o1.getAddress().compareTo(o2.getAddress());
			}).collect(Collectors.toList());
			break;
		case "Score":
			sortedList = result.stream().sorted(Comparator.comparingDouble(StudentDTO::getAverageScore))
					.collect(Collectors.toList());
			break;
		default:
			break;
		}
//		Check sort Descending or Ascending
		if (sortBy.equals("DESC")) {
			Collections.reverse(sortedList);
		}
		return sortedList;
	}

}
