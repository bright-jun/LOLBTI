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
    print("{} read by mbti".format(file))
    return pd.read_pickle(os.path.join(path, file))

def dump_pkl(data, path, file):
    print("{} dumped by mbti".format(file))
    pd.to_pickle(data, os.path.join(path, file))

sohwan_mastery = read_pkl("../pkl_file", "dummy.pkl")

mbti = pd.read_excel(os.path.join("../pkl_file", "lolBTI설문_전처리.xlsx"), # write your directory here
                            sheet_name = 'Sheet1', 
                            header = 0)
mbti.set_index(mbti['name'], inplace=True)
mbti = pd.DataFrame(mbti['mbti'])
mbti_mastery = pd.merge(sohwan_mastery, mbti, left_index=True, right_index=True,how='right')
mbti_mastery = mbti_mastery.groupby(['mbti']).mean()
dump_pkl(mbti_mastery,"../pkl_file", "mbti_mastery.pkl")

mbti_mastery = read_pkl("../pkl_file", "mbti_mastery.pkl")
print(type(mbti_mastery))
print(len(mbti_mastery))


def recommend_champ_by_mbti(mbti,ascending):
    global mbti_mastery

    if(setting.updated):
        mbti_mastery = read_pkl("../pkl_file", "mbti_mastery.pkl")
    return mbti_mastery.loc[mbti].sort_values(axis=0,ascending=ascending)[:5]