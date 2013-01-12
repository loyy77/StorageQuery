<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ page import="org.storage.models.*,java.util.*,org.storage.biz.*,org.storage.dao.*" %>
<%

	request.setCharacterEncoding("utf-8");

	String typeStr=request.getParameter("typeName");
	typeStr=new String(typeStr.getBytes("ISO-8859-1"),"utf-8");
	//typeStr=new String(request.getParameter("typeName").getBytes("utf-8"));
	StorageBiz sb=new StorageBiz();
	System.out.println(typeStr);
	List<Storage> list=new ArrayList<Storage>();
	list=sb.listByType(typeStr);
	/*
	if(typeStr.equals(StorageDao.TYPE_WIRING)){
		list=sb.listByType(typeStr);
	}else if(typeStr.equals(StorageDao.TYPE_FOOD)){
		list=sb.listByType(typeStr);
	}else if(typeStr.equals(StorageDao.TYPE_CLOTHING)){
		System.out.println(typeStr);
		list=sb.listByType(typeStr);
	}else if(typeStr.equals(StorageDao.TYPE_COMMODITY)){
		list=sb.listByType(typeStr);
	}
	*/

	int len=list.size();

%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">


<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>按类型查询</title>
</head>
<body>
查询结果（<%=typeStr %>）
<table>
	<thead>
		<tr>
			<th>商品名</th>
			<th>单价（元）</th>
			<th>数量</th>
			<th>类型</th>
			<th>保质期（天）</th>
			<th>生产日期</th>
		</tr>
	</thead>
	<tbody>
	
	
		<tr><td>
	<%
	if(len==0){
	out.write("无数据!");}
	%></td>
	</tr>
	<%
		for(int i=0;i<len;i++){
			Storage s=list.get(i);
			
		
	%>
		<tr>
			<td><%=s.getName() %></td>
			<td><%=s.getPrice() %></td>
			<td><%=s.getNumber() %></td>
			<td><%=s.getType() %></td>
			<td><%=s.getUseDay() %></td>
			<td><%=s.getMakeTime() %></td>
		</tr>
	<%} %>
	</tbody>
	<tfoot>
	<tr>
		<td></td>
	</tr>
	</tfoot>
	
</table>
<a href="index.html">
重新选择</a>
</body>
</html>