package edu.cjc.main.serviceimpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.cjc.main.model.Student;
import edu.cjc.main.repository.StudentRepository;
import edu.cjc.main.service.StudentService;

@Service
public class StudentServiceImpl implements StudentService {

	@Autowired
	private StudentRepository sr;
	
	@Override
	public void saveStudentDetails(Student s) {
		// TODO Auto-generated method stub
		sr.save(s);
	}
	
	public List<Student> getAllStudents(){
		return sr.findAll();
	}

	@Override
	public List<Student> searchStudentsByBatch(String batchNumber) {
		// TODO Auto-generated method stub
		List<Student> batchStudents=sr.findAllByBatchNumber(batchNumber);
		return batchStudents;
	}

	@Override
	public Student getSingleStudent(int id) {
		Optional<Student> opstudent=sr.findById(id);
		return opstudent.get();
	}

	@Override
	public void updateStudentFees(int studentid, float amount) {
		// TODO Auto-generated method stub
		Optional<Student> opStudent=sr.findById(studentid);
		Student st=opStudent.get();
		st.setFeesPaid(st.getFeesPaid()+amount);
		sr.save(st);
		
	}

	@Override
	public Student updateBatch(int id) {
		// TODO Auto-generated method stub
		Optional<Student> opstudent=sr.findById(id);
		return opstudent.get();
	}

	@Override
	public void updateBatch(int studentid, String batchNumber) {
		// TODO Auto-generated method stub
		Optional<Student> opStudent=sr.findById(studentid);
		Student st=opStudent.get();
		st.setBatchNumber(batchNumber);
		sr.save(st);

	}

	@Override
	public List<Student> deleteStudent(int id) {
		// TODO Auto-generated method stub
		Optional<Student> opStudent=sr.findById(id);
		Student st=opStudent.get();
		sr.delete(st);
		return getAllStudents();
	
	}

}
