/**
 * 
 */
package com.hp.shopping.restservice;

import java.util.ArrayList;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hp.shopping.model.EmployeeModel;

/**
 * @author warhokar
 *
 */
@RestController
public class DialogFlowService {
	
	/*@Autowired
	private ResourceServerProperties ssoID;
	
	@Bean
	public ResourceServerTokenServices customUserInfoTokenServices() {
		System.out.println("User info URI : "+ssoID.getUserInfoUri()+"Client ID : "+ssoID.getClientId());
		return new CustomUserInfoTokenServices(ssoID.getUserInfoUri(),ssoID.getClientId());
	}*/
	
	@RequestMapping(path="/hello")
	public String helloWorld2() {
		return "Hello World !!";
	}
    
	//@PreAuthorize("#oauth2.hasScope('test_read') and hasAuthority('ROLE_OPERATOR','USER')")
	//@PreAuthorize("#oauth2.hasScope('test_read')")
	@RequestMapping("/employees")
	public ArrayList<EmployeeModel> getEmployeeData() {
		ArrayList<EmployeeModel> employees = new ArrayList<>();

		EmployeeModel employee1 = new EmployeeModel();
		employee1.setfName("AAA");
		employee1.setlName("ZZZ");
		employee1.setAge(10);
		employees.add(employee1);

		EmployeeModel employee2 = new EmployeeModel();
		employee2.setfName("BBB");
		employee2.setlName("YYY");
		employee2.setAge(20);

		employees.add(employee2);

		EmployeeModel employee3 = new EmployeeModel();
		employee3.setfName("CCC");
		employee3.setlName("XXX");
		employee3.setAge(30);
		employees.add(employee2);
		return employees;
	}
}
