<template>
	<view>
		<page-head title="websocket通讯示例"></page-head>
		<view class="uni-padding-wrap">
			<view class="uni-btn-v">
				<view class="websocket-msg">{{showMsg}}</view>
				<button type="primary" @click="connect">连接websocket服务</button>
				<button v-show="connected" type="primary" @click="send">发送一条消息</button>
				<button type="primary" @click="close">断开websocket服务</button>
				<view class="websocket-tips">发送消息后会收到一条服务器返回的消息（与发送的消息内容一致）</view>
			</view>
		</view>
	</view>
</template>

<script>
	import Common from '../../service/config/common.js';
	import Config from '../../service/config/config.js';
	import {
		mapState,
		mapMutations
	} from 'vuex'
	let platform = uni.getSystemInfoSync().platform
	export default {
		data() {
			return {  
				connected: false,
				connecting: false,
				msg: false,
				roomId: ''
			}
		},
		computed: { 
			showMsg() {
				if (this.connected) {
					if (this.msg) {
						return '收到消息：' + this.msg
					} else {
						return '等待接收消息'
					}
				} else {
					return '尚未连接'
				}
			}
		},
		onUnload() {
			this.close()
            uni.hideLoading()
		}, 
		methods: {
			...mapMutations(['login','publicRequest','authorizeLogin','WSConnect','WSSend','WSClose']),
			connect() { 
				let that = this;
				const obj = {
					data: {
						userid:'520', 
						username:'web520',
					},
					success(res,connectStatus) { 
						that.msg = res.data
						that.connected = connectStatus; 
					}
				};
				this.WSConnect(obj); 
			},
			send() {
				var msgcode = this.GenNonDuplicateID(3); 
				let that = this; 
				const obj = {
					data: {
						jydm:'SSGL10001',
						body: {
							operflag:6,
							inputname:'520',
							//orgid:'SO1566490861729585',
							//username:"春燕",
							/* flag:'admin',//admin
							from_userid:'520',
							from_username:'web520',
							msgcode:'kbrofzvmyaf', */
							//from_userid:'520',
							//from_username:'web520',
							/* to_userid:'520',
							msgcode:'kbrqdgfhl0m', */
							//to_section:'事业部',
							//type:'1',
							//title:'111任务来了',
							//from_userid:'520',
							//from_time:'2020-05-28 11:22:13',
							//from_section:'事业部',
							//title:'111任务来了',
							//status:'0',
							/* commentid:'1592900652739112', */
							touser:[
								//{userid:'123', username:'sgt123'},
								{userid:'456', username:'web456人事部',to_section:'人事部'},
								{userid:'520', username:'web520事业部',to_section:'事业部'},
								{userid:'111', username:'web111人事部',to_section:'111人事部'},
								{userid:'222', username:'web222事业部',to_section:'222事业部'}
							],
							/* message:{
								 notice:'notice',
								msgcode:kbrmo4ypmyr,
								sbucompid:"企业编号",  
								message:'消息内容', 
								type:'通知',//通知或评阅 
								userid:'520',
								from_userid:'520',
								from_username:'web520', 
								commentid:'12434',
								msg_type:'0',
								status:'0',
								audit_status:'1', 
								subcompid:'企业id',
								title:'111任务来了',
								content:'111很好！我是web520事业部',//1592900652739112 
								notice:'notice',//固定值
								msgcode:msgcode,
								message:'消息内容',  
								type:'通知',//通知或评阅 
								msg_type:'0',//0 评阅 或 1审核
								from_userid:'520',
								from_username:'web520', 
								subcompid:"企业ID", 
								userid:'520', 
								commentid:'1592900652739112', 
							}, */
							/* message:{
								msgcode:msgcode,
								sbucompid:"企业编号",
								title:'111任务来了',
								content:'111很好！我是web520事业部',
								type:'0', 
								from_userid:'520',
								from_username:'web520',
								from_section:'事业部'
							},
							accessory:[
								//{userid:'123', username:'sgt123'},
								{ resp_name:'工作总结计划下周计划.xlsx', path:'wgkms___20190808_1632573507'},
								{ resp_name:'GKXX_GFT.xls', path:'gkumis___20200521_1416393030'},
								{ resp_name:'工作总结计划.xlsx', path:'wgkms___20190808_1632573507'},
								{ resp_name:'GKXX_GFT_编码_配置审计表.xls', path:'gkumis___20200521_1416393030'}
							], */
						}
					},
					success(res) {  
					}
				};
				this.WSSend(obj);
				//this.WSSend(obj);  
			},
			close() { 
				this.connected = false 
				this.WSClose(); 
			},
			/**
			 * 生成一个用不重复的ID
			 */
			GenNonDuplicateID(randomLength){
			 let idStr = Date.now().toString(36)
			 idStr += Math.random().toString(36).substr(3,randomLength)
			 return idStr;
			}
		}
	}
</script>

<style>
	.uni-padding-wrap {
		width: 690rpx;
		padding: 0 30rpx;
	}

	.uni-btn-v {
		padding: 10rpx 0;
	}

	.uni-btn-v button {
		margin: 20rpx 0;
	}

	.websocket-room {
		height: 40px;
		line-height: 40px;
		text-align: center;
		border-bottom: solid 1px #DDDDDD;
		margin-bottom: 20px;
	}

	.websocket-msg {
		padding: 40px 0px;
		text-align: center;
		font-size: 14px;
		line-height: 40px;
		color: #666666;
	}

    .websocket-tips{
        padding: 40px 0px;
        text-align: center;
        font-size: 14px;
        line-height: 24px;
        color: #666666;
    }
</style>
