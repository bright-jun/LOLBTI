from django.shortcuts import render, get_object_or_404
from rest_framework.decorators import api_view, permission_classes
from rest_framework.response import Response
from rest_framework.permissions import IsAuthenticated
from django.http.response import JsonResponse, HttpResponse
from . import defs
from . import recommend
# from . import update

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

@api_view(['GET'])
def recommendByMastery(request, summonerName):
    recomm = recommend.recommend_champ_by_mastery(summonerName, include=False)
    return JsonResponse({
        'champList' : list(recomm.index),
        'pointList' : list(recomm.values)
    })


# @api_view(['PUT'])
# def updateMastery(request, summonerName):
#     update.update_mastery(summonerName)
#     if a.is_valid():