package com.deppon.crm.module.customer.server.testutils;

import java.io.IOException;
import java.lang.reflect.Method;

import com.deppon.crm.module.customer.server.utils.Assert;

public class TestClassCreateUtils {
	
	private String testEntity ;
	
	public  String createTestClassContext(Class clazz){
		testEntity = beginLowser(clazz.getSimpleName());
		
		StringBuffer sb = new StringBuffer();
		sb.append("package "+clazz.getPackage().getName()+";\n\n");
		sb.append(getImportPacke());
		sb.append("public class "+clazz.getSimpleName()+"Test{\n");
		sb.append("\t"+clazz.getSimpleName()+" "+testEntity+ " = null;\n\n");
		sb.append(getSetUp(clazz)+"\n");
		Method[] methods = clazz.getDeclaredMethods();
		for (Method method : methods) {
			sb.append(createMethod(method));
		}
		sb.append("}\n");
		return sb.toString();
	}
	
	private Object getImportPacke() {
		StringBuffer sb = new StringBuffer();
		sb.append("import org.junit.Before;\n");
		sb.append("import org.junit.Test;\n\n");
		sb.append("import com.deppon.crm.module.customer.server.utils.SpringTestHelper;\n");
		return sb.toString();
	}

	private String getSetUp(Class clazz) {
		StringBuffer sb = new StringBuffer();
		sb.append("\t@Before\n");
		sb.append("\tpublic void setUp(){\n");
		sb.append("\t\t"+testEntity+" = SpringTestHelper.get().getBean("+getFirstUpperCase(testEntity)+".class);\n");
		sb.append("\t}\n");
		return sb.toString();
	}

	public  String createMethod(Method method){
		StringBuffer sb = new StringBuffer();
		sb.append("\t@Test\n");
		sb.append("\tpublic void test"+getFirstUpperCase(method.getName())+"(){\n");
		sb.append(getMethodCode(method));
		sb.append("\t}\n");
		return sb.toString();
	}
	
	public String getMethodCode(Method method){
		StringBuffer sb = new StringBuffer();
		Class[] clazzs = method.getParameterTypes();
		StringBuffer par = new StringBuffer();
		for (Class clazz : clazzs) {
			sb.append("\t\t"+getParameterCode(clazz));
			par.append(beginLowser(clazz.getSimpleName()+","));
		}
		sb.append("\t\t"+testEntity+"."+method.getName());
		if(par.length() != 0){
			sb.append("("+par.substring(0,par.length()-1)+");\n");
		}else{
			sb.append("();\n");
		}
		
		return sb.toString();
	}
	
	private String getParameterCode(Class clazz) {
		StringBuffer sb = new StringBuffer();
		sb.append(clazz.getSimpleName()+" "+beginLowser(clazz.getSimpleName())+ " = null;\n");
		return sb.toString();
	}

	public  String getFirstUpperCase(String name){
		Assert.notEmpty(name, "name must not empty");
		String first = name.substring(0,1);
		String other = name.substring(1);
		return first.toUpperCase()+other;
	}
	
	private static String beginLowser(String name){
		Assert.notEmpty(name, "name must not empty");
		String startName = name.substring(0,1);
		String otherName = name.substring(1);
		return  startName.toLowerCase() + otherName;
	}
	
	public void createTestClass(Class clazz) throws IOException{
		String context = createTestClassContext(clazz);
		FileHelper.write(clazz, context);
	}
	
}
