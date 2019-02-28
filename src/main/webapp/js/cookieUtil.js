/**
 * 
 * @param cookieName
 * 쿠키 이름으로 해당 쿠키 값을 조회.
 */
function getCookieValue(cookieName){
/*   
   String result = null;
   for(int i = 0; i < cookie.length; i++){
      if(string.equals(cookie_arr[i][0])){
         result = cookie_arr[i][1];
         break;
      }
   }
   // cookie_arr[0][0], [0][1] -> "userId", "brown" 
   
   return result;
 */
   
   var cookieValue = "";
   var cookieArray = document.cookie.split("; ");

   for(var i = 0; i < cookieArray.length; i++){
      var cookie = cookieArray[i]; // "userId=brown"
      if(cookieName == cookie.split("=")[0]){
         cookieValue = cookie.split("=")[1];   // cookieValue
         break;
      }
   }
   
   return cookieValue;
}

/**
 * 
 * @param cookieName
 * @param cookieValue
 * @param expires
 * 쿠키 생성
 */
function setCookie(cookieName, cookieValue, expires){
   // 현재 날짜 기준으로 expires 날짜만큼 유효한 cookie 생성.
   // 쿠키 생성 방법 : document.cookie = "cookie 문자열 포맷";
   // cookie 문자열 포맷 : cookieName=cookieValue; path=/; expires=gmtString
   var today = new Date();
   today.setDate(today.getDate() + parseInt(expires));
   
   document.cookie = cookieName + "=" + cookieValue + "; path=/" +
         "; expires=" + today.toGMTString();
   
}

/**
 * 
 * @param cookieName
 * 쿠키 삭제.
 */
function deleteCookie(cookieName){
   var dt = new Date();         // 오늘 날짜
   dt.setDate(dt.getDate() - 1);   // 하루 전 날짜
   
   document.cookie = cookieName + "=; path=/; expires=" + dt.toGMTString();
   
}










