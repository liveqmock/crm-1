package com.deppon.crm.module.gis.server.service.impl;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.deppon.crm.module.gis.server.service.IPointTransitionService;
import com.deppon.crm.module.gis.shared.domain.BaiduPointJSONEntity;
import com.deppon.foss.framework.shared.encypt.base64.BASE64Decoder;

public class PointTransitionService implements IPointTransitionService{
	@Override
	public String transitionPointByOffset(String google) {
		
		String []lnglats = google.split(",");
		StringBuffer baidu = new StringBuffer();
		for(int i=0;i<lnglats.length;i++){
			String lnglat="";
			if(i%2==0){
				double lng = Double.parseDouble(lnglats[i])+0.0060;
				double lat = Double.parseDouble(lnglats[i+1])+0.0065;
				if(i>=lnglats.length-2){
					lnglat = lng+","+lat;
				}else{
					lnglat = lng+","+lat+",";
				}
			}
			baidu.append(lnglat);
		}
		return baidu.toString();
	}
	/**
	 * 
	 * <p>TODO(调用百度接口进行google坐标批量转换-坐标格式转换20个为一组)</p> 
	 * @author 073102-foss-Tommy Wu
	 * @date 2013-5-10 下午04:10:07
	 * @param google
	 * @return 
	 * @see com.deppon.crm.module.gis.server.service.IPointTransitionService#googleConvertBaiduByWs(java.lang.String)
	 */
	@Override
	public String googleConvertBaiduByWs(String google) {
		long time = 1000*60*5;
		long startTime = System.currentTimeMillis();
		String [] str = google.toString().replaceAll(" ","").split(",");
		int pagecount=(str.length%40)==0?(str.length/40):(str.length/40+1);
		int totleCount = str.length;
		System.out.println("总坐标数："+totleCount);
		StringBuffer points = new StringBuffer();
		if(pagecount==1){
			StringBuffer xs  = new StringBuffer();
			StringBuffer ys = new StringBuffer();
			for(int j=0;j<str.length;j++){
				if(j%2==0){
					xs.append(str[j]+",");
				}else{
					ys.append(str[j]+",");
				}
			}
			String s="";
			try {
				if(System.currentTimeMillis()-startTime>time){
			    	return "";
			    }
				s = this.batchTransitionPointByWebService(xs.substring(0,xs.toString().length()-1),ys.substring(0,ys.toString().length()-1));
			} catch (Exception e){
			    	return "";
			}
			points.append(s);
		}else{
			for(int i=0;i<pagecount;i++){
				StringBuffer xs  = new StringBuffer();
				StringBuffer ys = new StringBuffer();
				int len = 0;
				if(i==pagecount-1){
					len = totleCount;
				}else{
					len = 40+(i*40);
				}
				for(int j=40*i;j<len;j++){ 
					if(j%2==0){
						xs.append(str[j]+",");
					}else{
						ys.append(str[j]+",");
					}
				}
				String s="";
				try {
					if(System.currentTimeMillis()-startTime>time){
				    	return "";
				    }
					s = this.batchTransitionPointByWebService(xs.substring(0,xs.toString().length()-1),ys.substring(0,ys.toString().length()-1));
				} catch (Exception e) {
					return "";
				}
				points.append(s);
			}
		}
		String res = points.toString();
		String result = res.substring(0,res.length()-1);
	    return result;
		
	}
	/**
	 * 
	 * <p>TODO(批量google点转换为百度)</p> 
	 * @author 073102-foss-Tommy Wu
	 * @date 2013-5-10 下午04:09:50
	 * @param xs
	 * @param ys
	 * @return
	 * @throws Exception
	 * @see
	 */
	public String batchTransitionPointByWebService(String xs,String ys) throws Exception{
		String url = "http://api.map.baidu.com/ag/coord/convert?mode=1&from=2&to=4&x="+xs+"&y="+ys;
		URL urlmy = null;
		String ret = "";
		urlmy = null;
		HttpURLConnection con = null;
		String pointString = "";
		BufferedReader br = null;
		StringBuffer ps = new StringBuffer("");//返回来解析过后的坐标
		try {
			urlmy = new URL(url);	
			con = (HttpURLConnection) urlmy.openConnection();
			con.setConnectTimeout(6*1000);
			HttpURLConnection.setFollowRedirects(true);
			con.setInstanceFollowRedirects(false);	
			con.setReadTimeout(6*1000);
			con.connect();
			br = new BufferedReader(new InputStreamReader(
					con.getInputStream(), "UTF-8"));
			String s = "";
			StringBuffer sb = new StringBuffer("");
			while ((s=br.readLine())!=null) {
				sb.append(s);
			}
			ret = sb.toString();
			JSONArray jsArr = JSONArray.fromObject(ret);
			for (int i = 0; i < jsArr.size(); i++) {
				//转化json字符串成对象
				JSONObject instJ = JSONObject.fromObject(jsArr.get(i));
				BaiduPointJSONEntity bean = (BaiduPointJSONEntity) JSONObject.toBean(instJ,BaiduPointJSONEntity.class); // 把值绑定成相应的值对象//将解析的坐标解码
				if(!"0".equals(bean.getError())){
					pointString="";
				}else{
					//base64解密
					BASE64Decoder d = new BASE64Decoder();
					ByteArrayOutputStream outStreamX = new ByteArrayOutputStream();
					ByteArrayOutputStream outStreamY = new ByteArrayOutputStream();
					d.decodeBuffer(new ByteArrayInputStream(bean.getX().getBytes("utf-8")),outStreamX);
					d.decodeBuffer(new ByteArrayInputStream(bean.getY().getBytes("utf-8")),outStreamY);
					pointString = outStreamX + "," + outStreamY;
				}
				ps.append(pointString+",");
			}
		} catch (IOException e) {
			try {
				Thread.sleep(1200);
			} catch (Exception e2) {
				throw new RuntimeException(e2);
			}
			this.batchTransitionPointByWebService(xs,ys);
		}finally{
			if(con !=null){
				con.disconnect();
			}
			if(br !=null){
				try {
					br.close();
				} catch (IOException e) {
					throw new RuntimeException(e);
				}
			}
		}
		return ps.toString();
	}
}
