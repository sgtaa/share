function datetoString(dateTime, formatStr) {
	console.log('DatetoString//datetime:'+ dateTime+'formatStr:'+formatStr)
	//var default_format = "yyyy/MM/dd hh:mm:ss";
	//formatStr = formatStr ? formatStr : default_format;
	var year = dateTime.getFullYear();
	var month = dateTime.getMonth() + 1;
	var date = dateTime.getDate();
	var houres = dateTime.getHours();
	var minutes = dateTime.getMinutes();
	var seconds = dateTime.getSeconds();
	var milliseconds = dateTime.getMilliseconds();
	//console.log('DatetoString//milliseconds:'+ milliseconds)
	milliseconds = full(milliseconds + "", "0", 3, true);
	return formatStr.replace(/yyyy|YYYY/, year).replace(/yy|YY/, (year % 100) > 9 ? (year % 100).toString() : ('0' + (year % 100))).replace(/MM/,
			month > 9 ? month.toString() : '0' + month).replace(/M/g, month).replace(/dd|DD/, date > 9 ? date.toString() : '0' + date).replace(
			/d|D/g, date).replace(/hh|HH/, houres > 9 ? houres.toString() : '0' + houres).replace(/h|H/g, houres).replace(/mm/,
			minutes > 9 ? minutes.toString() : '0' + minutes).replace(/m/g, minutes).replace(/ss|SS/,
			seconds > 9 ? seconds.toString() : '0' + seconds).replace(/s|S/g, seconds).replace(/fff/g, milliseconds);
}


function full(str, ch, count, isLeft) {
		while (str.length < count)
			if (isLeft)
				str = ch + str;
			else
				str = str + ch;
		return str;
}
export default {
  datetoString
}