#_*_coding:utf_8 _*_
import urllib.request as req
import urllib

url = 'http://account.weibo.com/set/aj5/userinfo/checknickname'

postdata = urllib.parse.urlencode({'nickname': '行者',})  
postdata = postdata.encode('utf-8')  
  
res = req.urlopen(url,postdata) 
res = res.read()
print(res.decode('utf-8'))
# req.urlopen(url)