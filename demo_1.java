import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.flaps.utility.DBUtils;
import com.flaps.utility.DataSource4T;
import com.flaps.utility.DateUtils;
import com.flaps.utility.ResultSetTool;
import com.flaps.utility.StringUtils;


public class Test1 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Test1 t = new Test1();
		String result = t.testFunc();
		System.out.println(result);
		
		
		
		
		List<List<Map<String, String>>> dataList =  t.testArrayList();
		for(int i=0; i < dataList.size(); i++){
			List<Map<String, String>> list = dataList.get(i);
			for(int j=0; j < list.size(); j++){
				Map<String, String> map = list.get(j);
				System.out.println("Name: " + map.get("Name"));
				System.out.println("Id: " + map.get("Id"));
			}
			
		}
		
		
		
		//t.getCompeteBrandSalesRsList("93/01/29");
		
		//System.out.println(StringUtils.dateFormat(DateUtils.add(StringUtils.nowDate(), -1)));
		//System.out.println(StringUtils.nowDate());
		//System.out.println(StringUtils.today());
		
//		List spList = new ArrayList();
//		spList.add("123");
//		spList.add("456");
//		spList.add("789");
//		for(Object _obj : spList.toArray()){
//			System.out.println(_obj.toString());
//			
//		}
//		
//		Map allMap = new HashMap();
//		
//		allMap.put("123", "147");
//		allMap.get("123");
		
		//List compList = new ArrayList();
		
		//Map allMap = new HashMap();
		
		
		
		
		
		
		
//		/**       ------------------------------------------------------------------       **/
//		List allList = new ArrayList();
//		Map allMap = new HashMap();
//		
//		Map compMap = new HashMap();
//		
//		List<Map<String, String>> data = new ArrayList<Map<String, String>>(); //dataList.get(5);
//		
//		List<String> spList = new ArrayList<String>();
//		
//		List<String> compList = new ArrayList<String>();
//		
//		String preSP = "";
//		String preCOM = "";
//		
//		for(Object _obj : data.toArray()){
//			Map<String, String> dataMap =  (Map<String, String>)_obj;
//			//next SP
//			if(! preSP.equals("") && !preSP.equals(dataMap.get("SPCode"))){
//				
//				allMap.put("compList", compList);
//				allMap.put("compMap", compMap);
//				allList.add(allMap);
//				
//				//restart
//				allMap = new HashMap();
//				compList = new ArrayList();
//				compMap = new HashMap();
//				
//				
//			}
//			
//			//
//			if(!preSP.equals(dataMap.get("SPCode"))){
//				allMap.put("spCode", dataMap.get("SPCode"));
//				allMap.put("spName", dataMap.get("SPName"));
//			}
//			
//			if(!compList.contains(dataMap.get("BrandCode"))){
//				compList.add(dataMap.get("BrandCode"));
//				compMap.put(dataMap.get("BrandCode"), dataMap.get("BrandName"));
//			}
//			
//			
//			allMap.put(dataMap.get("SPCode")+"_"+dataMap.get("BrandCode")+"_"+dataMap.get("SDate")+"_Total", dataMap.get("Total"));
//			allMap.put(dataMap.get("SPCode")+"_"+dataMap.get("BrandCode")+"_Rank", dataMap.get("Rank"));
//			allMap.put(dataMap.get("SPCode")+"_"+dataMap.get("BrandCode")+"_TotalRank", dataMap.get("TotalRank"));
//			
//			allMap.put(dataMap.get("SPCode")+"_"+dataMap.get("BrandCode")+"_小計", StringUtils.toDouble((String)allMap.get(dataMap.get("SPCode")+"_"+dataMap.get("BrandCode")+"_小計"))  + Double.valueOf(dataMap.get("Total")));
//			
//			preSP = dataMap.get("SPCode");
//		} //for loop 
//		
//		if (allList.size() > 0 ){
//			allMap.put("compList", compList);
//			allMap.put("compMap", compMap);
//			allList.add(allMap);
//		}
		
//		return allList;
	}
	
	public String testFunc(){
		String str = "";
		str = "abc";
		return str;
	}
	public List<List<Map<String, String>>>  testArrayList(){
		
		List<List<Map<String, String>>> dataList = new ArrayList<List<Map<String, String>>>();
		
		for(int j =0 ; j < 100 ; j++){
			List<Map<String, String>> data = new ArrayList<Map<String, String>>();
			
			for(int i =0 ; i < 10 ; i++){
				Map<String, String> map = new HashMap<String, String>();
				map.put("Name", "Ted" + i);
				map.put("Id", "G121729472" + i + i);
				data.add(map);
			}
			dataList.add(data);
		}
		
		
		
		
		
		
		
		return dataList;
	}
	
	
	
	public List<List<Map<String, String>>> getCompeteBrandSalesRsList(String date){
		Connection conn = null; 
		Statement stmt = null; 
		ResultSet rs = null;
		
		List<List<Map<String, String>>> dataList = new ArrayList<List<Map<String, String>>>();
		List<Map<String, String>> rsList = null;
		try {
			conn = DataSource4T.getConnection();
			stmt = conn.createStatement();
			
			System.out.println("DCS_CompetBrandSalesM '"+date+"','"+date+"'");
			stmt.execute("DCS_CompetBrandSalesM '"+date+"','"+date+"'");
//			System.out.println("CompeteBrandSales '"+date+"','"+date+"','ktuou2','1','1','"+date+"','"+date+"'");
//			stmt.execute("CompeteBrandSales '"+date+"','"+date+"','ktuou2','1','1','"+date+"','"+date+"'");
			while(true){
				rs = stmt.getResultSet();
				if ((rs == null && stmt.getUpdateCount() != -1)) { // 取得第1個ResultSet或是跳掉不是ResultSet的回傳列;
					if (!stmt.getMoreResults() && stmt.getUpdateCount() == -1) {
						break;
					} else {
						continue;
					}
				}
				if (rs == null)
					break;
				
				rsList = ResultSetTool.getListMapData(rs, false); 
				dataList.add(rsList);
				
				stmt.getMoreResults();
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
		}finally{
			DBUtils.closeRs_Stmt(rs);
			DBUtils.close_Stmt(stmt);
			DBUtils.close_DS_Con(conn, Integer.MIN_VALUE);
		}
		
		return dataList;
	}
	
}
