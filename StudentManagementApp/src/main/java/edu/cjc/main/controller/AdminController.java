package edu.cjc.main.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import edu.cjc.main.model.Student;
import edu.cjc.main.service.StudentService;


@Controller
public class AdminController {

	@Autowired
	StudentService ss;
	
	@RequestMapping("/")
	public String preLogin() {
		return "login";
	}
	
	@RequestMapping("/login")
	public String login(@RequestParam String username,@RequestParam String password, Model m) {
		if(username.equals("admin") && password.equals("admin123")) {
			List<Student> students=ss.getAllStudents();
			m.addAttribute("data",students);
			return "adminscreen";
		}
		else {
			m.addAttribute("login_fail","Enter Valid Login Details.");
			return "login";
			
		}
		
	}
	
	
	@RequestMapping("/enroll_student")
	public String saveStudent(@ModelAttribute Student s,Model m) {
		ss.saveStudentDetails(s);
		List<Student> list=ss.getAllStudents();
		m.addAttribute("data",list);
		return "adminscreen";
	}
	
	@RequestMapping("/search")
	public String getBatchStudent(@RequestParam String batchNumber,Model m) {
		List<Student> result=ss.searchStudentsByBatch(batchNumber);
		if(result.size()>0) {
			m.addAttribute("data",result);
		}else {
			List<Student> students=ss.getAllStudents();
			m.addAttribute("data",students);
			m.addAttribute("message","No Records are available for the batch '"+batchNumber+"'...! ");
		}
		return "adminscreen";
		
	}
	
	
	
	
	@RequestMapping("/fees")
	public String onFees(@RequestParam int id,Model m) {
		Student st=ss.getSingleStudent(id);
		m.addAttribute("st",st);
		return "fees";
	}
	
	@RequestMapping("/payfees")
	public String payFees(@RequestParam int studentid,@RequestParam float amount,Model m) {
		ss.updateStudentFees(studentid,amount);
		List<Student> list=ss.getAllStudents();
		m.addAttribute("data",list);
		return "adminscreen";
	}
	
	@RequestMapping("/batch")
	public String editBatch(@RequestParam int id,Model m) {
		Student st=ss.updateBatch(id);
		m.addAttribute("st",st);
		return "batch";
	}
	
	@RequestMapping("/editbatch")
	public String updateBatch(@RequestParam int studentid,@RequestParam String batchNumber,Model m) {
		ss.updateBatch(studentid,batchNumber);
		List<Student> list=ss.getAllStudents();
		m.addAttribute("data",list);
		return "adminscreen";
	}
	
	@RequestMapping("/remove")
	public String deleteStudent(@RequestParam int id,Model m) {
		List<Student> list=ss.deleteStudent(id);
		m.addAttribute("data",list);
		return "adminscreen";
	}
}
