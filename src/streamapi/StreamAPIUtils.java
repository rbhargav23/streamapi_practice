package streamapi;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.DoubleSummaryStatistics;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

public class StreamAPIUtils {

	public static void main(String[] args) {
		List<Employee> employeeList = new ArrayList<Employee>();
        
		employeeList.add(new Employee(111, "Jiya Brein", 32, "Female", "HR", 2011, 25000.0));
		employeeList.add(new Employee(122, "Paul Niksui", 25, "Male", "Sales And Marketing", 2015, 13500.0));
		employeeList.add(new Employee(133, "Martin Theron", 29, "Male", "Infrastructure", 2012, 18000.0));
		employeeList.add(new Employee(144, "Murali Gowda", 28, "Male", "Product Development", 2014, 32500.0));
		employeeList.add(new Employee(155, "Nima Roy", 27, "Female", "HR", 2013, 22700.0));
		employeeList.add(new Employee(166, "Iqbal Hussain", 43, "Male", "Security And Transport", 2016, 10500.0));
		employeeList.add(new Employee(177, "Manu Sharma", 35, "Male", "Account And Finance", 2010, 27000.0));
		employeeList.add(new Employee(188, "Wang Liu", 31, "Male", "Product Development", 2015, 34500.0));
		employeeList.add(new Employee(199, "Amelia Zoe", 24, "Female", "Sales And Marketing", 2016, 11500.0));
		employeeList.add(new Employee(200, "Jaden Dough", 38, "Male", "Security And Transport", 2015, 11000.5));
		employeeList.add(new Employee(211, "Jasna Kaur", 27, "Female", "Infrastructure", 2014, 15700.0));
		employeeList.add(new Employee(222, "Nitin Joshi", 25, "Male", "Product Development", 2016, 28200.0));
		employeeList.add(new Employee(233, "Jyothi Reddy", 27, "Female", "Account And Finance", 2013, 21300.0));
		employeeList.add(new Employee(244, "Nicolus Den", 24, "Male", "Sales And Marketing", 2017, 10700.5));
		employeeList.add(new Employee(255, "Ali Baig", 23, "Male", "Infrastructure", 2018, 12700.0));
		employeeList.add(new Employee(266, "Sanvi Pandey", 26, "Female", "Product Development", 2015, 28900.0));
		employeeList.add(new Employee(277, "Anuj Chettiar", 31, "Male", "Product Development", 2012, 35700.0));
		StreamAPIUtils.oldestEmployee(employeeList);
		System.out.println("*******************************");
		StreamAPIUtils.avgAgeOfMaleAndFemaleEmployees(employeeList);
		System.out.println("*******************************");
		StreamAPIUtils.avgSalAndTotalSal(employeeList);
		System.out.println("*******************************");
		StreamAPIUtils.avgSalarayByDept(employeeList);
		System.out.println("*******************************");
		StreamAPIUtils.avgSalByGender(employeeList);
		System.out.println("*******************************");
		StreamAPIUtils.empListByDept(employeeList);
		System.out.println("*******************************");
		StreamAPIUtils.groupByGender(employeeList);
		System.out.println("*******************************");
		StreamAPIUtils.highestPaidEmployee(employeeList);
		System.out.println("*******************************");
		StreamAPIUtils.joinedAfter2015(employeeList);
		System.out.println("*******************************");
		StreamAPIUtils.maleAndFemaleCountinSalesAndMarketing(employeeList);
		System.out.println("*******************************");
		StreamAPIUtils.nofempInDept(employeeList);
		System.out.println("*******************************");
		StreamAPIUtils.partitionByAge25(employeeList);
		System.out.println("*******************************");
		StreamAPIUtils.printAllDepts(employeeList);
		System.out.println("*******************************");
		StreamAPIUtils.seniorEmployee(employeeList);
		System.out.println("*******************************");
		StreamAPIUtils.youngestMaleEmployeeInProductDevelopment(employeeList);
		System.out.println("*******************************");
	} 
	
