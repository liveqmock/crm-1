package com.deppon.crm.module.marketing.utils;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import junit.framework.TestCase;

import com.deppon.crm.module.marketing.server.utils.MarketAssessUtils;
import com.deppon.crm.module.marketing.shared.domain.AssessDept;
import com.deppon.crm.module.marketing.shared.domain.AssessDeptPrincipal;

public class MarketAssessUtilsTest extends TestCase{
	
	public void testGetCreateDate(){
		MarketAssessUtils.getCreateDate();
	}
	public void testAssessAuthority(){
		List<AssessDeptPrincipal> prin = new ArrayList<AssessDeptPrincipal>();
		AssessDeptPrincipal p = new AssessDeptPrincipal();
		p.setDeptId("463");
		p.setDeptName("浙江事业部");
		p.setPrincipal("003998");
		prin.add(p);
		
		Set<String> auths = new HashSet<String>();
		List<AssessDept> depts = new ArrayList<AssessDept>();
		auths.add("2005");
		AssessDept dept = new AssessDept();
		dept.setId("106139");
		dept.setDeptName("钟琼");
		dept.setHierarchy("C");
		depts.add(dept);
		MarketAssessUtils.assessAuthority(null,auths, depts);
		MarketAssessUtils.assessAuthority(prin,auths, depts);
		AssessDept dept1 = new AssessDept();
		dept1.setId("106139");
		dept1.setDeptName("经营本部");
		depts.add(dept1);
		MarketAssessUtils.assessAuthority(prin,auths, depts);
		AssessDept dept2 = new AssessDept();
		dept2.setId("106139");
		dept2.setDeptName("事业部");
		AssessDept dept3 = new AssessDept();
		dept3.setId("106139");
		dept3.setDeptName("大区");
		dept3.setHierarchy("2005");
		depts.add(dept3);
		dept2.setChildDept(dept3);
		depts.add(dept2);
		MarketAssessUtils.assessAuthority(prin,auths, depts);
	}
	public void testMaxAssessAuthority(){
		List<AssessDeptPrincipal> prin = new ArrayList<AssessDeptPrincipal>();
		AssessDeptPrincipal p = new AssessDeptPrincipal();
		p.setDeptId("463");
		p.setDeptName("浙江事业部");
		p.setPrincipal("003998");
		prin.add(p);
		
		Set<String> auths = new HashSet<String>();
		auths.add("2005"); //是否具有全国权限
		MarketAssessUtils.maxAssessAuthority(prin,auths);
		auths.clear();
		auths.add("2004"); //是否具有经营本部权限
		MarketAssessUtils.maxAssessAuthority(prin,auths);
		auths.clear();
		auths.add("2003"); //是否具有事业部权限
		MarketAssessUtils.maxAssessAuthority(prin,auths);
		auths.clear();
		auths.add("2002"); //是否具有大区权限
		MarketAssessUtils.maxAssessAuthority(prin,auths);
		auths.clear();
		auths.add("2001"); //是否具有营业区权限
		MarketAssessUtils.maxAssessAuthority(null,auths);
		auths.clear();
		MarketAssessUtils.maxAssessAuthority(null,auths);
	}
	
	public void testGetDeptHier(){
		String authId = "2005";//全国权限
		MarketAssessUtils.getDeptHier(authId);
		authId = "2004";//经营本部权限
		MarketAssessUtils.getDeptHier(authId);
		authId = "2003";//事业部权限
		MarketAssessUtils.getDeptHier(authId);
		authId = "2002";//大区权限
		MarketAssessUtils.getDeptHier(authId);
		authId = "2001";//营业区权限
		MarketAssessUtils.getDeptHier(authId);
		authId = "2000";//营业区权限
		MarketAssessUtils.getDeptHier(authId);
	}
	
	public void testCloneDept(){
		AssessDept dept = new AssessDept();
		dept.setId("106139");
		dept.setDeptName("钟琼");
		String hierarchy = "106139";
		MarketAssessUtils.cloneDept(dept, hierarchy);
	}
	
