var keyStr = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/=";
function encode(input) {  
	input = unicodetoBytes(input); 
	var output = ""; 
	var chr1, chr2, chr3 = ""; 
	var enc1, enc2, enc3, enc4 = ""; 
	var i = 0;
	 
	do { 
	   chr1 = input[i++]; 
	   chr2 = input[i++]; 
	   chr3 = input[i++];
	 
	   enc1 = chr1 >> 2; 
	   enc2 = ((chr1 & 3) << 4) | (chr2 >> 4); 
	   enc3 = ((chr2 & 15) << 2) | (chr3 >> 6); 
	   enc4 = chr3 & 63;
	 
	   if (isNaN(chr2)) { 
		 enc3 = enc4 = 64; 
	   } else if (isNaN(chr3)) { 
		 enc4 = 64; 
	   }
	 
	   output = output + 
		 keyStr.charAt(enc1) + 
		 keyStr.charAt(enc2) + 
		 keyStr.charAt(enc3) + 
		 keyStr.charAt(enc4); 
	   chr1 = chr2 = chr3 = ""; 
	   enc1 = enc2 = enc3 = enc4 = ""; 
	  } while (i < input.length);
	  return output;   
} 

function decode(input) {  
	var output = ""; 
	  var chr1, chr2, chr3 = ""; 
	  var enc1, enc2, enc3, enc4 = ""; 
	  var i = 0;
	  // remove all characters that are not A-Z, a-z, 0-9, +, /, or = 
	  var base64test = /[^A-Za-z0-9/+///=]/g; 
	  if (base64test.exec(input)) { 
	   alert("There were invalid base64 characters in the input text./n" + 
		  "Valid base64 characters are A-Z, a-z, 0-9, '+', '/', and '='/n" + 
		  "Expect errors in decoding."); 
	  } 
	  input = input.replace(/[^A-Za-z0-9/+///=]/g, ""); 
	  output=new Array(); 
	  do { 
	   enc1 = keyStr.indexOf(input.charAt(i++)); 
	   enc2 = keyStr.indexOf(input.charAt(i++)); 
	   enc3 = keyStr.indexOf(input.charAt(i++)); 
	   enc4 = keyStr.indexOf(input.charAt(i++));
	 
	   chr1 = (enc1 << 2) | (enc2 >> 4); 
	   chr2 = ((enc2 & 15) << 4) | (enc3 >> 2); 
	   chr3 = ((enc3 & 3) << 6) | enc4;
	 
	   output.push(chr1); 
	   if (enc3 != 64) { 
		 output.push(chr2); 
	   } 
	   if (enc4 != 64) { 
		 output.push(chr3); 
	   }
	 
	   chr1 = chr2 = chr3 = ""; 
	   enc1 = enc2 = enc3 = enc4 = "";
	 
	  } while (i < input.length); 
	  return this.bytesToUnicode(output);
}

function unicodetoBytes(s){
	   var result=new Array(); 
	   if(s==null || s=="") return result; 
	   result.push(255); // add "FE" to head 
	   result.push(254); 
	   for(var i=0;i<s.length;i++) 
	   { 
		var c=s.charCodeAt(i).toString(16); 
		if(c.length==1) i="000"+c; 
		else if(c.length==2) c="00"+c; 
		else if(c.length==3) c="0"+c; 
		var var1=parseInt( c.substring(2),16); 
		var var2=parseInt( c.substring(0,2),16); 
		result.push( var1); 
		result.push(var2) ; 
	   } 
	   return result; 
}

function bytesToUnicode(bs){ 
 var result=""; 
 var offset=0; 
 if(bs.length>=2 && bs[0]==255 && bs[1]==254) offset=2; // delete "FE" 
 for(var i=offset;i<bs.length;i+=2){ 
	var code=bs[i]+(bs[i+1]<<8); 
	result+=String.fromCharCode(code); 
 } 
 return result; 
}
/*
 * Javascript base64_decode() base64解密函数
	用于解密base64加密的字符串(发交易使用)
 * @param string str base64加密字符串
 * @return string 解密后的字符串
*/
function base64_encode(str){ 
	var c1, c2, c3;
	var base64EncodeChars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/";                
	var i = 0, len= str.length, string = '';

	while (i < len){
			c1 = str.charCodeAt(i++) & 0xff;
			if (i == len){
					string += base64EncodeChars.charAt(c1 >> 2);
					string += base64EncodeChars.charAt((c1 & 0x3) << 4);
					string += "==";
					break;
			}
			c2 = str.charCodeAt(i++);
			if (i == len){
					string += base64EncodeChars.charAt(c1 >> 2);
					string += base64EncodeChars.charAt(((c1 & 0x3) << 4) | ((c2 & 0xF0) >> 4));
					string += base64EncodeChars.charAt((c2 & 0xF) << 2);
					string += "=";
					break;
			}
			c3 = str.charCodeAt(i++);
			string += base64EncodeChars.charAt(c1 >> 2);
			string += base64EncodeChars.charAt(((c1 & 0x3) << 4) | ((c2 & 0xF0) >> 4));
			string += base64EncodeChars.charAt(((c2 & 0xF) << 2) | ((c3 & 0xC0) >> 6));
			string += base64EncodeChars.charAt(c3 & 0x3F)
	}
	return string
}
/*
 * Javascript base64_decode() base64解密函数
	用于解密base64加密的字符串
 * @param string str base64加密字符串
 * @return string 解密后的字符串
*/
function base64_decode(str){ 
	var c1, c2, c3, c4;
	var base64DecodeChars = new Array(
			-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1,
			-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1,
			-1, -1, -1, -1, -1, -1, -1, 62, -1, -1, -1, 63, 52, 53, 54, 55, 56, 57,
			58, 59, 60, 61, -1, -1, -1, -1, -1, -1, -1, 0,  1,  2,  3,  4,  5,  6,
			7,  8,  9,  10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24,
			25, -1, -1, -1, -1, -1, -1, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36,
			37, 38, 39, 40, 41, 42, 43, 44, 45, 46, 47, 48, 49, 50, 51, -1, -1, -1,
			-1, -1
	);
	var i=0, len = str.length, string = '';

	while (i < len){
			do{
					c1 = base64DecodeChars[str.charCodeAt(i++) & 0xff]
			} while (
					i < len && c1 == -1
			);

			if (c1 == -1) break;

			do{
					c2 = base64DecodeChars[str.charCodeAt(i++) & 0xff]
			} while (
					i < len && c2 == -1
			);

			if (c2 == -1) break;

			string += String.fromCharCode((c1 << 2) | ((c2 & 0x30) >> 4));

			do{
					c3 = str.charCodeAt(i++) & 0xff;
					if (c3 == 61)
							return string;

					c3 = base64DecodeChars[c3]
			} while (
					i < len && c3 == -1
			);

			if (c3 == -1) break;

			string += String.fromCharCode(((c2 & 0XF) << 4) | ((c3 & 0x3C) >> 2));

			do{
					c4 = str.charCodeAt(i++) & 0xff;
					if (c4 == 61) return string;
					c4 = base64DecodeChars[c4]
			} while (
					i < len && c4 == -1
			);

			if (c4 == -1) break;

			string += String.fromCharCode(((c3 & 0x03) << 6) | c4)
	}
	return string;
}

export default {
  base64_decode,
  base64_encode,
  encode
}