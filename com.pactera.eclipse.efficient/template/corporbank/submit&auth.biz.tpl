<?xml version="1.0" encoding="UTF-8" ?>


<MCITransaction trxName="{englishName}" trxCode="{englishName}">
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
		<refData access="private" refId="iAuthList"/>
		<refData access="private" refId="iAuthListBak"/>
		<refData access="private" refId="authResult"/>
		<refData access="private" refId="beginAmt"/>
		<refData access="private" refId="endAmt"/>
		<refData access="private" refId="orderState"/>
		<refData access="private" defaultValue="0" refId="rollBackFlag"/>
		<refData access="private" defaultValue="1.0" refId="ver_no"/>
		<refData access="private" refId="bank_id"/>
		<refData access="private" defaultValue="17" refId="snd_chnl_no"/>
		<refData access="private" defaultValue="23" refId="rcv_chnl_no"/>
		<refData access="private" refId="ext_txn_no"/>
		<refData access="private" refId="chnl_dt"/>
		<refData access="private" refId="host_dt"/>
		<refData access="private" refId="chnl_tm"/>
		<refData access="private" refId="host_tm"/>
		<refData access="private" refId="chnl_seq"/>
		<refData access="private" refId="host_seq"/>
	</datas>
	<document/>
	<services/>
	<formats>
		<format id="{tranCode}SendFormat">
			<document>上送报文定义</document>
			<datas version="1.0" packageType="WrapXMLFormat" encoding="gb18030">
				<dataGroup xmlTag="msg">
					<dataGroup xmlTag="comm_head">
						<refData needDesc="false" constant="false" refId="ver_no" fullTag="true" xmlTag="ver_no"/>
						<refData needDesc="false" constant="false" refId="bank_id" fullTag="true" xmlTag="bank_id"/>
						<refData needDesc="false" constant="false" refId="snd_chnl_no" fullTag="true" xmlTag="snd_chnl_no"/>
						<refData needDesc="false" constant="false" refId="rcv_chnl_no" fullTag="true" xmlTag="rcv_chnl_no"/>
						<refData needDesc="false" constant="false" refId="ext_txn_no" fullTag="true" xmlTag="ext_txn_no"/>
						<refData needDesc="false" constant="false" refId="chnl_dt" fullTag="true" xmlTag="chnl_dt"/>
						<refData needDesc="false" constant="false" refId="host_dt" fullTag="true" xmlTag="host_dt"/>
						<refData needDesc="false" constant="false" refId="chnl_tm" fullTag="true" xmlTag="chnl_tm"/>
						<refData needDesc="false" constant="false" refId="host_tm" fullTag="true" xmlTag="host_tm"/>
						<refData needDesc="false" constant="false" refId="chnl_seq" fullTag="true" xmlTag="chnl_seq"/>
						<refData needDesc="false" constant="false" refId="host_seq" fullTag="true" xmlTag="host_seq"/>
						<refData needDesc="false" constant="false" refId="hostErrorCode" fullTag="true" xmlTag="rsp_no"/>
						<refData needDesc="false" constant="false" refId="hostErrorMsg" fullTag="true" xmlTag="rsp_msg"/>
					</dataGroup>
					<dataGroup xmlTag="main_data"/>
				</dataGroup>
			</datas>
		</format>
		<format id="{tranCode}ReceiveFormat">
			<document>返回报文定义</document>
			<datas version="1.0" packageType="WrapXMLFormat" encoding="gb18030">
				<dataGroup xmlTag="msg">
					<dataGroup xmlTag="comm_head">
						<refData needDesc="false" constant="false" refId="ver_no" fullTag="true" xmlTag="ver_no"/>
						<refData needDesc="false" constant="false" refId="bank_id" fullTag="true" xmlTag="bank_id"/>
						<refData needDesc="false" constant="false" refId="snd_chnl_no" fullTag="true" xmlTag="snd_chnl_no"/>
						<refData needDesc="false" constant="false" refId="rcv_chnl_no" fullTag="true" xmlTag="rcv_chnl_no"/>
						<refData needDesc="false" constant="false" refId="ext_txn_no" fullTag="true" xmlTag="ext_txn_no"/>
						<refData needDesc="false" constant="false" refId="chnl_dt" fullTag="true" xmlTag="chnl_dt"/>
						<refData needDesc="false" constant="false" refId="host_dt" fullTag="true" xmlTag="host_dt"/>
						<refData needDesc="false" constant="false" refId="chnl_tm" fullTag="true" xmlTag="chnl_tm"/>
						<refData needDesc="false" constant="false" refId="host_tm" fullTag="true" xmlTag="host_tm"/>
						<refData needDesc="false" constant="false" refId="chnl_seq" fullTag="true" xmlTag="chnl_seq"/>
						<refData needDesc="false" constant="false" refId="host_seq" fullTag="true" xmlTag="host_seq"/>
						<refData needDesc="false" constant="false" refId="hostErrorCode" fullTag="true" xmlTag="rsp_no"/>
						<refData needDesc="false" constant="false" refId="hostErrorMsg" fullTag="true" xmlTag="rsp_msg"/>
					</dataGroup>
					<dataGroup xmlTag="main_data"/>
				</dataGroup>
			</datas>
		</format>
	</formats>
	<operation id="submit{EnglishName}" name="提交{chineseName}">
		<document/>
		<flow name="flow1" x="10" height="518" width="569" y="10">
			<StartAction name="StartAction0" x="91" height="40" width="79" y="40">
				<transition name="transition142" dest="CheckCustomerAccountAction0"/>
			</StartAction>
			<EndAction name="EndAction0" x="216" height="40" width="110" y="392"/>
			<JournalAction name="JournalAction1" x="221" height="40" logDefineName="logFieldDefine" width="100" procDefine="CommonLog" y="314">
				<transition name="transition34" dest="EndAction0"/>
			</JournalAction>
			<StartFlowAction name="StartFlowAction0" width="100" fieldAccount="accountNo" fieldAmount="payAmount" y="314" provider="{provider}" fieldCurrency="currencyType" x="380" height="40">
				<transition id="0" name="transition48" dest="JournalAction1"/>
			</StartFlowAction>
			<GetSequence name="GetSequence0" x="74" height="50" label="获取指令流水号" width="96" sequenceName="{tableName}_NO" y="116" fieldName="orderFlowNo">
				<transition name="transition112" dest="SetTimeAction2"/>
			</GetSequence>
			<SetTimeAction name="SetTimeAction0" x="221" height="46" width="100" timeFieldName="orderSubmitTime" y="217" timeFormat="yyyyMMddHHmmss">
				<transition name="transition144" dest="SetValueAction8"/>
			</SetTimeAction>
			<SetValueAction name="SetValueAction6" x="221" height="51" dataName="logFieldDefine" label="设置日志内容" width="100" dataValue="orderFlowNo&amp;accountNo&amp;currencyType&amp;securityNo&amp;securityAccountNo&amp;amount&amp;authActionType" y="115">
				<transition name="transition104" dest="GetSequence0"/>
			</SetValueAction>
			<SetValueAction name="SetValueAction7" x="380" height="57" dataName="authActionType" label="设置操作类型为录入" width="100" dataValue="3" y="113">
				<transition name="transition106" dest="SetValueAction6"/>
			</SetValueAction>
			<SignVerify name="SignVerify0" x="380" height="40" outputFields="accountNo||currencyType||amount" commonFields="session_customerId||session_userId||signTime||currentBusinessCode" width="100" y="38" signDataField="signData">
				<transition name="transition108" dest="SetValueAction7"/>
			</SignVerify>
			<SetTimeAction name="SetTimeAction2" x="72" height="53" label="设置最后修改时间" width="100" timeFieldName="lastModifiedTime" y="213" timeFormat="yyyyMMddHHmmss">
				<transition name="transition113" dest="SetTimeAction0"/>
			</SetTimeAction>
			<CheckCustomerAccountAction name="CheckCustomerAccountAction0" x="221" height="40" width="100" accountFieldName="accountNo" y="39">
				<transition name="transition143" dest="SignVerify0"/>
			</CheckCustomerAccountAction>
			<SetValueAction name="SetValueAction8" x="380" height="40" dataName="payAmount" width="100" dataValue="0.00" y="221">
				<transition name="transition145" dest="StartFlowAction0"/>
			</SetValueAction>
		</flow>
		<input>
			<refData refId="accountNo"/>
			<refData refId="currencyType"/>
			<refData refId="amount"/>
			<refData refId="signData"/>
			<refData refId="signTime"/>
		</input>
		<output/>
	</operation>
	<operation id="auth{EnglishName}" name="处理{chineseName}">
		<document/>
		<flow name="flow1" x="10" height="692" width="569" y="10">
			<StartAction name="StartAction0" x="25" height="40" width="100" y="26">
				<transition name="transition150" dest="SetValueAction6"/>
			</StartAction>
			<EndAction name="EndAction0" x="45" height="40" width="56" y="635"/>
			<SetValueAction name="SetValueAction1" x="274" height="54" dataName="orderState" label="修改指令状态为失败" width="110" dataValue="91" y="461">
				<transition name="transition73" dest="UpdateChangedDataAction1"/>
			</SetValueAction>
			<SetValueAction name="SetValueAction2" x="423" height="54" dataName="orderState" label="修改指令状态为可疑" width="110" dataValue="99" y="461">
				<transition name="transition127" dest="UpdateChangedDataAction1"/>
			</SetValueAction>
			<SetValueAction name="SetValueAction3" x="134" height="54" dataName="orderState" label="修改指令状态为成功" width="110" dataValue="90" y="461">
				<transition name="transition72" dest="UpdateChangedDataAction1"/>
			</SetValueAction>
			<JournalAction name="JournalAction1" x="24" height="40" logDefineName="logFieldDefine" width="100" procDefine="CommonLog" y="414">
				<transition name="transition34" dest="EndAction0"/>
			</JournalAction>
			<UpdateChangedDataAction name="UpdateChangedDataAction1" x="257" height="53" businessCode="{tableName}_SEND" label="落实指令状态的修改" opType="3" width="143" y="559">
				<transition name="transition78" dest="JournalAction2"/>
			</UpdateChangedDataAction>
			<JournalAction name="JournalAction2" x="278" height="40" logDefineName="logFieldDefine" width="100" y="636">
				<transition name="transition79" dest="EndAction0"/>
			</JournalAction>
			<SetValueAction name="SetValueAction5" x="403" height="52" dataName="orderState" label="修改指令状态为处理中" width="120" dataValue="50" y="168">
				<transition name="transition141" dest="SetTimeAction1"/>
			</SetValueAction>
			<SetTimeAction name="SetTimeAction1" x="412" height="51" label="取发送主机时间" width="100" timeFieldName="orderSendTime" y="258" timeFormat="yyyyMMddHHmmss">
				<transition name="transition94" dest="UpdateChangedDataAction3"/>
			</SetTimeAction>
			<UpdateChangedDataAction name="UpdateChangedDataAction3" x="248" height="59" businessCode="{tableName}_SEND" label="修改状态和发送主机时间" opType="3" width="133" y="255">
				<transition name="transition121" dest="CoreTCPIP1"/>
			</UpdateChangedDataAction>
			<SetValueAction name="SetValueAction6" x="26" height="55" dataName="logFieldDefine" label="设置日志内容" width="100" dataValue="orderFlowNo&amp;authActionType" y="103">
				<transition name="transition151" dest="DealSingleFlowAction0"/>
			</SetValueAction>
			<CoreTCPIP name="CoreTCPIP1" width="111" sendFormatName="{tranCode}SendFormat" serviceName="CoreTCPIPService" tranCode="{tranCode}" y="345" x="258" receiveFormatName="{tranCode}ReceiveFormat" height="40">
				<transition name="transition122" label="交易成功" dest="SetValueAction3" condition="@IsNull($hostErrorCode)"/>
				<transition name="transition123" label="交易失败" dest="SetValueAction1" condition="exception:TranFailException"/>
				<transition name="transition125" label="异常" dest="SetValueAction2" condition="exception:TCPIPCommunicationException"/>
			</CoreTCPIP>
			<DealSingleFlowAction name="DealSingleFlowAction0" width="100" fieldDealResult="authActionType" y="204" x="26" filedLastModifiedTime="lastModifiedTime" provider="{provider}" fieldRemark="authRejectReason" fieldBusinessCode="businessCode" height="40">
				<transition id="0" name="transition146" dest="JournalAction1"/>
				<transition id="1" name="transition152" dest="QureyDataDetailAction0"/>
			</DealSingleFlowAction>
			<QureyDataDetailAction name="QureyDataDetailAction0" x="253" height="40" businessCode="{tableName}_QUERY" throwException="true" width="100" transactionType="0" y="175">
				<transition name="transition153" dest="SetValueAction5"/>
			</QureyDataDetailAction>
		</flow>
		<input>
			<refData refId="orderFlowNo"/>
			<refData refId="authActionType"/>
			<refData refId="authRejectReason"/>
			<refData refId="businessCode"/>
			<refData refId="currencyType"/>
			<refData refId="lastModifiedTime"/>
		</input>
		<output/>
	</operation>
	<operation id="batchAuth{EnglishName}" name="批量处理{chineseName}">
		<document/>
		<flow name="flow1" x="10" height="692" width="569" y="10">
			<StartAction name="StartAction0" x="22" height="40" width="68" y="33">
				<transition name="transition150" dest="SetValueAction6"/>
			</StartAction>
			<EndAction name="EndAction0" x="500" height="38" width="44" y="64"/>
			<SetValueAction name="SetValueAction1" x="319" height="54" dataName="orderState" label="修改指令状态为失败" width="110" dataValue="91" y="465">
				<transition name="transition73" dest="UpdateChangedDataAction1"/>
			</SetValueAction>
			<SetValueAction name="SetValueAction2" x="451" height="54" dataName="orderState" label="修改指令状态为可疑" width="110" dataValue="99" y="447">
				<transition name="transition127" dest="UpdateChangedDataAction1"/>
			</SetValueAction>
			<SetValueAction name="SetValueAction3" x="175" height="54" dataName="orderState" label="修改指令状态为成功" width="110" dataValue="90" y="450">
				<transition name="transition72" dest="UpdateChangedDataAction1"/>
			</SetValueAction>
			<JournalAction name="JournalAction1" x="30" height="40" logDefineName="logFieldDefine" width="100" procDefine="CommonLog" y="331">
				<transition name="transition159" dest="CleanDataFieldValue0"/>
			</JournalAction>
			<UpdateChangedDataAction name="UpdateChangedDataAction1" x="302" height="53" businessCode="{tableName}_SEND" label="落实指令状态的修改" opType="3" width="143" y="578">
				<transition name="transition158" dest="ExtractContextDataToIcollAction0"/>
			</UpdateChangedDataAction>
			<SetValueAction name="SetValueAction5" x="443" height="52" dataName="orderState" label="修改指令状态为处理中" width="120" dataValue="50" y="172">
				<transition name="transition141" dest="SetTimeAction1"/>
			</SetValueAction>
			<SetTimeAction name="SetTimeAction1" x="454" height="51" label="取发送主机时间" width="100" timeFieldName="orderSendTime" y="267" timeFormat="yyyyMMddHHmmss">
				<transition name="transition94" dest="UpdateChangedDataAction3"/>
			</SetTimeAction>
			<UpdateChangedDataAction name="UpdateChangedDataAction3" x="291" height="57" businessCode="{tableName}_SEND" label="修改状态和发送主机时间" opType="3" width="133" y="263">
				<transition name="transition121" dest="CoreTCPIP1"/>
			</UpdateChangedDataAction>
			<SetValueAction name="SetValueAction6" x="151" height="53" dataName="logFieldDefine" label="设置日志内容" width="100" dataValue="orderFlowNo&amp;authActionType" y="27">
				<transition name="transition154" dest="ExtractIcollDataToContextAction0"/>
			</SetValueAction>
			<CoreTCPIP name="CoreTCPIP1" width="111" sendFormatName="{tranCode}SendFormat" serviceName="CoreTCPIPService" tranCode="{tranCode}" y="349" x="301" receiveFormatName="{tranCode}ReceiveFormat" height="40">
				<transition name="transition122" label="交易成功" dest="SetValueAction3" condition="@IsNull($hostErrorCode)"/>
				<transition name="transition123" label="交易失败" dest="SetValueAction1" condition="exception:TranFailException"/>
				<transition name="transition125" label="异常" dest="SetValueAction2" condition="exception:TCPIPCommunicationException"/>
			</CoreTCPIP>
			<DealSingleFlowAction name="DealSingleFlowAction0" width="100" fieldDealResult="authActionType" y="176" x="145" filedLastModifiedTime="lastModifiedTime" provider="{provider}" fieldRemark="authRejectReason" fieldBusinessCode="businessCode" height="40">
				<transition id="1" name="transition152" dest="QureyDataDetailAction0"/>
				<transition id="0" name="transition156" dest="ExtractContextDataToIcollAction0"/>
			</DealSingleFlowAction>
			<QureyDataDetailAction name="QureyDataDetailAction0" x="336" height="40" businessCode="{tableName}_QUERY" throwException="true" width="100" transactionType="0" y="176">
				<transition name="transition153" dest="SetValueAction5"/>
			</QureyDataDetailAction>
			<ExtractIcollDataToContextAction name="ExtractIcollDataToContextAction0" x="299" height="40" ICollName="iAuthList" width="100" y="34">
				<transition id="0" name="transition155" dest="DealSingleFlowAction0"/>
				<transition id="1" name="transition161" dest="EndAction0"/>
			</ExtractIcollDataToContextAction>
			<CleanDataFieldValue name="CleanDataFieldValue0" x="30" height="40" dataFieldValues="hostErrorCode||hostErrorMsg" width="100" y="159">
				<transition name="transition160" dest="ExtractIcollDataToContextAction0"/>
			</CleanDataFieldValue>
			<ExtractContextDataToIcollAction name="ExtractContextDataToIcollAction0" x="30" height="40" ICollName="iAuthListBak" width="100" y="584">
				<transition name="transition157" dest="JournalAction1"/>
			</ExtractContextDataToIcollAction>
		</flow>
		<input>
			<refColl refId="iAuthList"/>
		</input>
		<output/>
	</operation>
</MCITransaction>