	public void testGetAssessDeptByDeptName(){
		List<AssessDept> depts = new ArrayList<AssessDept>();
		String deptName = "钟琼";
		MarketAssessUtils.getAssessDeptByDeptName(depts, deptName);
		AssessDept dept = new AssessDept();
		dept.setId("106139");
		dept.setDeptName("钟琼");
		depts.add(dept);
		MarketAssessUtils.getAssessDeptByDeptName(depts, deptName);
		deptName = "欢欢";
		MarketAssessUtils.getAssessDeptByDeptName(depts, deptName);
		AssessDept dept1 = null;
		depts.clear();
		depts.add(dept1);
		MarketAssessUtils.getAssessDeptByDeptName(depts, deptName);
	}
	public void testAssessAuthorityBuildDept(){
		List<AssessDeptPrincipal> prin = new ArrayList<AssessDeptPrincipal>();
		AssessDeptPrincipal p = new AssessDeptPrincipal();
		p.setDeptId("463");
		p.setDeptName("浙江事业部");
		p.setPrincipal("003998");
		prin.add(p);
		
		MarketAssessUtils.assessAuthorityBuildDept(null,null, null);
		Set<String> auths = new HashSet<String>();
		auths.add("2005");
		MarketAssessUtils.assessAuthorityBuildDept(null,auths, null);
		List<AssessDept> depts = new ArrayList<AssessDept>();
		AssessDept dept = new AssessDept();
		dept.setId("106139");
		dept.setDeptName("经营本部");
		depts.add(dept);
		AssessDept dept1 = new AssessDept();
		dept1.setId("106139");
		dept1.setDeptName("事业部");
		depts.add(dept1);
		AssessDept dept2 = new AssessDept();
		dept2.setId("106139");
		dept2.setDeptName("大区");
		depts.add(dept2);
		AssessDept dept3 = new AssessDept();
		dept3.setId("106139");
		dept3.setDeptName("营业区");
		depts.add(dept3);
		AssessDept dept4 = new AssessDept();
		dept4.setId("106139");
		dept4.setDeptName("营业部");
		depts.add(dept4);
		
		MarketAssessUtils.assessAuthorityBuildDept(prin,auths, depts);
		auths.add("2004");
		MarketAssessUtils.assessAuthorityBuildDept(prin,auths, depts);
		auths.add("2003");
		MarketAssessUtils.assessAuthorityBuildDept(prin,auths, depts);
		auths.add("2002");
		MarketAssessUtils.assessAuthorityBuildDept(prin,auths, depts);
		auths.add("2001");
	}
	
	public void testAssessAuthorityBuildDept1(){
		Set<String> auths = new HashSet<String>();
		auths.add("2005");
		List<AssessDept> depts = new ArrayList<AssessDept>();
		AssessDept dept = new AssessDept();
		dept.setId("106139");
		dept.setDeptName("2121");
		depts.add(dept);
		MarketAssessUtils.assessAuthorityBuildDept(null,auths, depts);
	}
	
	public void testAssessAuthorityBuildDept2(){
		List<AssessDeptPrincipal> prin = new ArrayList<AssessDeptPrincipal>();
		AssessDeptPrincipal p = new AssessDeptPrincipal();
		p.setDeptId("463");
		p.setDeptName("浙江事业部");
		p.setPrincipal("003998");
		prin.add(p);
		
		Set<String> auths = new HashSet<String>();
		auths.add("2005");
		List<AssessDept> depts = new ArrayList<AssessDept>();
		AssessDept dept = new AssessDept();
		dept.setId("106139");
		dept.setDeptName("经营本部");
		depts.add(dept);
		AssessDept dept1 = new AssessDept();
		dept1.setId("106139");
		dept1.setDeptName("事业部");
		depts.add(dept1);
		MarketAssessUtils.assessAuthorityBuildDept(prin,auths, depts);
		AssessDept dept2 = new AssessDept();
		dept2.setId("106139");
		dept2.setDeptName("大区");
		depts.add(dept2);
		MarketAssessUtils.assessAuthorityBuildDept(prin,auths, depts);
		AssessDept dept3 = new AssessDept();
		dept3.setId("106139");
		dept3.setDeptName("营业区");
		depts.add(dept3);
		MarketAssessUtils.assessAuthorityBuildDept(prin,auths, depts);
	}
	public void testAssessAuthorityBuildDept3(){
		List<AssessDeptPrincipal> prin = new ArrayList<AssessDeptPrincipal>();
		AssessDeptPrincipal p = new AssessDeptPrincipal();
		p.setDeptId("463");
		p.setDeptName("浙江事业部");
		p.setPrincipal("003998");
		prin.add(p);
		
		Set<String> auths = new HashSet<String>();
		auths.add("2005");
		List<AssessDept> depts = new ArrayList<AssessDept>();
		AssessDept dept = new AssessDept();
		dept.setId("106139");
		dept.setDeptName("经营本部");
		depts.add(dept);
		MarketAssessUtils.assessAuthorityBuildDept(prin,auths, depts);
	}
	
