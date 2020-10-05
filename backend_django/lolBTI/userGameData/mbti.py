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


mbti_mastery = read_pkl("../pkl_file", "mbti_mastery.pkl")
print(type(mbti_mastery))
print(len(mbti_mastery))


def recommend_champ_by_mbti(mbti,ascending):
    global mbti_mastery
    create_mbti_mastery()

    if(setting.updated):
        mbti_mastery = read_pkl("../pkl_file", "mbti_mastery.pkl")
    return mbti_mastery.loc[mbti].sort_values(axis=0,ascending=ascending)[:5]