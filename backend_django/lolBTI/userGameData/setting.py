api_key_list = [
    'RGAPI-b6822d6f-04fd-4d98-8f41-705d9d038491',
    'RGAPI-1a4b3463-b171-414a-b26d-3c0eb1bc6a66',
    'RGAPI-6e9d1947-08ff-45df-889d-ed4cba46849f',
    'RGAPI-914c67f1-e069-431b-833f-4262481851d2',
    'RGAPI-edc57be5-357d-40dd-8a5a-c01966c180d9',
    'RGAPI-911ed4a1-b7b7-466d-8ffa-3dd532c5ee4b',
    'RGAPI-b1272ff0-5a2c-4819-81c9-96e3e8417963',
    'RGAPI-c1a1f277-1c25-4b8b-b803-9755439763d9'
]

sohwan_mastery = None

updated = False

def update_sohwan_mastery():
    global updated
    print("sohwan_mastery updated")
    updated = True

def apply_sohwan_mastery():
    global updated
    print("sohwan_mastery applied")
    updated = False