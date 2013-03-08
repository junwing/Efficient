<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="../head.jsp"%>
<%
	String orderFlowNo = utb.nvl(context.getDataValue("orderFlowNo"));
	String businessCode = utb.nvl(context.getDataValue("businessCode")); 
	String authActionType = (String)context.getDataValue("authActionType");
	String lastModifiedTime = utb.nvl(context.getDataValue("lastModifiedTime"));
	String orderSubmitTime = utb.nvl(context.getDataValue("orderSubmitTime"));
	String orderSendTime = utb.nvl(context.getDataValue("orderSendTime"));
	String orderState = utb.nvl(context.getDataValue("orderState")); 
	String customerId = utb.nvl(context.getDataValue("customerId"));
	String userId = utb.nvl(context.getDataValue("userId"));
	String userName = utb.nvl(context.getDataValue("userName"));
	
	// TODO 业务相关字段

	String hostErrorMsg = utb.nvl(context.getDataValue("hostErrorMsg"));
%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<%=utb.getCSS(true)%>
<script language="javascript" src="<%= webAppPath %>scripts/lib/prototype.js"></script>
<script language="javascript" src="<%= webAppPath %>scripts/lib/prototype_EMP.js"></script>
<script language="javascript" src="<%= webAppPath %>scripts/auth_history.js"></script>
<script language="javascript" src="<%= webAppPath %>scripts/public.js"></script>
<script language="javascript" src="<%= webAppPath %>scripts/submitController.js"></script>
<script language="javascript">
var detailTableShowFlag = true; 
var historyQueryDone = false;  

function showDetailTable()
{  
	var detailTableBody = document.getElementById('detailTableBody');
	detailTableBody.style.display = detailTableShowFlag ? 'none' : '';
	detailTableShowFlag = !detailTableShowFlag ;
}

function showHistory(){  
	queryAuthHistory('<%=utb.getURL("queryAuthInfoAjax.do")%>','<%=orderFlowNo%>','<%=businessCode%>');
}

function doAccept(submitButton)
{
	submitButton.disabled = true;
	document.submitForm.authActionType.value += '0';
	var controller = new SubmitController();
	controller.disableElement();
	document.submitForm.submit();
} 

function doReject(submitButton)
{
	var rejectTable = document.getElementById('rejectTable');
	var cancleButton = document.getElementById('cancleButton');
	var acceptButton = document.getElementById('acceptButton');
	if(rejectTable.style.display == 'none')
	{
		rejectTable.style.display = '';
		cancleButton.style.display = '';
		acceptButton.style.display = 'none';
		return;
	}else{
		var rejectReasonText = document.getElementById('rejectReasonText').value;
		if(isEmpty(rejectReasonText))
		{
			alert('<liana:I18N name="请填写拒绝原因"/>');
			return;
		}
		submitButton.disabled = true;
		document.submitForm.authActionType.value += '1';
		document.submitForm.authRejectReason.value = rejectReasonText;
		var controller = new SubmitController();
		controller.disableElement();
		document.submitForm.submit();
	}
}

function doCancle()
{
	var rejectTable = document.getElementById('rejectTable');
	var cancleButton = document.getElementById('cancleButton');
	var acceptButton = document.getElementById('acceptButton');
	rejectTable.style.display = 'none';
	cancleButton.style.display = 'none';
	acceptButton.style.display = '';
}

function doDelete(submitButton)
{
	var dropReasonText = document.getElementById('dropReasonText').value;
	if(isEmpty(dropReasonText))
	{
		alert('<liana:I18N name="请填写删除原因"/>');
		return;
	}
	if(!window.confirm('<liana:I18N name="您确实要删除该指令?"/>')){
		return;
	}
	submitButton.disabled = true;
	document.dropForm.dropReason.value = dropReasonText;
	document.dropForm.submit();
}

function closeWindow()
{
	parent.closeWindow();
}
/**
* 隐藏按钮
*/
function doHidden()
{
	document.getElementById("button_area").style.display="none";
	document.getElementById("button_area_print").style.display="none";
}
/**
* 显示按钮
*/
function doAppear()
{
	document.getElementById("button_area").style.display="";
	document.getElementById("button_area_print").style.display="";
}
/**
* 打印
*/
function doPrint()
{
	doHidden();
	print();
	//parent.closeWindow();
	doAppear();
}
function init()
{
	showHistory();
}

