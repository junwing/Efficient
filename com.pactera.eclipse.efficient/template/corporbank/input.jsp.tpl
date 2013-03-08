<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="../head.jsp"%>
<%

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
	// 数据验证
	// 数据相关处理

	document.submitForm.submit();
}
</script>
</head>
<body>
<div id="main_title" ></div>
<div id="main">
	<div><%=utb.getPosition()%></div>
	<div id="dataDiv">
		<table width="98%" border="0" cellpadding="2" cellspacing="0" class="form_table_border">
			<tr>
				<td align="right">银行账号：</td>
				<td id="accountNoTxt" align="left"></td>
			</tr>
			<!-- 
				TODO 业务相关输入
			 -->
		</table>
		<div class="button_area">	
			<div>
				<%=utb.getButton("nextButton","下一步","doSubmit()") %>
			</div>
		</div>	
	</div>
</div>
</body>
<form method="post" name="submitForm" action="{bsnCode}_{englishName}Confirm.do">
	<%=utb.getRequiredHtmlFields(false)%>
	<!-- data fields -->
</form>
</html>