# _*_coding:utf-8_*_
import re


str = '这是一段测试文字，用来测试的，出现最多的词应该是测试这是一段测试文字，用来测试的，出现最多的词应该是测试'
# str = '这是是的一一一'
# str = 
'''
   


'''

map = {}

commonWords = ['是','的','看','到','一', '这', '用','，', '。', '！','\n','在', '、', '？', '“', '”' ,'：', ' ','　', '了', '．']

def containsCommonWord(str):
	for x in commonWords:
		if str.count(x) > 0:
			return True
	else:
		return False

for x in range(0,len(str)-3):
	s2 = str[x:x+2]
	s3 = str[x: x+3] 
	# print(s2 + " " + s3)

	if containsCommonWord(s2):
		# print(s2 + ' contains common word')
		continue

	if s2 in map:
		map[s2] = map.get(s2) + 1
	else:
		map[s2] = 1


	if containsCommonWord(s3):
		# print(s3 + ' contains common word')
		continue

	if s3 in map:
		map[s3] = map.get(s3) + 1
	else:
		map[s3] = 1

res = {}
for s in map:
	if map[s] > 200:
		res[s] = map.get(s)

res = sorted(res.items(), key=lambda d:d[1], reverse = True)

print(res)
