from django.shortcuts import render, get_object_or_404
from rest_framework.decorators import api_view, permission_classes
from rest_framework.response import Response
from rest_framework.permissions import IsAuthenticated
from django.http.response import JsonResponse, HttpResponse
from . import defs

<<<<<<< HEAD
# Create your views here.
    
=======
@api_view(['GET'])
def test(request):
    testData = {1 : "testtesttesttest"}
    return JsonResponse(testData)
    
@api_view(['GET'])
def userInfo(request, summonerName):
    rank_info, recent_matches = defs.sohwan_info(summonerName)
    return JsonResponse({
        'rankInfo' : rank_info,
        'recentMatches' : recent_matches
    })


    
>>>>>>> 28493202f883700905889c754fefe40799c2cbc4
