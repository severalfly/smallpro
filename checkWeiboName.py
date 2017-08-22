#_*_coding:utf_8 _*_
import urllib.request as req
import urllib
import http.cookiejar as cookielib

'''
url = 'http://account.weibo.com/set/aj5/userinfo/checknickname'

postdata = urllib.parse.urlencode({'nickname': '行者',})  
postdata = postdata.encode('utf-8')  
  
res = req.urlopen(url,postdata) 
res = res.read()
print(res.decode('utf-8')) '''
# req.urlopen(url)



baiduSpaceEntryUrl = "http://account.weibo.com/set/index?topnav=1&wvr=6";
cj = cookielib.CookieJar();
opener = req.build_opener(req.HTTPCookieProcessor(cj));
req.install_opener(opener);
resp = req.urlopen(baiduSpaceEntryUrl);

# print(resp.read().decode('gb2312'))
 
# second time do url request, the cookiejar will auto handle the cookie
loginBaiduUrl = "http://account.weibo.com/set/aj5/userinfo/checknickname";
para = {
    'nickname'  : '行者',
    };
# para="username=323&password=233"
postData = urllib.parse.urlencode(para);
reqs = req.Request(loginBaiduUrl, postData.encode('utf-8')); # req.Request: the HTTP request will be a POST instead of a GET when the data parameter is provided.
reqs.add_header('User-Agent', 'Mozilla/5.0 (Windows NT 6.1) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/60.0.3112.101 Safari/537.36');
reqs.add_header('Content-Type', 'application/x-www-form-urlencoded');
reqs.add_header('Cache-Control', 'no-cache');
reqs.add_header('Accept', '*/*');
reqs.add_header('Connection', 'Keep-Alive');
reqs.add_header('Referer', baiduSpaceEntryUrl)
reqs.add_header('X-Requested-With', "XMLHttpRequest")
resp = req.urlopen(reqs);
reqs.
# print(resp.read())
print(resp.read().decode('gb2312'))
# respInfo = resp.info();
# 


# resp = req.urlopen(loginBaiduUrl);

# print(resp.read().decode('gb2312'))