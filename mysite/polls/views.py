from django.shortcuts import render
from django.http import HttpResponse

# Create your views here.
# 
def index(request):
	return HttpResponse('Htllo, world, u r at the pools index')
