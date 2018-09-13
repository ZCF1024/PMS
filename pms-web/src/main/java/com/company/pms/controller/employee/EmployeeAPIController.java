package com.company.pms.controller.employee;

import com.company.pms.pmsbase.utils.ExportExcelUtil;
import com.company.pms.pmsbase.utils.FieldUtils;
import com.company.pms.pmsbase.web.GenericController;
import com.company.pms.pmsrepository.employee.domain.Employee;
import com.company.pms.pmsrepository.employee.domain.PositionTypeEnum;
import com.company.pms.pmsservice.employee.EmployeeManager;

import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.List;

@RestController
@RequestMapping("api/employee")
public class EmployeeAPIController extends GenericController<Employee, Long, EmployeeManager> {

	private final Logger logger = LoggerFactory.getLogger(EmployeeAPIController.class);

	@Value("${save_file_path}")
	private String SAVE_PATH;

	private EmployeeManager employeeManager;

	/**
	 * 测试方法
	 * @return
	 */
	@GetMapping("index")
	public String index() {
		return "Employee Index";
	}

	/**
	 * 通过员工姓名获取员工信息
	 * @param name
	 * @return
	 */
	@GetMapping("name/{name}")
	public List<Employee> getEmployeeByName(@PathVariable String name){
		return this.employeeManager.findAllByNameLike(name);
	}

	/**
	 * 通过年龄获取员工信息
	 * @param age1
	 * @param age2
	 * @return
	 */
	@GetMapping("getbyage")
	public List<Employee> getEmployeeByAgeBetween(@RequestParam(name = "age1", defaultValue = "0") int age1,
												  @RequestParam(name = "age2", defaultValue = "200") int age2){
		return this.employeeManager.findAllByAgeBetween(age1, age2);
	}

	/**
	 * 通过员工职位查找员工
	 * @param position
	 * @return
	 */
	@GetMapping("position/{position}")
	public List<Employee> getEmployeeByPosition(@PathVariable int position){
		return this.employeeManager.findAllByPosition(PositionTypeEnum.values()[position]);
	}

	@GetMapping("gender/{gender}")
	public List<Employee> getEmployeeByGender(@PathVariable Boolean gender){
		return this.employeeManager.findAllByGender(gender);
	}

	@GetMapping("loginbyname")
	public List<Employee> getEmployeeByNameAndPassword(@RequestParam(name = "name") String name,
													   @RequestParam(name = "password") String passwoed){
		return this.employeeManager.findAllByNameAndPassword(name, passwoed);
	}

    @GetMapping("loginbyphone")
    public List<Employee> getEmployeeByPhoneAndPassword(@RequestParam(name = "phone") String phone,
                                                       @RequestParam(name = "password") String passwoed){
        return this.employeeManager.findAllByPhoneAndPassword(phone, passwoed);
    }

	@GetMapping("export")
	public ResponseEntity<byte[]> export(){
		try{
			List<Employee> list = this.employeeManager.findAll(false);
		    String fileName = SAVE_PATH + "员工信息表.xls";
			OutputStream out = new FileOutputStream(new File(fileName));

			ExportExcelUtil<Employee> exportExcel = new ExportExcelUtil<>();
			exportExcel.exportExcel("员工信息", FieldUtils.EMPLOYEE_FIELDS, list, out, null);

			//将导出的文件输出到响应的信息中（即为下载）
			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(MediaType.APPLICATION_OCTET_STREAM); // 设置响应方式

			headers.setContentDispositionFormData("attachment",
					URLEncoder.encode("员工信息表.xls", "UTF-8")); // 设置响应文件

			return new ResponseEntity<byte[]>(FileUtils.readFileToByteArray(new File(fileName)),
					headers, HttpStatus.CREATED); // 把文件以二进制形式写回
		}catch (Exception e){
			logger.error(e.getMessage());
		}
		return null;
	}

	/**
	 * 下载文件
	 * @return
	 */
	@GetMapping("download")
	public ResponseEntity<byte[]> download(){
		try{
			String fileName = "C:\\Users\\Administrator\\Desktop\\突出显示续订的库存清单1.xlsx";

			//将导出的文件输出到响应的信息中（即为下载）
			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(MediaType.APPLICATION_OCTET_STREAM); // 设置响应方式

			headers.setContentDispositionFormData("attachment",
					URLEncoder.encode("下载Demo文件.xlsx", "UTF-8")); // 设置响应文件

			return new ResponseEntity<byte[]>(FileUtils.readFileToByteArray(new File(fileName)),
					headers, HttpStatus.CREATED); // 把文件以二进制形式写回
		}catch (Exception e){
			logger.error(e.getMessage());
		}
		return null;
	}

	@Autowired
	public void setEmployeeManager(EmployeeManager employeeManager) {
		this.employeeManager = employeeManager;
		this.manager = this.employeeManager;
	}

}