	public void testAAA(){
		String[] a = new String[]{"15303",
				"37852",
				"38371",
				"54416",
				"67775",
				"20110612-06917558",
				"149646",
				"316461",
				"488169",
				"638816",
				"650751",
				"703277",
				"400062448",
				"400063994",
				"400064076",
				"400064918",
				"400065046",
				"400065092",
				"400065097",
				"400066178",
				"400066614",
				"400066640",
				"400066701",
				"400066715",
				"400066767",
				"400066794",
				"400066814",
				"400066853",
				"400066985",
				"400067027",
				"400101342",
				"400118890",
				"400131452",
				"400132425",
				"400136757",
				"400136808",
				"400136847",
				"400138510",
				"400138721",
				"400138737",
				"400138741",
				"400176433",
				"400182434",
				"400210361",
				"400210953",
				"400210990",
				"400211027",
				"400211068",
				"400211135",
				"400211230",
				"400219128",
				"400235176",
				"400259498",
				"400261641",
				"400263245",
				"400265366",
				"400278510",
				"400278533",
				"400297361",
				"400297950",
				"400299821",
				"400324863",
				"400324902",
				"400324931",
				"400324932",
				"400324933",
				"400325770",
				"400333151",
				"400352705",
				"400372050",
				"400372072",
				"400390130",
				"400408472",
				"400425974",
				"400619005",
				"400619865",
				"400631878",
				"400631908",
				"400632546",
				"400632556",
				"400632578",
				"400632767",
				"400632851",
				"400633096",
				"400634252",
				"400634293",
				"400634302",
				"400634350",
				"400634415",
				"400634512",
				"400634613",
				"400634628",
				"400634648",
				"400669216",
				"400669281",
				"400669366",
				"400671471",
				"400672366",
				"400672436",
				"400672492",
				"400672548",
				"400672554",
				"400672586",
				"400707887",
				"400707897",
				"400708374",
				"400708421",
				"400708455",
				"400711459",
				"400711575",
				"400712366",
				"400969654",
				"400970094",
				"400970207",
				"400970421",
				"400970980",
				"400971119",
				"400971767",
				"400972643",
				"400972816",
				"400974017",
				"400974224",
				"400974589",
				"400975008",
				"400975618",
				"400975974",
				"401156534",
				"401158975",
				"401174732",
				"401174740",
				"401185895",
				"401203073",
				"401241866",
				"401139365",
				"401135422",
				"401135417",
				"401135379",
				"401118883",
				"401114358",
				"401103374",
				"401103371",
				"401081892",
				"401095362",
				"401076858",
				"401031478",
				"400996728",
				"400996523",
				"400996447",
				"400996309",
				"400996263",
				"400996305",
				"400995714",
				"400995345",
				"400995112",
				"400994831",
				"400994774",
				"400994439",
				"400992114",
				"400991359",
				"400991226",
				"400990292",
				"400986131",
				"400985673",
				"400985425",
				"400985141",
				"400984925",
				"400984825",
				"400983796"};
		String[] b=new String[]{
				"149646",
				"400138721",
				"400066178",
				"400211135",
				"703277",
				"400065092",
				"400064076",
				"400066715",
				"400132425",
				"400118890",
				"400136808",
				"400065046",
				"400067027",
				"650751",
				"400066701",
				"638816",
				"400136847",
				"400138510",
				"400131452",
				"400632556",
				"400708455",
				"400708421",
				"400631878",
				"400671471",
				"400712366",
				"400619005",
				"400634613",
				"400632578",
				"400634350",
				"400672548",
				"400711459",
				"400634628",
				"400631908",
				"400669281",
				"400672366",
				"400672492",
				"400669366",
				"400632851",
				"400708374",
				"400707887",
				"400634252",
				"400634302",
				"400634648",
				"400425974",
				"400372050",
				"400211068",
				"400324863",
				"400176433",
				"400278510",
				"400324933",
				"400408472",
				"400259498",
				"400278533",
				"400325770",
				"400182434",
				"400210361",
				"400324931",
				"400265366",
				"400297950",
				"400372072",
				"400297361",
				"400352705",
				"400985673",
				"400985141",
				"400970207",
				"400969654",
				"401203073",
				"400970421",
				"400984825",
				"401095362",
				"401174732",
				"401174740",
				"400995345",
				"400994831",
				"400971119",
				"401135379",
				"401135422",
				"400990292",
				"400996728",
				"401139365",
				"400994439",
				"401118883",
				"400996263",
				"400996305",
				"400996309",
				"400995714",
				"400974589",
				"401031478",
				"400974017",
				"401076858",
				"401081892",
				"401158975",
				"401156534",
				"401185895",
				"400983796",
				"400986131"
		};
		int i = 0;
		List<String> l =new ArrayList<String>();
		for(String s : a){
			for(String v :b ){
				if(s.equals(v)){
					i++;
				}
			}
			if(i==0){
				l.add(s);
			}
			i=0;
		}
		for( String x : l){
			System.out.println("'"+x+"',");
		}
	}
}
