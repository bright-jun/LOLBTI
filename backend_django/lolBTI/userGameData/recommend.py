import requests
import numpy as np
import pandas as pd

import time

from tqdm import tqdm
from tqdm import trange

import copy

from multiprocessing import Pool # Pool import하기

import os

from . import setting

def read_pkl(path, file):
    global sohwan_mastery
    print("{} read by recommend".format(file))
    return pd.read_pickle(os.path.join(path, file))

def dump_pkl(data, path, file):
    global sohwan_mastery
    print("{} dumped by recommend".format(file))
    return pd.to_pickle(data, os.path.join(path, file))

sohwan_mastery = read_pkl("./userGameData","dummy.pkl")

def load_freq_champ(sohwan):
    global sohwan_mastery

    if(setting.updated):
        sohwan_mastery = read_pkl("./userGameData", "dummy.pkl")

    return sohwan_mastery.loc[sohwan].sort_values(axis=0,ascending=False)[:4]

from numpy import dot
from numpy.linalg import norm

def cosine_sim(A,B):
    return dot(A, B)/(norm(A)*norm(B))

def sim_sohwan(sohwan):
    df = pd.DataFrame()
    
    sohwan_mastery_compressed = sohwan_mastery.loc[:,sohwan_mastery.loc[sohwan,:]>0]
    for i in sohwan_mastery.index:
        df.loc[sohwan,i] = cosine_sim(sohwan_mastery_compressed.loc[sohwan,:],sohwan_mastery_compressed.loc[i,:])
    
    return df
    
def recommend_champ_by_mastery(sohwan, ascending, n=5, k=10,threshold=0, include=True):
    global sohwan_mastery

    if(setting.updated):
        sohwan_mastery = read_pkl("./userGameData", "dummy.pkl")

    # 유사도 상위 k개의 유저 리스트
    top_k_sohwan_info = sim_sohwan(sohwan).T[1:].sort_values(by=[sohwan],ascending=False)[:k]
    top_k_sohwan_index = sim_sohwan(sohwan).T[1:].sort_values(by=[sohwan],ascending=False)[:k].index
    
    
    # DWG Canyon에 대해서 추천을 처리해 줄 행을 뽑아옴(Series형태)
    recommend_sohwan_mastery = copy.deepcopy(sohwan_mastery.loc[sohwan,:])
    
    # DWG Canyon플레이어가 한판이라도 플레이 하지 않은 (threshold<=) 챔피언 list
    unplayed = sohwan_mastery.loc[sohwan,:][sohwan_mastery.loc[sohwan,:]<=threshold].index
    
    # 한판이라도 플레이 안 한 챔피언 list의 점수를 업데이트 한다!
    for champ in unplayed:
        temp_val=0;
        temp_sim=0;
        for top_k in top_k_sohwan_index:
            temp_val +=top_k_sohwan_info[sohwan][top_k]*sohwan_mastery.loc[top_k,champ]
            temp_sim +=top_k_sohwan_info[sohwan][top_k]
        recommend_sohwan_mastery[champ]=temp_val/temp_sim
    
    if include:
        return recommend_sohwan_mastery.sort_values(ascending=ascending)[:n]
    else:
        return recommend_sohwan_mastery[unplayed].sort_values(ascending=ascending)[:n]
