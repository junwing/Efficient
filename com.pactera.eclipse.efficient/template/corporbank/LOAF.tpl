		<!-- {chineseName}查询 -->
		<config businessCode="{tableName}_QUERY" tableName="{tableName}">
			<configField dataField="orderFlowNo" tableField="{tablePrefix}_FLOWNO" isPrimaryKey="true" />
			<configField dataField="session_customerId" tableField="{tablePrefix}_CSTNO"/>
			<configField dataField="businessCode" tableField="{tablePrefix}_BSNCODE" />
			<configField dataField="userId" tableField="{tablePrefix}_OPRNO"/>
			<configField dataField="userName" tableField="{tablePrefix}_OPRNAME" />
			<configField dataField="orderSubmitTime" tableField="{tablePrefix}_SUBMITTIME" />
			<configField dataField="accountNo" tableField="{tablePrefix}_ACCOUNTNO" />
			<configField dataField="hostCode" tableField="{tablePrefix}_HOSTCODE" />
			<configField dataField="orderSendTime" tableField="{tablePrefix}_SENDTIME" />
			<configField dataField="lastModifiedTime" tableField="{tablePrefix}_LASTMODIFIEDTIME" />
			<configField dataField="orderState" tableField="{tablePrefix}_STT" />
			<configField dataField="customerName" tableField="{tablePrefix}_CSTNAME" />
		</config>

		<!-- {chineseName}录入 -->
		<config businessCode="{tableName}_ADD" tableName="{tableName}">
			<configField dataField="orderFlowNo" tableField="{tablePrefix}_FLOWNO" isPrimaryKey="true" />
			<configField dataField="session_customerId" tableField="{tablePrefix}_CSTNO" />
			<configField dataField="session_userId" tableField="{tablePrefix}_OPRNO" />
			<configField dataField="session_userName" tableField="{tablePrefix}_OPRNAME" />
			<configField dataField="currentBusinessCode" tableField="{tablePrefix}_BSNCODE" />	
			<configField dataField="orderSubmitTime" tableField="{tablePrefix}_SUBMITTIME" />
			<configField dataField="hostCode" tableField="{tablePrefix}_HOSTCODE" />
			<configField dataField="payRem" tableField="{tablePrefix}_REM" />
			<configField dataField="lastModifiedTime" tableField="{tablePrefix}_LASTMODIFIEDTIME" />
			<configField dataField="orderState" tableField="{tablePrefix}_STT" />
		</config>

		<!-- {chineseName}状态更新 -->
		<config businessCode="{tableName}_SEND" tableName="{tableName}">
			<configField dataField="orderFlowNo" tableField="{tablePrefix}_FLOWNO" isPrimaryKey="true" />
			<configField dataField="hostErrorCode" tableField="{tablePrefix}_HOSTCODE" />
			<configField dataField="hostErrorMsg" tableField="{tablePrefix}_BANKREM" />
			<configField dataField="orderSendTime" tableField="{tablePrefix}_SENDTIME" />
			<configField dataField="orderState" tableField="{tablePrefix}_STT" />
			<configField dataField="host_seq" tableField="{tablePrefix}_HOSTFLOWNO" />
			<configField dataField="chnl_seq" tableField="{tablePrefix}_CHNLSEQ" />
			<configField dataField="customerName" tableField="{tablePrefix}_CSTNAME" />
			<configField dataField="securityName" tableField="{tablePrefix}_SECURITYNAME" />
			<configField dataField="flowNo" tableField="{tablePrefix}_SECFLOWNO" />
		</config>

