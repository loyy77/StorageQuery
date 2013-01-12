<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ page import="org.storage.models.*,java.util.*,org.storage.biz.*,org.storage.dao.*" %>
<%

	request.setCharacterEncoding("utf-8");
//response.setCharacterEncoding("utf-8");
	String orderBy=request.getParameter("orderBy");
	orderBy=new String(orderBy.getBytes("ISO-8859-1"),"utf-8");
	StorageBiz sb=new StorageBiz();

	List<Storage> list=new ArrayList<Storage>();
	if(orderBy.equals("name")){list=sb.listOrderByName();}
	else if(orderBy.equals("price")){list=sb.listOrderByPrice();}
	else if(orderBy.equals("number")){list=sb.listOrderByNumber();}
	else if(orderBy.equals("makeTime")){list=sb.listOrderByMakeTime();}
	else{
		list=sb.listOrderByName();
	}
	
		
	
	int len=list.size();

%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">


<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>查看库存</title>

<style>
	.line{
		border-bottom-color: red;
	}
</style>
</head>
<body>
查询结果(按商品<%

		if(orderBy.equals("name")){
			out.write("名字");
		}else if(orderBy.equals("price")){
			out.write("价格");
		}else if(orderBy.equals("number")){
			out.write("数量");
		}else if(orderBy.equals("makeTime")){
			out.write("生产日期");
		}else{
			out.write("名字");
		}
		
		%>排序)
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
		<tr class="line">
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