<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="../head.jsp"%>
<%
	String accoutnNo = utb.get("accoutnNo");
	String accoutnName = utb.get("accoutnName");
%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<%=utb.getCSS(true)%>
<script language="javascript" src="<%=webAppPath%>scripts/lib/prototype.js"></script>
<script language="javascript" src="<%=webAppPath%>scripts/lib/prototype_EMP.js"></script>
<script language="javascript" src="<%=webAppPath%>scripts/public.js"></script>
<script language="javascript" src="<%=webAppPath%>scripts/lib/effects.js"></script>
<script language="javascript" src="<%=webAppPath%>scripts/lib/window.js"></script>
<script language="javascript" src="<%=webAppPath%>scripts/window.js"></script>
<script language="javascript" src="<%=webAppPath%>scripts/search.js"></script>
<script language="javascript" src="<%=webAppPath%>scripts/turnpage.js"></script>
<script language="javascript" src="<%=webAppPath%>scripts/money.js"></script>
<script language="javascript">
function doSubmit(){
	document.submitForm.submit();
}
</script>
</head>
<body>
<div id="main_title"></div>
<div id="main">
	<div><%=utb.getPosition()%></div>
	<table width="98%" border="0" cellpadding="2" cellspacing="0" class="form_table_border">	
		<tr>
			<td width="20%" align="right" class="title">�����˺ţ�</td>
			<td width="30%" align="left"><%=accoutnNo%></td>
			<td width="20%" align="right" class="title">�˻����ƣ�</td>
			<td width="30%" align="left"><%=accoutnName%></td>
		</tr>
		<!-- 
			TODO ҵ�����ȷ����Ϣչʾ
		 -->
	</table>
	<div class="button_area">	
		<div>
			<%=utb.getButton("nextButton","ȷ��","doSubmit()") %>
		</div>
	</div>
</div>
</body>
<form method="post" name="submitForm" action="{bsnCode}_{englishName}Submit.do">
	<%=utb.getRequiredHtmlFields(true)%>
</form>
</html>