package com.dhcc.piccbid.utils;

import java.io.File;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.dhcc.framework.common.SpringContextHolder;
import com.dhcc.framework.web.context.WebContextHolder;
import com.dhcc.piccbid.common.GlobalProperties;

/**
 * <p>
 * 标题: DhccUtil.java
 * 业务描述:工具类
 * 公司:东华软件股份公司
 * 版权:dhcc2014
 * </p>
 * @author gzw
 * @version V1.0
 * @date 2018年10月10日
 */
public class DhccUtil {
	public static GlobalProperties globalProperties = (GlobalProperties)SpringContextHolder.getBean("GlobalProperties");
	private static Log logger = LogFactory.getLog(DhccUtil.class);
	
	public static String logo = globalProperties.getEnv().getProperty("logo.version");
	public static String css = logo;
	public static String title = getTitle();
	
	public static String getTitle(){
		if(logo.equals("picc")){
			return "人保飞检系统";
		}
		return "";
	}

	public static Date formatDate(String time) {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		if (DhccUtil.isNullOrEmpty(time)) {
			if (time.length() < 11) {
				time = time + " 00:00:00";
			}
			try {
				return formatter.parse(time);
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}

		return null;
	}

	public static String formatDate(Date time) {

		String newStr = "";
		try {
			newStr = new SimpleDateFormat("yyyy-MM-dd").format(time);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
		}
		return newStr;
	}

	public static String getSystemTime() {
		String sj = "";
		try {
			sj = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return sj;
	}

	/**
	 * 方法名: isNullOrEmpty 方法功能描述: 判断非空
	 */
	public static boolean isNullOrEmpty(String str) {

		boolean b = false;
		if (str != null && !"".equals(str)) {
			b = true;
		}

		return b;
	}

	/**
	 * Annotation: 获取绝对路径
	 */
	public static String getRealPath() {
		@SuppressWarnings("deprecation")
		String realPath = WebContextHolder.getContext().getRequest().getRealPath("");

		// 判断最后一个字符，是否添加文件分隔符
		if (!realPath.endsWith(File.separator)) {
			realPath += File.separator;
		}
		return realPath;
	}

	/**
	 * 方法名: getUserRealIp 方法功能描述: 获取ip
	 *
	 * @Author: 李研
	 * @Create Date: 2016年11月8日
	 */


	
	  
	/**
	 * Annotation: 合并 JsonArray
	 *
	 * @Author: Adam Ming
	 * @Date: Oct 24, 2018 at 5:17:39 PM
	 */
	public static JSONArray joinJSONArray(JSONArray mData, JSONArray array) {
		StringBuffer buffer = new StringBuffer();
		try {
			int len = mData.length();
			jsonArrayStr(mData, buffer, len);

			len = array.length();
			if (len > 0 && buffer.length() > 0) {
				buffer.append(",");
			}
			jsonArrayStr(array, buffer, len);
			buffer.insert(0, "[").append("]");
			return new JSONArray(buffer.toString());
		} catch (Exception e) {
			logger.error(e);
		}
		return null;
	}

	private static void jsonArrayStr(JSONArray mData, StringBuffer buffer, int len) throws JSONException {
		for (int i = 0; i < len; i++) {
			JSONObject obj1 = (JSONObject) mData.get(i);
			if (i == len - 1)
				buffer.append(obj1.toString());
			else
				buffer.append(obj1.toString()).append(",");
		}
	}

	public static Date addDateHour(Date day, int hour) {
		Date date = null;
		try {
			date = day;
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		if (date == null)
			return null;
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.HOUR, hour);// 24小时制
		date = cal.getTime();
		cal = null;
		return date;
	}

	public static String addDateHourString(String day, int hour) {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");// 24小时制
		Date date = null;
		try {
			date = format.parse(day);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		if (date == null)
			return "";
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.HOUR, hour);// 24小时制
		date = cal.getTime();
		cal = null;
		return format.format(date);
	}

	/**
	 * 比较日期大小
	 * 
	 * @param d1
	 * @param d2
	 * @return
	 */
	public static int compareDate(String date1, String date2) {
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		try {
			Date dt1 = df.parse(date1);
			Date dt2 = df.parse(date2);
			if (dt1.getTime() > dt2.getTime()) {
				return 1;
			} else if (dt1.getTime() < dt2.getTime()) {
				return -1;
			} else {
				return 0;
			}
		} catch (Exception exception) {
			exception.printStackTrace();
		}
		return 0;
	}
	 public static String getHostName(String ip){
	    	InetAddress inet;
	    	try {
				inet = InetAddress.getByName(ip);
				return inet.getHostName();
			} catch (UnknownHostException e) {
				e.printStackTrace();
			}
	    	return "";
	    } 
	   
	
/*	   public  static String getMACAddress(String ip){ 
	        String str = ""; 
	        String macAddress = ""; 
	        try { 
	            Process p = Runtime.getRuntime().exec("nbtstat -A " + ip); 
	            InputStreamReader ir = new InputStreamReader(p.getInputStream()); 
	            LineNumberReader input = new LineNumberReader(ir); 
	            for (int i = 1; i < 100; i++) { 
	                str = input.readLine(); 
	                if (str != null) { 
	                    if (str.indexOf("MAC Address") > 1) { 
	                        macAddress = str.substring(str.indexOf("MAC Address") + 14, str.length()); 
	                        break; 
	                    } 
	                } 
	            } 
	        } catch (IOException e) { 
	            e.printStackTrace(System.out); 
	        } 
	        return macAddress; 

	   }*/

	public static String getOsInfo(HttpServletRequest request) {
		String header = request.getHeader("User-Agent");
		if(header == null || header.equals("")){
			   return "";
		 }
		String systenInfo = "";
		if(header.toLowerCase().indexOf("windows") >= 0) {
		if (header.toLowerCase().indexOf("nt 6.0") > 0){
            systenInfo = "Windows Vista/Server 2008";
        } else if (header.toLowerCase().indexOf("nt 5.2") > 0){
            systenInfo = "Windows Server 2003";
        } else if (header.toLowerCase().indexOf("nt 5.1") > 0){
            systenInfo = "Windows XP";
        } else if (header.toLowerCase().indexOf("nt 6.0") > 0){
            systenInfo = "Windows Vista";
        } else if (header.toLowerCase().indexOf("nt 6.1") > 0){
            systenInfo = "Windows 7";
        } else if (header.toLowerCase().indexOf("nt 6.2") > 0){
            systenInfo = "Windows Slate";
        } else if (header.toLowerCase().indexOf("nt 6.3") > 0){
            systenInfo = "Windows 9";
        } else if (header.toLowerCase().indexOf("nt 5") > 0){
            systenInfo = "Windows 2000";
        } else if (header.toLowerCase().indexOf("nt 4") > 0){
            systenInfo = "Windows NT4";
        } else if (header.toLowerCase().indexOf("me") > 0){
            systenInfo = "Windows Me";
        } else if (header.toLowerCase().indexOf("98") > 0){
            systenInfo = "Windows 98";
        } else if (header.toLowerCase().indexOf("95") > 0){
            systenInfo = "Windows 95";
        }
		} else if (header.toLowerCase().indexOf("mac") > 0){
            systenInfo = "Mac";
        } else if (header.toLowerCase().indexOf("unix") > 0&&header.toLowerCase().indexOf("x11") >= 0){
            systenInfo = "Unix";
        } else if (header.toLowerCase().indexOf("linux") > 0){
            systenInfo = "Linux";
        } else if (header.toLowerCase().indexOf("sunos") > 0){
            systenInfo = "SunOS";
        }else if (header.toLowerCase().indexOf("android") > 0){
            systenInfo = "Android";
        }else if (header.toLowerCase().indexOf("iphone") > 0){
            systenInfo = "IPhone";
        }else {
        	systenInfo = "UnKnown, More-Info: " + header;
		}
		return systenInfo;
	}

	public static String getBrowserInfo(HttpServletRequest request) {
		String browserDetails = request.getHeader("User-Agent");
		String userAgent = browserDetails;
		String user = userAgent.toLowerCase();
		String browser = "";
		if (user.contains("edge")) {
			browser = (userAgent.substring(userAgent.indexOf("Edge")).split(" ")[0]).replace("/", "-");
		} else if (user.contains("msie")) {
			String substring = userAgent.substring(userAgent.indexOf("MSIE")).split(";")[0];
			browser = substring.split(" ")[0].replace("MSIE", "IE") + "-" + substring.split(" ")[1];
		} else if (user.contains("safari") && user.contains("version")) {
			browser = (userAgent.substring(userAgent.indexOf("Safari")).split(" ")[0]).split("/")[0] + "-"
					+ (userAgent.substring(userAgent.indexOf("Version")).split(" ")[0]).split("/")[1];
		} else if (user.contains("opr") || user.contains("opera")) {
			if (user.contains("opera")) {
				browser = (userAgent.substring(userAgent.indexOf("Opera")).split(" ")[0]).split("/")[0] + "-"
						+ (userAgent.substring(userAgent.indexOf("Version")).split(" ")[0]).split("/")[1];
			} else if (user.contains("opr")) {
				browser = ((userAgent.substring(userAgent.indexOf("OPR")).split(" ")[0]).replace("/", "-"))
						.replace("OPR", "Opera");
			}
		} else if (user.contains("chrome")) {
			browser = (userAgent.substring(userAgent.indexOf("Chrome")).split(" ")[0]).replace("/", "-");
		} else if ((user.indexOf("mozilla/7.0") > -1) || (user.indexOf("netscape6") != -1)
				|| (user.indexOf("mozilla/4.7") != -1) || (user.indexOf("mozilla/4.78") != -1)
				|| (user.indexOf("mozilla/4.08") != -1) || (user.indexOf("mozilla/3") != -1)) {
			browser = "Netscape-?";
		} else if (user.contains("firefox")) {
			browser = (userAgent.substring(userAgent.indexOf("Firefox")).split(" ")[0]).replace("/", "-");
		} else if (user.contains("rv")) {
			String IEVersion = (userAgent.substring(userAgent.indexOf("rv")).split(" ")[0]).replace("rv:", "-");
			browser = "IE" + IEVersion.substring(0, IEVersion.length() - 1);
		} else {
			browser = "UnKnown, More-Info: " + userAgent;
		}
		return browser;
	}

}
