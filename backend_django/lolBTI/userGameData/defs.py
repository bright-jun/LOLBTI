import requests
import numpy as np
import pandas as pd
import os

import time

import copy
from multiprocessing import Pool

api_key_list = [
    'RGAPI-1f9d5855-7f31-4407-af51-d8bd19913718',
    'RGAPI-d12adbf4-e5c4-4772-a6b4-f39d79e58611',
    'RGAPI-8e7d7ffc-fbff-49e4-9ca0-0546348a46f8',
    'RGAPI-849ccf28-3409-4df1-93bd-7f70df4fc464',
    'RGAPI-6706a4f2-ca89-420a-b3af-aec64ffc4a19',
    'RGAPI-262abd8e-e69a-4c97-9c21-bd8bbb4fdf7d',
    'RGAPI-754f597b-6618-450f-85c6-318eb0846c2a',
    'RGAPI-8002ed33-6f19-44d8-bed0-61671a6efcfa'
]
def rank_info(sohwan_r, key_idx):
    # 소환사 랭크 정보 수집
    api_key = getApiKey(key_idx)
    url = 'https://kr.api.riotgames.com/lol/league/v4/entries/by-summoner/' + sohwan_r.json()['id'] +'?api_key=' + api_key
    return requests.get(url)

def recent_match(sohwan_r, key_idx, n):
    # 소환사 최근 n개 매치 정보 수집
    api_key = getApiKey(key_idx)
    match_url = 'https://kr.api.riotgames.com/lol/match/v4/matchlists/by-account/' + sohwan_r.json()['accountId'] +'?api_key=' + api_key +'&endIndex=' + str(n)
    return requests.get(match_url)

def sohwan_info(sohwan, n=5):
    # api_key setting
    key_idx = 0
    # id(=summonerid),accountid 수집
    url = "https://kr.api.riotgames.com/lol/summoner/v4/summoners/by-name/" + sohwan
    r , key_idx = getRequests(url,key_idx)
    while True:
      if r.status_code == 200:
        r_info = rank_info(r, key_idx)
        if r_info.status_code == 200:
          break
        elif r_info.status_code == 403: # api갱신이 필요
          print('you need renewal key no.{}'.format(key_idx))
          return '키갱신필요'
        else:
          key_idx = renewKeyIdx(key_idx)
          r , key_idx = getRequests(url,key_idx)
      elif r.status_code == 404:
        return '소환사가 존재하지 않습니다.'
    while True:
      if r.status_code == 200:
        r_match = recent_match(r, key_idx, n)
        if r_match.status_code == 200:
          break
        elif r_match.status_code == 403: # api갱신이 필요
          print('you need renewal key no.{}'.format(key_idx))
          return '키갱신필요'
        else:
          key_idx = renewKeyIdx(key_idx)
          r , key_idx = getRequests(url,key_idx)
      elif r.status_code == 404:
        return '소환사가 존재하지 않습니다.'
  
    return r_info.json(), r_match.json()

def sohwan_info_lane(sohwan, n=100):
    # api_key setting
    key_idx = 0
    # id(=summonerid),accountid 수집
    url = "https://kr.api.riotgames.com/lol/summoner/v4/summoners/by-name/" + sohwan
    r , key_idx = getRequests(url,key_idx)
    while True:
      if r.status_code == 200:
        r_match = recent_match(r, key_idx, n)
        if r_match.status_code == 200:
          break
        elif r_match.status_code == 403: # api갱신이 필요
          print('you need renewal key no.{}'.format(key_idx))
          return '키갱신필요'
        else:
          key_idx = renewKeyIdx(key_idx)
          r , key_idx = getRequests(url,key_idx)
      elif r.status_code == 404:
        return '소환사가 존재하지 않습니다.'
  
    return r_match.json()

def freq_lane_info(sohwan):
    temp = sohwan_info_lane(sohwan)
    temp = temp['matches']
    temp = pd.DataFrame(temp)
    temp = temp.groupby('lane').count()['role']
    return temp

def getRequests(root,key_idx):
  api_key = getApiKey(key_idx)
  url = root + '?api_key=' + api_key
  r = requests.get(url)
  while True:
    if r.status_code == 200 or r.status_code == 404:
      return r, key_idx
    elif r.status_code == 403: # api갱신이 필요
      print('you need renewal key no.{}'.format(key_idx))
      return r, key_idx
    else:
      # 키 값 갱신         
      key_idx = renewKeyIdx(key_idx)
      api_key = getApiKey(key_idx)
      url = root + '?api_key=' + api_key
      r = requests.get(url)

def getApiKey(key_idx): # api 가져오기
  global api_key_list
  return api_key_list[key_idx]

def renewKeyIdx(key_idx): # key_idx갱신
  global api_key_list
  print('key_idx갱신')
  return (key_idx+1)%len(api_key_list)