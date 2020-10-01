import requests
import numpy as np
import pandas as pd

import time

from tqdm import tqdm
from tqdm import trange

import copy

from multiprocessing import Pool # Pool import하기

import os

DATA_DIR = "./userGameData"
DUMP_FILE = os.path.join(DATA_DIR, "dummy.pkl")

def dump_dataframes(dataframes):
    pd.to_pickle(dataframes, DUMP_FILE)

def load_dataframes():
    print("로드했음")
    return pd.read_pickle(DUMP_FILE)

user_mastery = load_dataframes()

def load_freq_champ(sohwan):
    return user_mastery.loc[sohwan].sort_values(axis=0,ascending=False)[:4]

from numpy import dot
from numpy.linalg import norm

def cosine_sim(A,B):
    return dot(A, B)/(norm(A)*norm(B))

def sim_sohwan(sohwan):
    df = pd.DataFrame()
    
    user_mastery_compressed = user_mastery.loc[:,user_mastery.loc[sohwan,:]>0]
    for i in user_mastery.index:
        df.loc[sohwan,i] = cosine_sim(user_mastery_compressed.loc[sohwan,:],user_mastery_compressed.loc[i,:])
    
    return df
    
def recommend_champ_by_mastery(sohwan, ascending, n=5, k=10,threshold=0, include=True):
    global user_mastery
    
    # 유사도 상위 k개의 유저 리스트
    top_k_sohwan_info = sim_sohwan(sohwan).T[1:].sort_values(by=[sohwan],ascending=False)[:k]
    top_k_sohwan_index = sim_sohwan(sohwan).T[1:].sort_values(by=[sohwan],ascending=False)[:k].index
    
    
    # DWG Canyon에 대해서 추천을 처리해 줄 행을 뽑아옴(Series형태)
    recommend_user_mastery = copy.deepcopy(user_mastery.loc[sohwan,:])
    
    # DWG Canyon플레이어가 한판이라도 플레이 하지 않은 (threshold<=) 챔피언 list
    unplayed = user_mastery.loc[sohwan,:][user_mastery.loc[sohwan,:]<=threshold].index
    
    # 한판이라도 플레이 안 한 챔피언 list의 점수를 업데이트 한다!
    for champ in unplayed:
        temp_val=0;
        temp_sim=0;
        for top_k in top_k_sohwan_index:
            temp_val +=top_k_sohwan_info[sohwan][top_k]*user_mastery.loc[top_k,champ]
            temp_sim +=top_k_sohwan_info[sohwan][top_k]
        recommend_user_mastery[champ]=temp_val/temp_sim
    
    if include:
        return recommend_user_mastery.sort_values(ascending=ascending)[:n]
    else:
        return recommend_user_mastery[unplayed].sort_values(ascending=ascending)[:n]
