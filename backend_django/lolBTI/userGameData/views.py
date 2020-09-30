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
    best = recommend.recommend_champ_by_mastery(summonerName, ascending=False, include=False)
    worst = recommend.recommend_champ_by_mastery(summonerName, ascending=True, include=False)
    return JsonResponse({
        'bestChampList' : list(best.index),
        'bestPointList' : list(best.values),
        'worstChampList' : list(worst.index),
        'worstPointList' : list(worst.values),
    })

@api_view(['GET'])
def showFreqChamp(request, summonerName):
    freq_champ_list = recommend.load_freq_champ(summonerName)
    return JsonResponse({
        'freqChampAvartar' : list(freq_champ_list.index),
        'freqChampScore' : list(freq_champ_list.values),
    })

@api_view(['GET'])
def showFreqLane(request, summonerName):
    freq_lane = defs.freq_lane_info(summonerName)
    return JsonResponse({
        'lane': list(freq_lane.index),
        'laneFreq' : list(freq_lane),
    })

# @api_view(['PUT'])
# def updateMastery(request, summonerName):
#     update.update_mastery(summonerName)
#     if a.is_valid():
