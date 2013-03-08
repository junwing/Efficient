<?xml version="1.0" encoding="UTF-8" ?>


<MCITransaction trxName="{bizId}" trxCode="{bizId}">
	<datas>
		<refData access="private" refId="customerName"/>
		<refData access="private" refId="accountNo"/>
		<refData access="private" refId="currencyType"/>
		<refData access="private" refId="hostErrorCode"/>
		<refData access="private" refId="hostErrorMsg"/>
		<refData access="private" refId="errorMessage"/>
		<refData access="private" refId="amount"/>
		<refData access="private" refId="orderFlowNo"/>
		<refData access="private" refId="lastModifiedTime"/>
		<refData access="private" refId="signData"/>
		<refData access="private" refId="signTime"/>
		<refData access="private" refId="authActionType"/>
		<refData access="private" refId="logFieldDefine"/>
		<refData access="private" defaultValue="0" refId="offFlag"/>
		<refData access="private" refId="flowNo"/>
		<refData access="private" refId="payAmount"/>
		<refData access="private" refId="orderSubmitTime"/>
		<refData access="private" defaultValue="1" refId="turnPageBeginPos"/>
		<refData access="private" defaultValue="10" refId="turnPageShowNum"/>
		<refData access="private" refId="turnPageTotalNum"/>
		<refData access="private" refId="beginDate"/>
		<refData access="private" refId="endDate"/>
		<refData access="private" refId="businessCode"/>
		<refData access="private" refId="userId"/>
		<refData access="private" refId="userName"/>
		<refData access="private" refId="authRejectReason"/>
		<refData access="private" refId="authResult"/>
		<refData access="private" refId="beginAmt"/>
		<refData access="private" refId="endAmt"/>
		<refData access="private" refId="orderState"/>
		<refData access="private" defaultValue="0" refId="rollBackFlag"/>
	</datas>
	<document/>
	<services/>
	<formats/>
	<operation id="query{englishName}List" name="查询{chineseName}列表">
		<document/>
		<flow name="flow1" x="10" height="400" width="500" y="10">
			<StartAction name="StartAction0" x="177" height="40" width="100" y="49">
				<transition name="transition0" dest="QueryDataListByPageUsingSpecialSQLAction0"/>
			</StartAction>
			<EndAction name="EndAction0" x="178" height="40" width="100" y="328"/>
			<QueryDataListByPageUsingSpecialSQLAction name="QueryDataListByPageUsingSpecialSQLAction0" queryFields="SELECT {tablePrefix}_FLOWNO, {tablePrefix}_BSNCODE, {tablePrefix}_SUBMITTIME, {tablePrefix}_ACCOUNTNO, {tablePrefix}_TRANAMT, {tablePrefix}_LASTMODIFIEDTIME, {tablePrefix}_STT" width="100" beginPosFieldName="turnPageBeginPos" transactionType="1" sortParam="ORDER BY {tablePrefix}_FLOWNO DESC" outputFields="orderFlowNo||businessCode||orderSubmitTime||accountNo||currencyType||securityNo||securityAccountNo||securityOrderNo||amount||lastModifiedTime||orderState" y="188" querySql="SELECT * FROM {tableName}&#13;&#10;WHERE {tablePrefix}_CSTNO = ?session_customerId AND&#13;&#10;  (?businessCode IS NULL OR {tablePrefix}_BSNCODE = ?businessCode) AND &#13;&#10;  ( {tablePrefix}_OPRNO = ?session_userId OR {tablePrefix}_FLOWNO IN (&#13;&#10;    SELECT FLOWNO FROM VIEW_CB_USER_INVOLVED_ORDER&#13;&#10;    WHERE CSTNO=?session_customerId AND USERNO = ?session_userId AND (?businessCode IS NULL OR BSNCODE = ?businessCode))) AND&#13;&#10;  (?orderState IS NULL OR {tablePrefix}_STT=?orderState ) AND&#13;&#10;  (?endDate IS NULL OR {tablePrefix}_SUBMITTIME &lt;= ?endDate) AND&#13;&#10;  (?beginDate IS NULL OR {tablePrefix}_SUBMITTIME &gt;= ?beginDate) AND&#13;&#10;  (?endAmt IS NULL OR {tablePrefix}_TRANAMT &lt;= ?endAmt) AND&#13;&#10;  (?beginAmt IS NULL OR {tablePrefix}_TRANAMT &gt;= ?beginAmt) AND&#13;&#10;  (?accountNo IS NULL OR {tablePrefix}_ACCOUNTNO LIKE ?accountNo)" x="177" height="40" totalNumFieldName="turnPageTotalNum" showNumFieldName="turnPageShowNum">
				<transition name="transition1" dest="EndAction0"/>
			</QueryDataListByPageUsingSpecialSQLAction>
		</flow>
		<input>
			<refData refId="accountNo"/>
			<refData refId="beginAmt"/>
			<refData refId="endAmt"/>
			<refData refId="orderState"/>
			<refData refId="beginDate"/>
			<refData refId="endDate"/>
			<refData refId="turnPageBeginPos"/>
			<refData refId="businessCode"/>
		</input>
		<output/>
	</operation>
	<operation id="query{englishName}Detail" name="查询{chineseName}明细">
		<document/>
		<flow name="flow1" x="10" height="400" width="500" y="10">
			<StartAction name="StartAction0" x="186" height="40" width="100" y="57">
				<transition name="transition2" dest="QureyDataDetailAction0"/>
			</StartAction>
			<EndAction name="EndAction0" x="186" height="40" width="100" y="286"/>
			<QureyDataDetailAction name="QureyDataDetailAction0" x="187" height="40" businessCode="{tableName}_QUERY" throwException="true" width="100" transactionType="1" y="169">
				<transition name="transition3" dest="EndAction0"/>
			</QureyDataDetailAction>
		</flow>
		<input>
			<refData refId="authActionType"/>
			<refData refId="orderFlowNo"/>
		</input>
		<output/>
	</operation>
	<operation id="update{englishName}" name="更新{chineseName}">
		<document/>
		<flow name="flow1" x="10" height="459" width="532" y="10">
			<StartAction name="StartAction0" x="52" height="40" width="62" y="45">
				<transition name="transition142" dest="CheckCustomerAccountAction0"/>
			</StartAction>
			<EndAction name="EndAction0" x="132" height="40" width="110" y="397"/>
			<JournalAction name="JournalAction1" x="136" height="40" logDefineName="logFieldDefine" width="100" procDefine="CommonLog" y="306">
				<transition name="transition34" dest="EndAction0"/>
			</JournalAction>
			<SetValueAction name="SetValueAction6" x="141" height="40" dataName="logFieldDefine" label="设置日志内容" width="100" dataValue="orderFlowNo&amp;authActionType" y="133">
				<transition name="transition146" dest="SetTimeAction2"/>
			</SetValueAction>
			<SetValueAction name="SetValueAction7" x="332" height="54" dataName="rollBackFlag" label="设置操作类型为修改" width="100" dataValue="1" y="125">
				<transition name="transition106" dest="SetValueAction6"/>
			</SetValueAction>
			<SignVerify name="SignVerify0" x="331" height="40" outputFields="accountNo||currencyType||amount" commonFields="session_customerId||session_userId||signTime||currentBusinessCode" width="100" y="45" signDataField="signData">
				<transition name="transition108" dest="SetValueAction7"/>
			</SignVerify>
			<SetTimeAction name="SetTimeAction2" x="141" height="53" label="设置最后修改时间" width="100" timeFieldName="lastModifiedTime" y="221" timeFormat="yyyyMMddHHmmss">
				<transition name="transition147" dest="SetValueAction8"/>
			</SetTimeAction>
			<CheckCustomerAccountAction name="CheckCustomerAccountAction0" x="167" height="40" width="117" accountFieldName="accountNo" y="45">
				<transition name="transition143" dest="SignVerify0"/>
			</CheckCustomerAccountAction>
			<SetValueAction name="SetValueAction8" x="312" height="40" dataName="payAmount" width="100" dataValue="0.00" y="226">
				<transition name="transition148" dest="UpdateFlowAction0"/>
			</SetValueAction>
			<UpdateFlowAction name="UpdateFlowAction0" width="100" fieldAccount="accountNo" fieldAmount="payAmount" y="305" x="313" provider="{provider}" fieldCurrency="currencyType" fieldBusinessCode="businessCode" height="40">
				<transition name="transition149" dest="JournalAction1"/>
			</UpdateFlowAction>
		</flow>
		<input>
			<refData refId="accountNo"/>
			<refData refId="currencyType"/>
			<refData refId="amount"/>
			<refData refId="signData"/>
			<refData refId="signTime"/>
			<refData refId="businessCode"/>
			<refData refId="orderFlowNo"/>
		</input>
		<output/>
	</operation>
	<operation id="delete{englishName}" name="删除{chineseName}">
		<document/>
		<flow name="flow1" x="10" height="400" width="500" y="10">
			<StartAction name="StartAction0" x="89" height="40" width="100" y="42">
				<transition name="transition0" dest="SetValueAction0"/>
			</StartAction>
			<EndAction name="EndAction0" x="94" height="40" width="100" y="345"/>
			<SetValueAction name="SetValueAction0" x="253" height="65" dataName="rollBackFlag" label="设置操作类型为删除" width="116" dataValue="96" y="29">
				<transition name="transition1" dest="SetValueAction1"/>
			</SetValueAction>
			<SetValueAction name="SetValueAction1" x="243" height="63" dataName="logFieldDefine" label="设置日志内容" width="135" dataValue="orderFlowNo&amp;authActionType" y="115">
				<transition name="transition6" dest="SetValueAction2"/>
			</SetValueAction>
			<SetTimeAction name="SetTimeAction0" x="268" height="57" label="设置最后修改时间" width="100" timeFieldName="lastModifiedTime" y="218" timeFormat="yyyyMMddHHmmss">
				<transition name="transition9" dest="JournalAction0"/>
			</SetTimeAction>
			<JournalAction name="JournalAction0" x="269" height="40" logDefineName="logFieldDefine" width="100" y="344">
				<transition name="transition5" dest="EndAction0"/>
			</JournalAction>
			<SetValueAction name="SetValueAction2" x="94" height="55" dataName="authActionType|authRejectReason" label="赋值" width="100" dataValue="92|" y="120">
				<transition name="transition7" dest="DealSingleFlowAction0"/>
			</SetValueAction>
			<DealSingleFlowAction name="DealSingleFlowAction0" width="100" fieldDealResult="authActionType" y="218" x="93" filedLastModifiedTime="lastModifiedTime" provider="{provider}" label="清理临时表" fieldRemark="authRejectReason" fieldBusinessCode="businessCode" height="56">
				<transition name="transition8" dest="SetTimeAction0"/>
			</DealSingleFlowAction>
		</flow>
		<input>
			<refData refId="orderFlowNo"/>
			<refData refId="orderState"/>
			<refData refId="lastModifiedTime"/>
			<refData refId="businessCode"/>
		</input>
		<output/>
	</operation>
	<operation id="query{englishName}AuthList" name="查询{chineseName}授权列表">
		<document/>
		<flow name="flow1" x="10" height="400" width="500" y="10">
			<StartAction name="StartAction0" x="177" height="40" width="100" y="28">
				<transition name="transition4" dest="SetValueAction0"/>
			</StartAction>
			<EndAction name="EndAction0" x="177" height="40" width="100" y="309"/>
			<QueryDataListByPageUsingSpecialSQLAction name="QueryDataListByPageUsingSpecialSQLAction0" queryFields="SELECT {tablePrefix}_FLOWNO,{tablePrefix}_OPRNAME,{tablePrefix}_SUBMITTIME, {tablePrefix}_ACCOUNTNO, {tablePrefix}_CRY, {tablePrefix}_TRANAMT, {tablePrefix}_LASTMODIFIEDTIME, ATQ_ACTIONTYPE" width="100" beginPosFieldName="turnPageBeginPos" transactionType="1" sortParam="order by {tablePrefix}_submittime desc" outputFields="orderFlowNo||userName||orderSubmitTime||accountNo||currencyType||amount||lastModifiedTime||authActionType" y="218" querySql="SELECT * FROM {tableName},CB_AUTH_QUEUE WHERE ATQ_FLOWNO={tablePrefix}_FLOWNO AND ATQ_CSTNO=?session_customerId AND ATQ_USERNO=?session_userId AND ATQ_BSNCODE=?businessCode AND {tablePrefix}_SUBMITTIME&gt;=?beginDate AND {tablePrefix}_SUBMITTIME&lt;=?endDate" x="178" height="40" totalNumFieldName="turnPageTotalNum" showNumFieldName="turnPageShowNum">
				<transition name="transition2" dest="EndAction0"/>
			</QueryDataListByPageUsingSpecialSQLAction>
			<SetValueAction name="SetValueAction0" dataName="currentBusinessCode" height="52" x="178" label="设置当前交易代码（防止从主页面的消息连接进入业务复核时交易代码错误）" width="100" dataValue="900101" y="111">
				<transition name="transition5" dest="QueryDataListByPageUsingSpecialSQLAction0"/>
			</SetValueAction>
		</flow>
		<input>
			<refData refId="beginDate"/>
			<refData refId="endDate"/>
			<refData refId="turnPageBeginPos"/>
			<refData refId="businessCode"/>
		</input>
		<output/>
	</operation>
	<operation id="query{englishName}ForceAuthList" name="查询{chineseName}强制授权列表">
		<document/>
		<flow name="flow1" x="10" height="400" width="500" y="10">
			<StartAction name="StartAction0" x="182" height="40" width="100" y="76">
				<transition name="transition0" dest="QueryDataListUsingSpecialSQLAction0"/>
			</StartAction>
			<EndAction name="EndAction0" x="182" height="40" width="100" y="284"/>
			<QueryDataListUsingSpecialSQLAction name="QueryDataListUsingSpecialSQLAction0" width="100" transactionType="1" sortParam="order by bsf_flowno desc" outputFields="orderFlowNo||userName||orderSubmitTime||accountNo||currencyType||securityNo||securityAccountNo||securityOrderNo||amount||lastModifiedTime||authActionType" y="173" querySql="SELECT DISTINCT &#13;&#10; {tablePrefix}_FLOWNO,{tablePrefix}_OPRNAME,{tablePrefix}_SUBMITTIME, {tablePrefix}_ACCOUNTNO, {tablePrefix}_CRY, &#13;&#10; {tablePrefix}_SECURITYNO,{tablePrefix}_SECACCOUNTNO, {tablePrefix}_SECORDERNO, {tablePrefix}_TRANAMT,   {tablePrefix}_LASTMODIFIEDTIME, '2' &#13;&#10; FROM {tableName},CB_AUTH_QUEUE&#13;&#10; WHERE ATQ_FLOWNO={tablePrefix}_FLOWNO AND ATQ_CSTNO=?session_customerId  AND ATQ_BSNCODE=?businessCode AND {tablePrefix}_STT IN('10','00') AND  {tablePrefix}_SUBMITTIME&gt;=?beginDate AND {tablePrefix}_SUBMITTIME&lt;=?endDate" x="182" height="40">
				<transition name="transition1" dest="EndAction0"/>
			</QueryDataListUsingSpecialSQLAction>
		</flow>
		<input>
			<refData refId="beginDate"/>
			<refData refId="endDate"/>
			<refData refId="businessCode"/>
		</input>
		<output/>
	</operation>
	<operation id="query{englishName}AuthResult" name="查询{chineseName}审核结果">
		<document/>
		<flow name="flow1" x="10" height="400" width="500" y="10">
			<StartAction name="StartAction0" x="163" height="40" width="100" y="61">
				<transition name="transition0" dest="QueryDataListByPageUsingSpecialSQLAction0"/>
			</StartAction>
			<EndAction name="EndAction0" x="162" height="40" width="100" y="270"/>
			<QueryDataListByPageUsingSpecialSQLAction name="QueryDataListByPageUsingSpecialSQLAction0" queryFields="SELECT ATH_BSNCODE,ATH_FLOWNO, ATH_ACTIONTYPE,ATH_TIME,ATH_AUTHFLAG" width="100" beginPosFieldName="turnPageBeginPos" transactionType="1" sortParam="ORDER BY ATH_TIME DESC" outputFields="businessCode||orderFlowNo||authActionType||authDateTime||authResult" y="165" querySql="SELECT * FROM (select ATH_CSTNO,ATH_USERNO,ATH_BSNCODE,ATH_FLOWNO,ATH_ACTIONTYPE,ATH_TIME,ATH_AUTHFLAG from CB_AUTH_BACK_HISTORY a&#13;&#10;where ATH_ACTIONTYPE='7' AND ATH_USERNO = ?session_userId AND &#13;&#10;ATH_TIME = (SELECT MAX(ATH_TIME) FROM CB_AUTH_BACK_HISTORY WHERE A.ATH_FLOWNO = ATH_FLOWNO AND A.ATH_USERNO = ATH_USERNO AND ATH_ACTIONTYPE='7') &#13;&#10;UNION&#13;&#10;select ATH_CSTNO,ATH_USERNO,ATH_BSNCODE,ATH_FLOWNO,ATH_ACTIONTYPE,ATH_TIME,ATH_AUTHFLAG from CB_AUTH_HISTORY),{tableName} &#13;&#10;WHERE ATH_FLOWNO = {tablePrefix}_FLOWNO &#13;&#10;AND {tablePrefix}_BSNCODE = ATH_BSNCODE &#13;&#10;AND {tablePrefix}_BSNCODE = ?businessCode &#13;&#10;AND ATH_CSTNO = ?session_customerId&#13;&#10;AND ATH_USERNO = ?session_userId &#13;&#10;AND (?endDate IS NULL OR ATH_TIME &lt;= ?endDate) &#13;&#10;AND (?beginDate IS NULL OR ATH_TIME &gt;= ?beginDate) &#13;&#10;AND (?authActionType IS NULL OR ATH_ACTIONTYPE = ?authActionType)  &#13;&#10;AND ATH_ACTIONTYPE&lt;&gt;'3' &#13;&#10;AND (?authResult IS NULL OR ATH_AUTHFLAG = ?authResult )" x="163" height="40" totalNumFieldName="turnPageTotalNum" showNumFieldName="turnPageShowNum">
				<transition name="transition1" dest="EndAction0"/>
			</QueryDataListByPageUsingSpecialSQLAction>
		</flow>
		<input>
			<refData refId="turnPageBeginPos"/>
			<refData refId="businessCode"/>
			<refData refId="authActionType"/>
			<refData refId="authResult"/>
			<refData refId="beginDate"/>
			<refData refId="endDate"/>
		</input>
		<output/>
	</operation>
</MCITransaction>