	public static void groupByGender(List<Employee> list) {
		Map<String, Long> noOfMalAndFemaleEmployees = list.stream().collect(Collectors.groupingBy(Employee::getGender, Collectors.counting()));
		System.out.println(noOfMalAndFemaleEmployees);
	}
	
	public static void printAllDepts(List<Employee> list) {
		list.stream().map(Employee::getDepartment).distinct().forEach(System.out::println);
	}
	
	public static void avgAgeOfMaleAndFemaleEmployees(List<Employee> list) {
		Map<String, Double> map = list.stream().collect(Collectors.groupingBy(Employee::getGender,Collectors.averagingInt(Employee::getAge)));
		System.out.println(map);
	}
	
	public static void highestPaidEmployee(List<Employee> list) {
		Optional<Employee> optional = list.stream().collect(Collectors.maxBy(Comparator.comparingDouble(Employee::getSalary)));
		Employee highestPaidEmployee = optional.get();
        
		System.out.println("Details Of Highest Paid Employee : ");
		         
		System.out.println("==================================");
		         
		System.out.println("ID : "+highestPaidEmployee.getId());
		         
		System.out.println("Name : "+highestPaidEmployee.getName());
		         
		System.out.println("Age : "+highestPaidEmployee.getAge());
		         
		System.out.println("Gender : "+highestPaidEmployee.getGender());
		         
		System.out.println("Department : "+highestPaidEmployee.getDepartment());
		         
		System.out.println("Year Of Joining : "+highestPaidEmployee.getYearOfJoining());
		         
		System.out.println("Salary : "+highestPaidEmployee.getSalary());
	}
	
	public static void joinedAfter2015(List<Employee> list) {
		list.stream().filter((emp) -> emp.getYearOfJoining() > 2015).map(Employee::getName).forEach(System.out::println);
	}
	
	public static void nofempInDept(List<Employee> list) {
		
		Map<String, Long> empByDept = list.stream().collect(Collectors.groupingBy(Employee::getDepartment, Collectors.counting()));
		Set<Entry<String, Long>> entrySet = empByDept.entrySet();
        
		for (Entry<String, Long> entry : entrySet)
		{
		    System.out.println(entry.getKey()+" : "+entry.getValue());
		}
	}
	
	public static void avgSalarayByDept(List<Employee> list) {
		Map<String, Double> avgSalByDept = list.stream().collect(Collectors.groupingBy(Employee::getDepartment, Collectors.averagingDouble(Employee::getSalary)));
		Set<Entry<String, Double>> entrySet = avgSalByDept.entrySet();
        
		for (Entry<String, Double> entry : entrySet)
		{
		    System.out.println(entry.getKey()+" : "+entry.getValue());
		}
	}
	
	public static void youngestMaleEmployeeInProductDevelopment(List<Employee> list) {
		Optional<Employee> optional = list.stream().filter(emp -> emp.getGender().equals("Male") && emp.getDepartment().equals("Product Development")).min(Comparator.comparingInt(Employee::getAge));

		Employee youngestMaleEmployeeInProductDevelopment = optional.get();

		System.out.println("Details Of Youngest Male Employee In Product Development");

		System.out.println("----------------------------------------------");

		System.out.println("ID : " + youngestMaleEmployeeInProductDevelopment.getId());

		System.out.println("Name : " + youngestMaleEmployeeInProductDevelopment.getName());

		System.out.println("Age : " + youngestMaleEmployeeInProductDevelopment.getAge());

		System.out.println("Year Of Joinging : " + youngestMaleEmployeeInProductDevelopment.getYearOfJoining());

		System.out.println("Salary : " + youngestMaleEmployeeInProductDevelopment.getSalary());
	
	}
	
