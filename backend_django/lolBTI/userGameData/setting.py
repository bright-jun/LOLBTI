api_key_list = [
    'RGAPI-e2156167-c88a-4fd4-8012-83aad126c933',
    'RGAPI-c1a1f277-1c25-4b8b-b803-9755439763d9',
    'RGAPI-b1272ff0-5a2c-4819-81c9-96e3e8417963',
    'RGAPI-911ed4a1-b7b7-466d-8ffa-3dd532c5ee4b',
    'RGAPI-edc57be5-357d-40dd-8a5a-c01966c180d9',
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