function doBack(){
	document.submitForm.orderFlowNo.value='<%=orderFlowNo%>';
	document.submitForm.businessCode.value='<%=businessCode%>';
	document.submitForm.authActionType.value="74";
	document.submitForm.lastModifiedTime.value='<%=lastModifiedTime%>';
	document.submitForm.submit();
}
</script>
</head>
<body onload="init();">
<div>
	<%
		if(authActionType == null)
		{
	%>
		<div class="button_area" id="button_area_print"> 
			<%=utb.getButton("submitButton","打印","javascript:commonprint('{chineseName}交易明细','main_print',740,700)")%>
		</div>
	<%
		} else {
	%>
		<div class="button_area" id="button_area_print"></div>
	<%
		}
	%>
	<table width="95%" border="0" cellpadding="0" cellspacing="0" class="detail_table" id="main_print" style="word-break: break-all;">
		<tr class="title">
			<td colspan="4" align="center" onclick="showDetailTable();" >
				<liana:I18N name="指令明细"/>
			</td>
		</tr>
		<tbody id="detailTableBody">
			<tr>
				<td class="title" width="100"><liana:I18N name="指令序号"/></td>
				<td width="30%"><%=orderFlowNo%></td>
				<td class="title" width="120"><liana:I18N name="交易状态"/></td>
				<td ><%=utb.getDisplayAt("CB_ORDER_STATE", orderState)%></td>
			</tr>
			<tr>
				<td class="title"><liana:I18N name="交易类型"/></td>
				<td ><%=utb.getBusinessAlias(businessCode)%></td>
				<td class="title" ><liana:I18N name="提交人"/></td>
				<td ><%=userName%></td>
			</tr>
			<tr>
				<td class="title" ><liana:I18N name="提交时间"/></td>
				<td ><%=utb.getFmtDate(orderSubmitTime,"yyyy年MM月dd日 HH:mm:ss")%></td>
				<td class="title" ><liana:I18N name="发送银行时间"/></td>
				<td ><%=utb.getFmtDate(orderSendTime,"yyyy年MM月dd日 HH:mm:ss")%></td>
			</tr>
			<!-- 
				TODO 业务相关字段展示
			 -->
			<tr>
				<td class="title" ><liana:I18N name="银行反馈"/></td>
				<td colspan="3"><%=hostErrorMsg%></td>
			</tr>
		</tbody>
	</table>
	<table id="historyTable" width="95%" style="" align="center" class="history_table" border="0" cellpadding="0" cellspacing="0" >
		<thead id="historyTableHead">
			<tr><td align="center" colspan="3"><liana:I18N name="交易处理历史"/></td></tr>
		</thead>
		<tbody id="historyTableBody" style="">
			<tr class="title">
				<td width="150" ><liana:I18N name="处理时间"/></td>
				<td width="200" ><liana:I18N name="处理人"/></td>
				<td ><liana:I18N name="处理意见"/></td>
			</tr>
		</tbody>
	</table>
	<table id="rejectTable" width="95%" border="0" cellpadding="0" cellspacing="0" class="detail_table" style="display:none">
		<tr>
			<td width="100" class="title" ><span class="notNullInput">* </span><liana:I18N name="拒绝原因"/></td>
			<td ><textarea id="rejectReasonText" rows="2" cols="40" onblur="checkLength(this,100);"></textarea></td>
		</tr>
	</table>
	<div class="button_area" id="button_area">
		<%=utb.getAuthButton(authActionType,"flag") %>
		<%=utb.getButton("cancleButton","取消","doCancle();","style=\"display:none;\" class=\"button\"")%>
	</div>

</div>
<form name="submitForm" method="post" action="{bsnCode}_singleDeal.do">
	<%=utb.getRequiredHtmlFields(true)%>
	<input type="hidden" name="orderFlowNo" value="<%=orderFlowNo%>">
	<input type="hidden" name="businessCode" value="<%=businessCode%>">
	<input type="hidden" name="authActionType" value="<%=authActionType%>">
	<input type="hidden" name="lastModifiedTime" value="<%=lastModifiedTime%>">
	<input type="hidden" name="authRejectReason" value="">
</form>
</body>
</html>