	public static void seniorEmployee(List<Employee> list) {
		Optional<Employee> optional = list.stream().sorted(Comparator.comparingInt(Employee::getYearOfJoining)).findFirst();
		Employee seniorMostEmployee = optional.get();
        
		System.out.println("Senior Most Employee Details :");
		         
		System.out.println("----------------------------");
		         
		System.out.println("ID : "+seniorMostEmployee.getId());
		         
		System.out.println("Name : "+seniorMostEmployee.getName());
		         
		System.out.println("Age : "+seniorMostEmployee.getAge());
		         
		System.out.println("Gender : "+seniorMostEmployee.getGender());
		         
		System.out.println("Age : "+seniorMostEmployee.getDepartment());
		         
		System.out.println("Year Of Joinging : "+seniorMostEmployee.getYearOfJoining());
		         
		System.out.println("Salary : "+seniorMostEmployee.getSalary());
	}

	public static void maleAndFemaleCountinSalesAndMarketing(List<Employee> list) {
		Map<String, Long> map = list.stream().filter(emp -> emp.getDepartment().equals("Sales And Marketing")).collect(Collectors.groupingBy(Employee::getGender, Collectors.counting()));
		System.out.println(map);
	}
	
	public static void avgSalByGender(List<Employee> list) {
		Map<String, Double> map = list.stream().collect(Collectors.groupingBy(Employee::getGender, Collectors.averagingDouble(Employee::getSalary)));
		System.out.println(map);
	}
	
	public static void empListByDept(List<Employee> list) {
		Map<String, List<Employee>> map = list.stream().collect(Collectors.groupingBy(Employee::getDepartment));
		Set<Entry<String, List<Employee>>> entrySet = map.entrySet();
        
		for (Entry<String, List<Employee>> entry : entrySet) 
		{
		    System.out.println("--------------------------------------");
		             
		    System.out.println("Employees In "+entry.getKey() + " : ");
		             
		    System.out.println("--------------------------------------");
		             
		    List<Employee> li = entry.getValue();
		             
		    for (Employee e : li) 
		    {
		        System.out.println(e.getName());
		    }
		}
	}
	
	public static void avgSalAndTotalSal(List<Employee> list) {
		DoubleSummaryStatistics doubleSummaryStatistics = list.stream().collect(Collectors.summarizingDouble(Employee::getSalary));
	
		doubleSummaryStatistics.getAverage();
		doubleSummaryStatistics.getMin();
		doubleSummaryStatistics.getSum();
		
		System.out.println("Average Salary = " + doubleSummaryStatistics.getAverage());

		System.out.println("Total Salary = " + doubleSummaryStatistics.getSum());
			
		
	}
	
	
	public static void partitionByAge25(List<Employee> list) {
		Map<Boolean, List<Employee>> map = list.stream().collect(Collectors.partitioningBy(emp -> emp.getAge() > 25));
		Set<Entry<Boolean, List<Employee>>> entrySet = map.entrySet();
        
		for (Entry<Boolean, List<Employee>> entry : entrySet) 
		{
		    System.out.println("----------------------------");
		             
		    if (entry.getKey()) 
		    {
		        System.out.println("Employees older than 25 years :");
		    }
		    else
		    {
		        System.out.println("Employees younger than or equal to 25 years :");
		    }
		             
		    System.out.println("----------------------------");
		             
		    List<Employee> li = entry.getValue();
		             
		    for (Employee e : li) 
		    {
		        System.out.println(e.getName());
		    }
		}
	}
	
	public static void oldestEmployee(List<Employee> list) {
		
		Optional<Employee> optionaltwo = list.stream().max(Comparator.comparingInt(Employee::getAge));
		Employee oldestEmployee = optionaltwo.get();
        
		System.out.println("Name : "+oldestEmployee.getName());
		         
		System.out.println("Age : "+oldestEmployee.getAge());
		         
		System.out.println("Department : "+oldestEmployee.getDepartment());
	
	}
}
