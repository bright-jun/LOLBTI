import requests
import numpy as np
import pandas as pd
import os

import time

import copy
from multiprocessing import Pool

api_key_list = [
    'RGAPI-48ef7d74-1d87-404b-a573-988faca98f6d',
    'RGAPI-3c4c2908-cf96-42c5-b8c5-8d5713d8fab4',
    'RGAPI-74277b35-7876-4b02-8112-6b8224379b62',
    'RGAPI-8794d187-5541-471c-8242-96ec482e4229',
    'RGAPI-1fa822af-b25c-4d0e-8eb9-bef149d24791',
    'RGAPI-c20efacb-5819-4517-89c0-0069734a11d2',
    'RGAPI-69d8c662-938b-4231-a0b1-7358871a0a8f',
    'RGAPI-64a9f06a-d9eb-4c45-8666-c4f5c132b4a6'
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