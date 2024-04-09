package edu.cjc.main.service;

import java.util.List;

import edu.cjc.main.model.Student;

public interface StudentService {

	public void saveStudentDetails( Student s);
	public List<Student> getAllStudents();
	
	public List<Student> searchStudentsByBatch(String batchNumber);
	public Student getSingleStudent(int id);
	public void updateStudentFees(int studentid, float amount);
	public Student updateBatch(int id);
	public void updateBatch(int studentid, String batchNumber);
	public List<Student> deleteStudent(int id);
}
