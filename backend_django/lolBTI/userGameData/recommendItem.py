import numpy as np
import pandas as pd
import os

DATA_DIR = "./userGameData"
DUMP_FILE = os.path.join(DATA_DIR, "item.pkl")

def load_dataframes():
    return pd.read_pickle(DUMP_FILE)

items = load_dataframes()

def getItems(myChamp, opponentChamp):
    global items
    if(myChamp in items):
        if(opponentChamp in items[myChamp]):
            itemDict = items[myChamp][opponentChamp]
        else:
            return "데이터가 없음", "데이터가 없음"
    else:
        return "데이터가 없음", "데이터가 없음"
    cnt = itemDict['cnt']
    del itemDict['cnt']
    keyList = list(itemDict.keys())
    valueList = list(itemDict.values())
    visit = [0] * len(valueList)
    sortedKeyList = []
    sortedValueList = []

    for i in range(len(valueList)):
        if i >= 10:
            break
        maxN = 0
        maxIdx = -1
        for j in range(len(valueList)):
            if visit[j] != 0:
                continue
            if valueList[j] > maxN:
                maxN = valueList[j]
                maxIdx = j
        sortedKeyList.append(keyList[maxIdx])
        sortedValueList.append(maxN*100.0/cnt)
        visit[maxIdx] = 1
    print(sortedKeyList)
    print(sortedValueList)
    return sortedKeyList, sortedValueList

