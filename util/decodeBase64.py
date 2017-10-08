# coding:utf-8

from  urllib import parse
import base64


# url = 'y3MQ3wFiyccV7jZBIEy4mGeDfBT8K6z7ZO%2FQen6E%2B1dTHPsYNmgdbHdfwgiy1XBv%2FiJGSp4DRdln%0AkMfiCZSE1EHFANx1rujDFRFT6etSADptRG5QDrcNJ4x13UUIfdTf3RZU9TsLd4yxIjRWuJJDDR9W%0AEk0FqdGTKa01Fwo2W9F%2F49vAc%2FJoXRrbpEJl%2F149s60P2iJoP%2FyHG6%2F64LNfRt6QZ9BaDMKS1A7%2B%0Ap4z6H7ZJ4RlO'
url = 'y3MQ3wFiyccV7jZBIEy4mGeDfBT8K6z7ZO%2FQen6E%2B1dTHPsYNmgdbHdfwgiy1XBv%2FiJGSp4DRdln%0AkMfiCZSE1EHFANx1rujDFRFT6etSADptRG5QDrcNJ4x13UUIfdTf3RZU9TsLd4yxIjRWuJJDDR9W%0AEk0FqdGTKa01Fwo2W9F%2F49vAc%2FJoXRrbpEJl%2F149s60P2iJoP%2FyHG6%2F64LNfRt6QZ9BaDMKS1A7%2B%0Ap4z6H7ZJ4RlO'

url = parse.unquote(url)

# print(type(url))

# base64.b16decode(url.encode('utf-8'))

print(base64.decodestring(url.encode('utf-8')))



'http://dynamic.12306.cn/mapping/kfxt/zwdcx/LCZWD/cx.jsp?cz=%B1%B1%BE%A9%C4%CF&cc=G12&cxlx=0&rq=2017-10-08&czEn=-E5-8C-97-E4-BA-AC-E5-8D-97&tp=1507435170368'