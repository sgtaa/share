import Config from './config.js';
import Code_conversion from '../utils/code_conversion.js';
import Base64 from '../utils/base64.js'; 
import DateUtil from '../utils/date_util.js'
import RSAsign from '../utils/jsrsasign.js'

function tradeRequest(jydm, dataobj) {
	
	var data = JSON.stringify(dataobj);
	var author = Base64.encode(Config.AUTHOR);
	var account = Config.ACCOUNT; 
	var orgid = Config.ORGID;
	var head = '{"jydm":"' + jydm + '","account":"' + account + '","orgid":"' + orgid + '","platform":"' + Config.XMBH + '","author":"' + author + '"}';
	var date = new Date();//format
	var formatStr = "yyyyMMddhhmmss";
	var datetime = DateUtil.datetoString(date,formatStr);
	var mills = date.getMilliseconds();
	var datetime_stamp = datetime + mills;
	// 报文体统一添加时间戳
	if (data != null && data != undefined && data != "" && data != "{}") {
		
		data = data.substring(0,data.length - 1);
		data = data + ',"datetime_stamp":"' + datetime_stamp + '"}';
		
	} else {
		data = '{"datetime_stamp":"' + datetime_stamp + '"}';
	}
	var dataObj = JSON.parse(data); //由json字符串转换为json对象
	var signature = sign(dataObj);
	var safe = '{"signature":"' + signature + '","signmethod":"' + "RSA" + '"}';
	var safeObj = JSON.parse(safe);
	var headObj = JSON.parse(head);
	// 构造请求参数
	var params = {
		// 报文头
		"head" : headObj,
		// 数据体
		"data" : dataObj,
		// 安全
		"safe" : safeObj
	};
	return params;
}

function sign(data){ 
	var signStr = "";
	var cnum = "";// 循环报文
	var arr = new Array();
	var index = 0;
	
	for ( var key in data) { 
		if (key == "cnum") {
			cnum = data[key] + "";
			// alert(typeof data[key]);
		}
		// 字符有空格的去掉空格
		if (typeof data[key] == "string") {
			data[key] = data[key].trim();
		}
		if (typeof data[key] == "string" && data[key].trim() == "") {// 空字符节点不验签
			 
		} else {
			if (typeof data[key] == "object") {
				arr[index] = key + "=[object]";
				index++;
			} else {
				arr[index] = key + "=" + data[key];
				index++;
			}
		}
	}
	arr.sort();
	for (var flag = 0; flag < arr.length; flag++) {
		signStr += arr[flag] + "&";
	}
	signStr = signStr.substring(0, signStr.length - 1);
	//var rsasign = new $jsrsasign();
	//console.log('tradeRequest//rsasign:'+ rsasign)
	var rsa = RSAsign.KEYUTIL.getRSAKeyFromPlainPKCS8PEM("-----BEGIN PRIVATE KEY-----" + Config.merchant_private_key + "-----END PRIVATE KEY-----");
	var result = rsa.signString(signStr, "md5"); 
	var signKey = Base64.base64_encode(Code_conversion.code_conversion(result));
	signKey = encodeURIComponent(signKey);
	return signKey;
}

export default {
  tradeRequest
}