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

# deeplearning

from keras.models import Sequential
from keras.layers.core import Dense
from keras.utils import np_utils
from sklearn.preprocessing import LabelEncoder

import tensorflow.keras as keras
import pandas as pd
import seaborn as sns
import matplotlib.pyplot as plt
import numpy as np
import tensorflow as tf

def read_pkl(path, file):
    print("{} read by mbti".format(file))
    return pd.read_pickle(os.path.join(path, file))

def dump_pkl(data, path, file):
    print("{} dumped by mbti".format(file))
    pd.to_pickle(data, os.path.join(path, file))

def update_mbti_mastery():
    sohwan_mastery = read_pkl("../pkl_file", "dummy.pkl")
    # db랑 연동해서 mbti 만들어줘야함.
    mbti = pd.read_excel(os.path.join("../pkl_file", "lolBTI설문_전처리.xlsx"), # write your directory here
                                sheet_name = 'Sheet1', 
                                header = 0)
    mbti.set_index(mbti['name'], inplace=True)
    mbti = pd.DataFrame(mbti['mbti'])
    mbti_mastery = pd.merge(sohwan_mastery, mbti, left_index=True, right_index=True,how='right')
    # 결측치 제거
    mbti_mastery = mbti_mastery.dropna()
    dump_pkl(mbti_mastery,"../pkl_file", "mbti_merged.pkl")
    mbti_mastery = mbti_mastery.groupby(['mbti']).mean()
    dump_pkl(mbti_mastery,"../pkl_file", "mbti_mastery.pkl")

update_mbti_mastery()

sohwan_mastery = read_pkl("../pkl_file", "dummy.pkl")
mbti_mastery = read_pkl("../pkl_file", "mbti_mastery.pkl")
print(type(mbti_mastery))
print(len(mbti_mastery))
# model = tf.keras.models.load_model(os.path.join("../pkl_file","mbti_model.h5"))
encoder = LabelEncoder()
encoder.classes_ = np.load(os.path.join("../pkl_file","encoder.npy"))

def recommend_champ_by_mbti(mbti,ascending):
    global mbti_mastery

    if(setting.updated):
        update_mbti_mastery()
        mbti_mastery = read_pkl("../pkl_file", "mbti_mastery.pkl")
    return mbti_mastery.loc[mbti].sort_values(axis=0,ascending=ascending)[:5]

def recommend_mbti_by_sohwan(sohwan,ascending):
    global sohwan_mastery
    global model
    global encoder

    # X = np.mat(sohwan_mastery.loc[sohwan,:])
    # Y_pred = model.predict(X)

    # return model.inverse_transform(np.argmax(Y_pred,axis=1))[0]
    return 'TEST'
def learning():
    #
    mbti_merged = read_pkl("../pkl_file", "mbti_merged.pkl")
    dataset = mbti_merged.values
    X = dataset[:,:-1].astype(float)
    Y_obj = dataset[:,-1]

    e = LabelEncoder()
    e.fit(Y_obj)
    Y = e.transform(Y_obj)

    Y_encoded = np_utils.to_categorical(Y)
    Y_encoded

    # 모델 설정
    model = Sequential()
    model.add(Dense(16, input_dim=151, activation='relu'))
    model.add(Dense(16,activation='softmax'))

    # 모델 컴파일
    model.compile(loss='categorical_crossentropy',
                optimizer='adam',
                metrics=['accuracy'])

    # 모델 실행
    model.fit(X,Y_encoded,epochs=50,batch_size=1)

