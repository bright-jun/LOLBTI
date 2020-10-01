import requests
import numpy as np
import pandas as pd

import time

from tqdm import tqdm
from tqdm import trange

from sklearn import preprocessing

import copy

from multiprocessing import Pool # Pool import하기

api_key_list = [
    'RGAPI-d3a53c22-f940-40b8-9e4c-089ad160d8f2',
    'RGAPI-2866c236-5976-4cac-8955-49fccb487e8b',
    'RGAPI-1389cd81-c02e-4fa1-9654-80579633a8ff',
    'RGAPI-24091344-7123-4eac-8ebc-9f9c6e75311a',
    'RGAPI-754d8513-daaf-4192-831f-a6953405e338'
]

min_max_scaler = preprocessing.MinMaxScaler()

import os

def dump_dataframes(dataframes, path):
    pd.to_pickle(dataframes, path)

def load_dataframes(path):
    print("update 에서로드했음")
    return pd.read_pickle(path)

champ_dict = load_dataframes(os.path.join("./userGameData","champ_dict.pkl"))
sohwan_mastery = load_dataframes(os.path.join("./userGameData","dummy.pkl"))

def mastery_info(sohwan_r, key_idx):
    # 소환사 챔피언 숙련도 정보 수집
    api_key = getApiKey(key_idx)
    url = 'https://kr.api.riotgames.com/lol/champion-mastery/v4/champion-masteries/by-summoner/' + sohwan_r.json()['id'] +'?api_key=' + api_key
    return requests.get(url)

def sohwan_mastery_info(sohwan):
    # api_key setting
    key_idx = 0
    # id(=summonerid),accountid 수집
    url = "https://kr.api.riotgames.com/lol/summoner/v4/summoners/by-name/" + sohwan
    r , key_idx = getRequests(url,key_idx)
    while True:
      if r.status_code == 200:
        m_info = mastery_info(r, key_idx)
        if m_info.status_code == 200:
          break
        elif m_info.status_code == 403: # api갱신이 필요
          print('you need renewal key no.{}'.format(key_idx))
          return '키갱신필요'
        else:
          key_idx = renewKeyIdx(key_idx)
          r , key_idx = getRequests(url,key_idx)
      elif r.status_code == 404:
        return '소환사가 존재하지 않습니다.'
    
    return m_info.json()
  
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

def update_sohwan_mastery(sohwan):
    global min_max_scaler
    global sohwan_mastery
    global champ_dict

    champ_mastery = sohwan_mastery_info(sohwan)
    if type(champ_mastery)==list:
        # user_mastery행렬 업데이트
        t_champ_dict = copy.deepcopy(champ_dict)

        for champ_info in champ_mastery:
            t_champ_dict[str(champ_info['championId'])].append(champ_info['championPoints'])

        # 빈 값 0 으로
        for champ in t_champ_dict.keys():
            if(len(t_champ_dict[champ])==0):
                t_champ_dict[champ].append(0)

        #   정규화
        t_champ_df = pd.DataFrame(data=t_champ_dict,index=[sohwan])
        t_champ_df_scaled = min_max_scaler.fit_transform(t_champ_df.T)
        t_champ_df_scaled = pd.DataFrame(data = t_champ_df_scaled.T,index=[sohwan],columns=t_champ_dict.keys())

        #   concat이 아니라 중복되면 갱신해야함.
        sohwan_mastery.loc[sohwan] = t_champ_df_scaled.loc[sohwan]
        dump_dataframes(sohwan_mastery,os.path.join("./userGameData","dummy.pkl"))
        return True
    else:
        print(r)
        return False