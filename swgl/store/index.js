import Vue from 'vue'
import Vuex from 'vuex'

import Config from '../service/config/config.js';
import Common from '../service/config/common.js';

Vue.use(Vuex)

const store = new Vuex.Store({
	state: {
		baseUrl: Config.SERVER_IP + ':' + Config.SERVER_PORT + Config.TRADE_ACTION,
		WSUrl: Config.WSSERVER_IP + ':' + Config.SERVER_PORT + Config.WEBSOCKE_ACTION,
		//是否需要强制登录
		forcedLogin: false,
		hasLogin: false,
		userName: "",
		count:1,
		//websocket连接
		connected: false,
		connecting: false,
		msg: false,
		// push 添加商品弹窗状态
		contrary:false,
		numders:0,
		accountType:'',
		phoneNo:'',
		score:'',
		token:''
	},
	// 类似 vue 的 computed (计算属性)
	//getters 的返回值会根据它的依赖被缓存起来，且只有当它的依赖值发生了改变才会被重新计算，这里我们可以通过定义vuex的Getters来获取，Getters 可以用于监听、state中的值的变化，返回计算后的结果
	getters:{
		getStateCount(state){ return state.count + 1 }
	},
	mutations: {
		//测试用方法
		add(state){ state.count = state.count + 1 },
		reduction(state){ state.count = state.count - 1 },
		// push 添加商品弹窗状态
		push(state){ state.contrary = !state.contrary },
		// 详情页 购物车显示数量
		addnum(state){ state.numders = state.numders + 1 },
		
		login(state, userName) {
			state.userName = userName || '新用户';
			state.hasLogin = true;
		},
		logout(state) {
			state.userName = "";
			state.hasLogin = false;
		},
		//授权登录
		authorizeLogin(state, params){
			var resultObj = Common.tradeRequest(params.data.jydm,params.data.body);
			console.log('publicSave==JSON.stringify(resultObj):'+ JSON.stringify(resultObj))
			uni.showLoading({
				title: '加载中',
				mask:true
			});
			uni.request({
				method:'POST',
				url: state.baseUrl,
				async : false,
				cache : false,
				data: "params={imp:" + JSON.stringify(resultObj) + "}",
				header: {
					'content-type':'application/x-www-form-urlencoded'
				},
				success: (res) => {
					console.log(res)
					uni.hideLoading()
					if(res.data.imp.head[0].respcode =='00000'){
						uni.showToast({
							title: res.data.head.msgMsg,
							duration: 3000,
							icon:'none'
						});
						uni.setStorage({
						key:'shtorgObj',
						data:JSON.stringify( res.data.body)
						})
						
						params.success(res.data)
						state.token = res.data.body.token
						
						uni.switchTab({
							url: '../../pages/main/main'
						});
					}if(res.data.imp.head[0].respcode =='登录失败码'){
						
						uni.showToast({
							//title: '用户账号或密码不正确',
							title: res.data.imp.head[0].respmsg,
							duration: 3000,
							icon:'none'
						});
					}else if(res.data.imp.head[0].respcode =='44444'){
						uni.showToast({
							title: res.data.imp.head[0].respmsg,
							duration: 3000,
							icon:'none'
						});
					}else if(res.data.imp.head[0].respcode =='ERROR'){
						uni.showToast({
							title: res.data.imp.head[0].respmsg,
							duration: 3000,
							icon:'none'
						});
					}else {
						uni.showToast({
							title: '服务器异常',
							duration: 3000,
							icon:'none'
						});
					}
				},
				fail:(res) => {
					uni.hideLoading();
					uni.showToast({
						title: '请求失败，请稍后再试',
						duration: 3000,
						icon:'none'
					});
				}
			});
		},
		publicRequest(state, params){
			var resultObj = Common.tradeRequest(params.data.jydm,params.data.body);
			console.log('publicSave==JSON.stringify(resultObj):'+ JSON.stringify(resultObj))
			uni.showLoading({
				title: '加载中',
				mask:true
			}); 
			uni.request({
				method:'POST',
				url: state.baseUrl,
				async : false,
				cache : false,
				data: "params={imp:" + JSON.stringify(resultObj) + "}",
				header: {
					'content-type':'application/x-www-form-urlencoded'
				},
				success: (res) => {
					console.log(res)
					uni.hideLoading()
					if(res.data.imp.head[0].respcode =='00000'){
						params.success(res.data)
					}else if(res.data.imp.head[0].respcode =='44444'){
						uni.showToast({
							title: res.data.imp.head[0].respmsg,
							duration: 3000,
							icon:'none'
						});
					}else if(res.data.imp.head[0].respcode =='ERROR'){
						uni.showToast({
							title: res.data.imp.head[0].respmsg,
							duration: 3000,
							icon:'none'
						}); 
					}else {
						uni.showToast({
							title: '服务器异常',
							duration: 3000,
							icon:'none'
						});
					}	
				},
				fail:(res) => {
					uni.hideLoading();
					uni.showToast({
						title: '请求失败，请稍后再试',
						duration: 3000,
						icon:'none'
					});
				}
			});
		},
		WSConnect(state,params) {
			if (state.connected || state.connecting) {
				uni.showModal({
					content: '正在连接或者已经连接,请勿重复连接!',
					showCancel: false
				})
				return false
			}
			state.connecting = true 
			uni.showLoading({
				title: '连接中...'
			})
			uni.connectSocket({ 
				url: state.WSUrl + params.data.userid +'?'+ params.data.username, 
				async : false,
				cache : false, 
				method:'POST',
				header: {
					'content-type':'application/x-www-form-urlencoded'
				},
				success(res) {
					// 这里是接口调用成功的回调，不是连接成功的回调，请注意
				},
				fail(err) {
					// 这里是接口调用失败的回调，不是连接失败的回调，请注意
				}
			})
			uni.onSocketOpen((res) => {
				state.connecting = false
				state.connected = true 
				uni.hideLoading()
				uni.showToast({
					icon: 'none',
					title: '连接成功'
				})
				console.log('onOpen', res);
			})
			uni.onSocketError((err) => {
				state.connecting = false
				state.connected = false
				uni.hideLoading()
				uni.showModal({
					content: '连接失败，可能是websocket服务不可用，请稍后再试',
					showCancel: false
				})
				console.log('onError', err);
			})
			uni.onSocketMessage((res) => { 
				console.log('onMessage', res)
				params.success(res,state.connected)
			})
			uni.onSocketClose((res) => {
				state.connected = false 
				console.log('onClose', res)
			})
		},
		WSSend(state, params){
			var resultObj = Common.tradeRequest(params.data.jydm,params.data.body); 
			uni.sendSocketMessage({ 
				data: "{imp:" + JSON.stringify(resultObj) + "}",
				success(res) { 
					console.log(res); 
				},
				fail(err) {
					console.log(err);
				}
			})
		},
		WSClose() {  
			uni.closeSocket({ 
				
				success(res) {
					console.log(res);
				},
				fail(err) {
					console.log(err);
				}
			})
		}, 
	},
	// 虽然从 mutations 中可以达到修改 state 中状态值的目的,
	//但官方不建议直接使用 mutations 修改 state 中的状态值,
	//建议使用 actions 提交 mutations 去修改状态值
	actions:{
		//测试用方法 
		addfun(ele){ ele.commit("add") },
		reductionfun(ele){ ele.commit("reduction") },
		// push 添加商品弹窗状态
		pushfun(ele){ ele.commit("push") },
		// 详情页 购物车显示数量
		addnumfun(ele){ ele.commit("addnum") },
	}
})

export default store
