from django.shortcuts import render, get_object_or_404
from rest_framework.decorators import api_view, permission_classes
from rest_framework.response import Response
from rest_framework.permissions import IsAuthenticated
from django.http.response import JsonResponse, HttpResponse
from . import defs
from . import recommend
from . import update
from . import recommendItem as rI
from . import mbti as mb

@api_view(['GET'])
def test(request):
    testData = {1 : "testtesttesttest"}
    return JsonResponse(testData)
    
@api_view(['GET'])
def userInfo(request, summonerName):
    summonerName = summonerName.replace(" ","").lower()
    try:
        rank_info, recent_matches = defs.sohwan_info(summonerName)
        return JsonResponse({
            'rankInfo' : rank_info,
            'recentMatches' : recent_matches
        })
    except:
        return JsonResponse({
            # 'rankInfo' : [],
            # 'recentMatches' : {}
        })

@api_view(['GET'])
def recommendByMastery(request, summonerName):
    summonerName = summonerName.replace(" ","").lower()
    try:
        best = recommend.recommend_champ_by_mastery(summonerName, ascending=False, include=False)
        worst = recommend.recommend_champ_by_mastery(summonerName, ascending=True, include=False)
        return JsonResponse({
            'bestChampList' : list(best.index),
            'bestPointList' : list(best.values),
            'worstChampList' : list(worst.index),
            'worstPointList' : list(worst.values),
        })
    except:
        return JsonResponse({
        })
@api_view(['GET'])
def showFreqChamp(request, summonerName):
    summonerName = summonerName.replace(" ","").lower()
    try:
        freq_champ_list = recommend.load_freq_champ(summonerName)
        return JsonResponse({
            'freqChampAvartar' : list(freq_champ_list.index),
            'freqChampScore' : list(freq_champ_list.values),
        })
    except:
        return JsonResponse({
        })
@api_view(['GET'])
def showFreqLane(request, summonerName):
    summonerName = summonerName.replace(" ","").lower()
    try:
        freq_lane = defs.freq_lane_info(summonerName)
        return JsonResponse({
            'lane': list(freq_lane.index),
            'laneFreq' : list(freq_lane),
        })
    except:
        return JsonResponse({
        })
@api_view(['GET'])
def updateMastery(request, summonerName):
    summonerName = summonerName.replace(" ","").lower()
    try:
        result = update.update_sohwan_mastery(summonerName)
        return JsonResponse({
            'result' : result
        })
    except:
        return JsonResponse({
        })

@api_view(['GET'])
def recommendItem(request, myChamp, opponentChamp):
    try:
        key,value = rI.getItems(int(myChamp), int(opponentChamp))
        return JsonResponse({
            'key' : key,
            'value' : value
        })
    except:
        return JsonResponse({
        })
@api_view(['GET'])
def recommendByMbti(request, mbti):
    try:
        best = mb.recommend_champ_by_mbti(mbti, ascending=False)
        worst = mb.recommend_champ_by_mbti(mbti, ascending=True)
        return JsonResponse({
            'bestChampList' : list(best.index),
            'bestPointList' : list(best.values),
            'worstChampList' : list(worst.index),
            'worstPointList' : list(worst.values),
        })
    except:
        return JsonResponse({
        })
@api_view(['GET'])
def recommendBySohwan(request, sohwan):
    try:     
        best = mbti.recommend_champ_by_mbti(mbti, ascending=False)
        worst = mbti.recommend_champ_by_mbti(mbti, ascending=True)
        return JsonResponse({
            'bestChampList' : list(best.index),
            'bestPointList' : list(best.values),
            'worstChampList' : list(worst.index),
            'worstPointList' : list(worst.values),
        })
    except:
        return JsonResponse({
        })