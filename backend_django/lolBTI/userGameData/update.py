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

# def update_mastery(sohwan):
    # key 고려해서 받아와야 함.