package p1;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.URL;
import java.net.URLConnection;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.appengine.labs.repackaged.org.json.JSONArray;
import com.google.appengine.labs.repackaged.org.json.JSONException;
import com.google.appengine.labs.repackaged.org.json.JSONObject;

@SuppressWarnings("serial")
public class Google_requestServlet extends HttpServlet {
	
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException  {
		resp.setContentType("application/json");
		resp.setCharacterEncoding("UTF-8");
		PrintWriter out = resp.getWriter();
		URL url;
//		JSONObject json2 = new JSONObject();
//		JSONArray arr = new JSONArray();
		try {
			
			String p = req.getParameter("p1");
			int nbtonextpage = Integer.parseInt(req.getParameter("start"));
			
			url = new URL(
				    "https://ajax.googleapis.com/ajax/services/search/web?v=1.0&"
				    + "start="+ nbtonextpage + "&q=" +p + "&userip=USERS-IP-ADDRESS");
			URLConnection connection = url.openConnection();
			//connection.addRequestProperty("Referer", /* Enter the URL of your site here */);

			String line;
			StringBuilder builder = new StringBuilder();
			BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream(), "UTF-8"));
			while((line = reader.readLine()) != null) {
			 builder.append(line);
			}

			JSONObject json = null;
			try {
				json = new JSONObject(builder.toString());
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				//e.printStackTrace();
			}
			
//			
//			
//		    for (int i = 0; i < json.optJSONObject("responseData").optJSONArray("results").length(); i++) {
//		        try {
//		        	arr.put(json.optJSONObject("responseData").optJSONArray("results").get(i));
//				} catch (JSONException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}
//		    }
//		    
//		    url = new URL(
//				    "https://ajax.googleapis.com/ajax/services/search/web?v=1.0&"
//				    + "start="+ (nbtonextpage+4) + "&q=" +p + "&userip=USERS-IP-ADDRESS");
//			connection = url.openConnection();
//			//connection.addRequestProperty("Referer", /* Enter the URL of your site here */);
//
//			//line;
//			builder = new StringBuilder();
//			reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
//			while((line = reader.readLine()) != null) {
//			 builder.append(line);
//			}
//
//			json = null;
//			try {
//				json = new JSONObject(builder.toString());
//			} catch (JSONException e) {
//				// TODO Auto-generated catch block
//				//e.printStackTrace();
//			}
//			
//			for (int i = 0; i < json.optJSONObject("responseData").optJSONArray("results").length(); i++) {
//		        try {
//		        	arr.put(json.optJSONObject("responseData").optJSONArray("results").get(i));
//				} catch (JSONException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}
//		    }
			
			try {
				//json2.put("response", arr);
				//json2.put("nextpage", nbtonextpage+8);
				json.put("nextpage", nbtonextpage+4);
				json.put("lastpage", nbtonextpage-4);
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			out.print(json);
			//out.flush();
				
			
		} finally {
			out.close();
		}
}

	
//	public void doGet(HttpServletRequest req, HttpServletResponse resp)
//			throws IOException {
//		resp.setContentType("text/html");
//		PrintWriter out = resp.getWriter();
//		URL url;
//		int nbtonextpage = 0;
//		try {
//			
//			String p = req.getParameter("p1");
//			out.println("<html> <head> <title>Result</title> </head> <body> <h1>Result!</h1>");
//			out.println("<table>");
//			
//			url = new URL(
//				    "https://ajax.googleapis.com/ajax/services/search/web?v=1.0&"
//				    + "start="+ nbtonextpage + "&q=" +p + "&userip=USERS-IP-ADDRESS");
//			for(int j = 0; j<2; j++ )
//			{
//				
//				URLConnection connection = url.openConnection();
//				//connection.addRequestProperty("Referer", /* Enter the URL of your site here */);
//	
//				String line;
//				StringBuilder builder = new StringBuilder();
//				BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
//				while((line = reader.readLine()) != null) {
//				 builder.append(line);
//				}
//	
//				JSONObject json = null;
//				try {
//					json = new JSONObject(builder.toString());
//				} catch (JSONException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}
//				
//				JSONObject json2 = json.optJSONObject("responseData");
//				
//				JSONArray json3 = null;
//				try {
//					json3 = json2.getJSONArray("results");
//				} catch (JSONException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}
//			
//				for (int i = 0; i < json3.length(); ++i) {
//				    JSONObject rec=null;
//					try {
//						rec = json3.getJSONObject(i);
//						out.println("<tr>");
//						out.println("<td> LIEN NO " + ((j*4)+i+1));
//						out.println("</td></tr>");
//						out.println("<tr>");
//						out.println(" <td>DESCR: " + rec.getString("content"));
//						out.println("</td></tr>");
//						out.println("<tr>");
//						out.println("<td>TITRE NON FORMATE: " + rec.getString("titleNoFormatting"));
//						out.println("</td></tr>");
////						out.println("<tr>");
////						out.println("</td>TITRE: " +rec.getString("title"));
////						out.println("</td></tr>");
//						out.println("<tr>");
//						out.println("<td>LIEN: " + rec.getString("url"));
//						out.println("</td></tr>");
//						out.println("<td>-----------------------------");
//						out.println("</td>");
//					} catch (JSONException e) {
//						// TODO Auto-generated catch block
//						e.printStackTrace();
//					}
//				}
//				try {
//					out.println(json2.getJSONObject("cursor").getString("currentPageIndex").toString());
//					nbtonextpage = Integer.parseInt(json2.getJSONObject("cursor").getString("currentPageIndex"));
//				} catch (NumberFormatException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				} catch (JSONException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}
//				nbtonextpage = (nbtonextpage+1) *4;
//				out.println(nbtonextpage);
//				url = new URL(
//					    "https://ajax.googleapis.com/ajax/services/search/web?v=1.0&"
//							    + "start=" + nbtonextpage + "&q=" +p + "&userip=USERS-IP-ADDRESS");
//			
//			}
//			
//			out.println("<form action=\"google_request\" method=\"GET\">");
//			out.println("les params: <input type=\"text\" name=\"p1\" value=\"\" size=20 />");
//			out.println("<input type=\"submit\" value=\"submit\" />");
//			out.println("</form>");
//			out.println("</table> </body> </html>");
//			
//			
//			
//			
//			
//		} finally {
//			out.close();
//		}
//	}
	
	public void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {
		resp.setContentType("application/json");
		PrintWriter out = resp.getWriter();
		URL url;
		int nbtonextpage = 0;
		try {
			
			String p = req.getParameter("p1");
			
			url = new URL(
				    "https://ajax.googleapis.com/ajax/services/search/web?v=1.0&"
				    + "start="+ nbtonextpage + "&q=" +p + "&userip=USERS-IP-ADDRESS");
			for(int j = 0; j<1; j++ )
			{
				
				URLConnection connection = url.openConnection();
				//connection.addRequestProperty("Referer", /* Enter the URL of your site here */);
	
				String line;
				StringBuilder builder = new StringBuilder();
				BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
				while((line = reader.readLine()) != null) {
				 builder.append(line);
				}
	
				JSONObject json = null;
				try {
					json = new JSONObject(builder.toString());
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					//e.printStackTrace();
				}
				out.print(json);
				//out.flush();
				
			}
			
		} finally {
			out.close();
		}
	}

}
