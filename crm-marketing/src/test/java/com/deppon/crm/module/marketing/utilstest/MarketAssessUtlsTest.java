package com.deppon.crm.module.marketing.utilstest;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.Test;

import com.deppon.crm.module.marketing.server.utils.MarketAssessUtils;
import com.deppon.crm.module.marketing.shared.domain.AssessAuthority;
import com.deppon.crm.module.marketing.shared.domain.AssessDept;
import com.deppon.crm.module.marketing.shared.domain.AssessDeptPrincipal;
import com.deppon.crm.module.marketing.shared.domain.MarketAssessConstance;
import com.deppon.crm.module.marketing.shared.domain.SearchAssessCondition;

public class MarketAssessUtlsTest {
	@Test
	public void testAssessAuthority(){
		Set<String> auths = new HashSet<String>();
		auths.add(MarketAssessConstance.AUTHOR_NATION_ID);
		auths.add(MarketAssessConstance.AUTHOR_SERCENTER_ID);
		auths.add(MarketAssessConstance.AUTHOR_BUDEPT_ID);
//		auths.add(MarketAssessConstance.AUTHOR_REGION_ID);
//		auths.add(MarketAssessConstance.AUTHOR_BUQUARTER_ID);
		auths.add("0");
		auths.add("123");
		List<AssessDept> depts = new ArrayList<AssessDept>();
		AssessDept s = new AssessDept();
		s.setId("103");
		s.setDeptName("办公门户机构人员");
		depts.add(s);
		
		s = new AssessDept();
		s.setId("104");
		s.setDeptName("总裁");
		depts.add(s);
		
		s = new AssessDept();
		s.setId("265");
		s.setDeptName("营运副总裁");
		depts.add(s);
		
		s = new AssessDept();
		s.setId("626");
		s.setDeptName("东北大区");
		depts.add(s);
		
		s = new AssessDept();
		s.setId("754");
		s.setDeptName("东北事业部");
		depts.add(s);
		
		s = new AssessDept();
		s.setId("10945");
		s.setDeptName("吉林营业区");
		depts.add(s);
		
		s = new AssessDept();
		s.setId("49082");
		s.setDeptName("吉林省吉林沙河子营业部");
		depts.add(s);
		List<AssessDeptPrincipal> prin = new ArrayList<AssessDeptPrincipal>();
		AssessDeptPrincipal p = new AssessDeptPrincipal();
		p.setDeptId("463");
		p.setDeptName("浙江事业部");
		p.setPrincipal("003998");
		prin.add(p);
		AssessAuthority aa = MarketAssessUtils.assessAuthority(null,auths, depts);
		 aa = MarketAssessUtils.assessAuthority(prin,auths, depts);
		depts = new ArrayList<AssessDept>();
		s = new AssessDept();
		s.setId("103");
		s.setDeptName("办公门户机构人员");
		depts.add(s);
		
		s = new AssessDept();
		s.setId("104");
		s.setDeptName("总裁");
		depts.add(s);
		
		s = new AssessDept();
		s.setId("265");
		s.setDeptName("华北经营本部");
		depts.add(s);
		
		s = new AssessDept();
		s.setId("626");
		s.setDeptName("东北事业部");
		depts.add(s);
		
		s = new AssessDept();
		s.setId("754");
		s.setDeptName("东北大区");
		depts.add(s);
		
		s = new AssessDept();
		s.setId("10945");
		s.setDeptName("东北大区吉林营业区");
		depts.add(s);
		
		s = new AssessDept();
		s.setId("49082");
		s.setDeptName("吉林省吉林沙河子营业部");
		depts.add(s);
		
		aa = MarketAssessUtils.assessAuthority(prin,auths, depts);
		//System.out.println(aa);
	}
	@Test
	public void testStrucSeaAssCondition(){
		String auth = "2005";
		List<AssessDept> depts = new ArrayList<AssessDept>();
		AssessDept a = new AssessDept();
		a.setId("1234");
		a.setDeptName("华北事业部");
		depts.add(a);
		Date date = new Date();
		SearchAssessCondition sc = MarketAssessUtils.strucSeaAssCondition(auth,depts,date);
		//System.out.println(sc);
		
		auth = "2003";
		depts = new ArrayList<AssessDept>();
		a = new AssessDept();
		a.setId("1234");
		a.setDeptName("华北事业部");
		depts.add(a);
		date = new Date();
		sc = MarketAssessUtils.strucSeaAssCondition(auth,depts,date);
		//System.out.println(sc);
		
		auth = "2003";
		depts = new ArrayList<AssessDept>();
		a = new AssessDept();
		a.setId("1234");
		a.setDeptName("华北事业部");
		depts.add(a);
		a = new AssessDept();
		a.setId("12344");
		a.setDeptName("华北大区");
		depts.add(a);
		date = new Date();
		sc = MarketAssessUtils.strucSeaAssCondition(auth,depts,date);
		//System.out.println(sc);
	}

}
