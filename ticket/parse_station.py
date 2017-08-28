import re
import requests
from pprint import pprint 

# url = 'https://kyfw.12306.cn/otn/resources/js/framework/station_name.js?station_version=1.8971'
url = 'http://dynamic.12306.cn/mapping/kfxt/zwdcx/LCZWD/from2.jsp?cz=%BA%BA%BF%DA&cc=T180&cxlx=0&rq=2017-08-28&czEn=-E6-B1-89-E5-8F-A3&tp=1503931254778'
response = requests.get(url, verify=False)
response.
stations = re.findall(u'([\u4e00-\u9fa5]+)\|([A-Z]+)', response.text)
pprint(dict(stations), indent=4)
